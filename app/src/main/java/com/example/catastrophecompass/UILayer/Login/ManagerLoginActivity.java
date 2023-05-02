package com.example.catastrophecompass.UILayer.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class ManagerLoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);

        usernameEditText = findViewById(R.id.edt_username_ac_ma_lo_ac);
        passwordEditText = findViewById(R.id.edt_password_ac_ma_lo_ac);
        loginButton = findViewById(R.id.btn_login_ac_ma_lo_ac);
        progressBar = findViewById(R.id.progressBar);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButtonClick();
            }
        });
    }

    private void onLoginButtonClick() {
        // Perform the login process
        // Show the ProgressBar when the process takes time
        progressBar.setVisibility(View.VISIBLE);
    }
}