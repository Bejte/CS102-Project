package com.example.catastrophecompass.UILayer.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.catastrophecompass.DataLayer.Model.UserLogin;
import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.ManagerLoginViewModel;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ManagerLoginActivity extends AppCompatActivity {

    private ManagerLoginViewModel viewModel;
    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);

        viewModel = new ViewModelProvider(this).get(ManagerLoginViewModel.class);

        edtUsername = findViewById(R.id.edt_username_manager_login);
        edtPassword = findViewById(R.id.edt_password_manager_login);
        btnLogin = findViewById(R.id.btn_login_manager_login);
        progressBar = findViewById(R.id.progressBar);

        btnLogin.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE); // Show the ProgressBar
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            UserLogin userLogin = new UserLogin(username, password);
            viewModel.validateLogin(userLogin);
        });

        viewModel.getUser().observe(this, user -> {
            progressBar.setVisibility(View.GONE); // Hide the ProgressBar
            if (user != null) {
                navigateToMainPage(user.getUserType());
            }
        });

        viewModel.getLoginFailure().observe(this, failure -> {
            progressBar.setVisibility(View.GONE); // Hide the ProgressBar
            if (failure != null && failure) {
                warnUser();
            }
        });
    }

    private void navigateToMainPage(String userType) {
        Intent intent = new Intent(this, LoginOptionsActivity.class);
        intent.putExtra("userType", userType);
        startActivity(intent);
    }

    private void warnUser() {
        Toast.makeText(this, "Invalid login, please try again.", Toast.LENGTH_LONG).show();
    }
}