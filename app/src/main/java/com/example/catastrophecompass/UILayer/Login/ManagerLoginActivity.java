package com.example.catastrophecompass.UILayer.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.catastrophecompass.DataLayer.Model.UserLogin;
import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.FieldOrganizer.FieldOrganizerMainActivity;
import com.example.catastrophecompass.UILayer.HQOrganizer.HQOrganizerMainActivity;
import com.example.catastrophecompass.UILayer.Logistics.LogisticsMainActivity;
import com.example.catastrophecompass.UILayer.TeamLeader.TeamLeaderMainActivity;
import com.example.catastrophecompass.UILayer.TeamOrganizor.TeamOrganizorMainActivity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ManagerLoginActivity extends AppCompatActivity {

    private ManagerLoginVM viewModel;
    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);

        viewModel = new ViewModelProvider(this).get(ManagerLoginVM.class);

        edtUsername = findViewById(R.id.edt_username_manager_login);
        edtPassword = findViewById(R.id.edt_password_manager_login);
        btnLogin = findViewById(R.id.btn_login_manager_login);
        progressBar = findViewById(R.id.progressBar);

        final String[] userType = new String[1];

        btnLogin.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE); // Show the ProgressBar
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            UserLogin userLogin = new UserLogin(username, password);
            userType[0] = viewModel.validateLogin(userLogin);
            progressBar.setVisibility(View.GONE);
            if (userType[0] == null)
                warnUser();
            else
                navigateToMainPage(userType[0]);
        });
    }

    private void navigateToMainPage(String userType) {
        Intent intent = null;
        switch (userType) {
            case "Logistic":
                intent = new Intent(this, LogisticsMainActivity.class);
                break;
            case "Field Organizer":
                intent = new Intent(this, FieldOrganizerMainActivity.class);
                break;
            case "Head Quarter":
                intent = new Intent(this, HQOrganizerMainActivity.class);
                break;
            case "Team Organizer":
                intent = new Intent(this, TeamOrganizorMainActivity.class);
                break;
            case "Team Leader":
                intent = new Intent(this, TeamLeaderMainActivity.class);
                break;
            default:
                finish();
        }
        startActivity(intent);
    }

    private void warnUser() {
        Toast.makeText(this, "Invalid login, please try again.", Toast.LENGTH_LONG).show();
    }
}