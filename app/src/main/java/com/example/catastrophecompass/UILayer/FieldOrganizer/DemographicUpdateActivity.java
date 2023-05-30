package com.example.catastrophecompass.UILayer.FieldOrganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.DataLayer.Model.DemographicInfo;
import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.UpdateDemographicsAdapter;


import android.widget.Button;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class DemographicUpdateActivity extends AppCompatActivity implements demographicInterface {
    private EditText male08EditText;
    private EditText female08EditText;
    private EditText male814EditText;
    private EditText female814EditText;
    private EditText male1564EditText;
    private EditText female1564EditText;
    private EditText male65EditText;
    private EditText female65EditText;
    private Button doneButton;
    private DemographicUpdateVM VM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demographic_update);
        VM = new ViewModelProvider(this).get(DemographicUpdateVM.class);

        male08EditText = findViewById(R.id.edt_male_0_8);
        female08EditText = findViewById(R.id.edt_female_0_8);
        male814EditText = findViewById(R.id.edt_male_8_14);
        female814EditText = findViewById(R.id.edt_female_8_14);
        male1564EditText = findViewById(R.id.edt_male_15_64);
        female1564EditText = findViewById(R.id.edt_female_15_64);
        male65EditText = findViewById(R.id.edt_male_65_plus);
        female65EditText = findViewById(R.id.edt_female_65_plus);
        doneButton = findViewById(R.id.btn_doneButtonUpdateDemographics_ac_de_up_ac);

        preDisplay();

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDemographicInfo();
            }
        });
    }

    public void preDisplay() {
        // Assuming you have a method to fetch a DemographicInfo object in your VM
        DemographicInfo demoInfo = VM.getDemographicInfo();

        // Fill in the EditTexts
        male08EditText.setText(demoInfo.getMale08());
        female08EditText.setText(demoInfo.getFemale08());
        male814EditText.setText(demoInfo.getMale814());
        female814EditText.setText(demoInfo.getFemale814());
        male1564EditText.setText(demoInfo.getMale1564());
        female1564EditText.setText(demoInfo.getFemale1564());
        male65EditText.setText(demoInfo.getMale65());
        female65EditText.setText(demoInfo.getFemale65());
    }

    private void updateDemographicInfo() {
        int male08 = Integer.parseInt(male08EditText.getText().toString());
        int female08 = Integer.parseInt(female08EditText.getText().toString());
        int male814 = Integer.parseInt(male814EditText.getText().toString());
        int female814 = Integer.parseInt(female814EditText.getText().toString());
        int male1564 = Integer.parseInt(male1564EditText.getText().toString());
        int female1564 = Integer.parseInt(female1564EditText.getText().toString());
        int male65 = Integer.parseInt(male65EditText.getText().toString());
        int female65 = Integer.parseInt(female65EditText.getText().toString());

        // Assuming you have a method to update a DemographicInfo object in your VM
        VM.updateDemographicInfo(new DemographicInfo(male08, female08, male814, female814, male1564, female1564, male65, female65));
    }

    @Override
    public void getDemographicInfo(demographicInterface demoInt) {
        // Implementation for the demographicInterface
    }
}