package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

import com.example.catastrophecompass.DataLayer.Model.WItem;
import com.example.catastrophecompass.DataLayer.Model.WorkplaceWeather;
import com.example.catastrophecompass.DomainLayer.Common.OVCommon;
import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.VolunteerInBase.VIBStartActivity;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

public class OVWorkforceActivity extends AppCompatActivity {

    private OVWorkforceVM viewModel;
    private WItem wItem;
    private TextView currentTempTextView;
    private TextView currentWeatherTypeTextView;
    private TextView placeInfo;
    private TextView addressInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ovworkforce);

        placeInfo = findViewById(R.id.txt_place_info_in_co);
        addressInfo = findViewById(R.id.txt_address_info_ad_in);
        wItem = OVCommon.clickedWItem;

        placeInfo.setText(wItem.getName());
        addressInfo.setText(wItem.getAddress());

        currentTempTextView = findViewById(R.id.txt_tempeture_ov_wo_it);
        currentWeatherTypeTextView = findViewById(R.id.txt_weather_desc_weather_fr);
        Button arrivedButton = findViewById(R.id.arrived_button_ov);
        arrivedButton.setOnClickListener(v -> {
            // Start the VIBStartActivity when the arrived button is clicked
            Intent intent = new Intent(this, VIBStartActivity.class);
            startActivity(intent);
        });

        // Observe weather data changes
        onWeatherInfoUpdated(viewModel.getWeatherInfo(OVCommon.clickedWItem.getLocation()));

    }

    private void onWeatherInfoUpdated(WorkplaceWeather weather) {
        // Update UI with weather information
        currentTempTextView.setText(weather.getCurrentTemp());
        currentWeatherTypeTextView.setText(weather.getCurrentWeatherType());


    }



}