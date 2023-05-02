package com.example.catastrophecompass.UILayer.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;

import com.example.myapplication.FieldOrganizerMain;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginOptionsctivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button volunteerButton = findViewById(R.id.btn_volunteer_login_fr);
        volunteerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVolunteerDialog();
                //Intent intent = new Intent(MainActivity.this, VolunteerActivity.class);
                // startActivity(intent);
            }
        });


        Button managerButton = findViewById(R.id.btn_manager_login_fr);
        managerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginOptionsctivity.this, ManagerLoginActivity.class);
                startActivity(intent);
                //Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
                //startActivity(intent);
            }
        });

        //silinecek
        Button fieldOrganizerMainButton = findViewById(R.id.field_organizer_main_button);

        fieldOrganizerMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginOptionsActivity.this, FieldOrganizerMain.class);
                startActivity(intent);
            }
        });

        Button volunteerInBaseButton = findViewById(R.id.btn_I_am_infield_login2_fr);

        // Set up a click listener
        volunteerInBaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yourQRDataString = "place_name|job1,job2,job3";

                // Launch ThirdActivity with the QR data
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                intent.putExtra("qrData", yourQRDataString);
                startActivity(intent);


            }
        });
    }

    private void showVolunteerDialog() {
        String[] choices = new String[]{"What can I do?", "I am in the field"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose an option")
                .setItems(choices, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                showWhatCanIDoDialog();
                                // Handle "What can I do?" option here
                                break;
                            case 1:
                                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                startActivity(intent);
                                // Handle "I am in the field" option here
                                break;
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showWhatCanIDoDialog() {
        Intent intent = new Intent(MainActivity.this, SixthActivity.class);
        startActivity(intent);
    }
}