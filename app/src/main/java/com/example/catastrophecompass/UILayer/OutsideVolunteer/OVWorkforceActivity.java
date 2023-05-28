package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.DataLayer.Model.WorkplaceWeather;
import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.OVWorkforceVM;
import com.example.catastrophecompass.UILayer.VolunteerInBase.VIBStartActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class OVWorkforceActivity extends AppCompatActivity {

    private OVWorkforceVM viewModel;
    private TextView currentTempTextView;
    private TextView currentWeatherTypeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ovworkforce);


        currentTempTextView = findViewById(R.id.txt_tempeture_ov_wo_it);
        currentWeatherTypeTextView = findViewById(R.id.txt_weather_desc_weather_fr);
        Button arrivedButton = findViewById(R.id.arrived_button_ov);
        arrivedButton.setOnClickListener(v -> {
            // Start the VIBStartActivity when the arrived button is clicked
            Intent intent = new Intent(this, VIBStartActivity.class);
            startActivity(intent);
        });

        // Observe weather data changes
        viewModel.getWeatherInfo(location).observe(this, this::onWeatherInfoUpdated);

        // Call method to get workforce job info
        viewModel.getWorkforceJobInfo();
    }

    private void onWeatherInfoUpdated(WorkplaceWeather workplaceWeather) {
        // Update UI with weather information
        currentTempTextView.setText(workplaceWeather.getCurrentTemp());
        currentWeatherTypeTextView.setText(workplaceWeather.getCurrentWeatherType());


    }



}