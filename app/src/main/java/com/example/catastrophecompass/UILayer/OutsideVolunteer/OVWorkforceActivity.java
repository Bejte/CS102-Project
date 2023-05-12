package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OVWorkforceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ovworkforce);
        Button arrivedButton = findViewById(R.id.arrived_button);
        TextView placeInfo = findViewById(R.id.place_info);
        TextView addressInfo = findViewById(R.id.address_info);

        placeInfo.setText("Example Place Information");
        addressInfo.setText("Example Address Information");
// Set a click listener for the button
        arrivedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the SecondActivity
                Intent intent = new Intent(OVWorkforceActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        String cityName = getIntent().getStringExtra("cityName");
    }
}