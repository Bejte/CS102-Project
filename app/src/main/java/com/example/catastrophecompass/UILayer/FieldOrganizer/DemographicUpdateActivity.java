package com.example.catastrophecompass.UILayer.FieldOrganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.UpdateDemographicsAdapter;


import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class DemographicUpdateActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button doneButton;
    private UpdateDemographicsAdapter updateDemographicsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demographic_update);

        recyclerView = findViewById(R.id.rec_UpdateDemographics_ac_de_up_ac);
        doneButton = findViewById(R.id.btn_doneButtonUpdateDemographics_ac_de_up_ac);

        // Set up the data and adapter for the RecyclerView
        List<String> labelTexts = Arrays.asList("Label 1", "Label 2", "Label 3");
        updateDemographicsAdapter = new UpdateDemographicsAdapter(labelTexts);
        recyclerView.setAdapter(updateDemographicsAdapter);

        doneButton.setOnClickListener(v -> {
            // Handle the Done button click event here
            finish();
        });
    }
}