package com.example.catastrophecompass.UILayer.FieldOrganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;

/**
 * Attach DemographicStatusFragment, AidStatusFragment, ChatFragment, ChatPeopleFragment.
 */

import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.catastrophecompass.UILayer.Common.Chat.ChatActivity;
import com.google.android.material.bottomappbar.BottomAppBar;

public class FieldOrganizorMainActivity extends AppCompatActivity {
    private Button updateDemographicsButton;
    private BottomAppBar bottomAppBar;
    private ImageButton icon1, icon2, icon3, icon4, icon5;
    private Button updateHousingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_organizor_main);
        TextView headline = findViewById(R.id.headline);
        headline.setText("FIELD ORGANIZER");

        updateDemographicsButton = findViewById(R.id.btn_update_ac_fi_or_ma_ac);

        updateDemographicsButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, UpdateDemographicsActivity.class);
            startActivity(intent);
        });
        updateHousingButton = findViewById(R.id.btn_updatebig_housing_chart_ac_fi_or_ma_ac);

        updateHousingButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, UpdateHousingActivity.class);
            startActivity(intent);
        });

        bottomAppBar = findViewById(R.id.bar_field_organizer_ac_fi_or_ma_ac);
        icon1 = findViewById(R.id.ic_icon1_of_appbar_fieldorga_main_ac);
        icon2 = findViewById(R.id.ic_icon2_of_appbar_fieldorga_main_ac);
        icon3 = findViewById(R.id.ic_icon3_of_appbar_fieldorga_main_ac);
        icon4 = findViewById(R.id.ic_icon4_of_appbar_fieldorga_main_ac);
        icon5 = findViewById(R.id.ic_icon5_of_appbar_fieldorga_main_ac);

        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FieldOrganizorMainActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FieldOrganizorMainActivity.this, PeopleActivity.class);
                startActivity(intent);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// Intent intent = new Intent(FieldOrganizerMain.this, ThirdActivity.class);
                // startActivity(intent);
            }
        });

        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(FieldOrganizerMain.this, FourthActivity.class);
                //startActivity(intent);
            }
        });

        icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(FieldOrganizerMain.this, FifthActivity.class);
                //  startActivity(intent);
            }
        });

        // Set up the remaining icons with their respective listeners here
    }
}