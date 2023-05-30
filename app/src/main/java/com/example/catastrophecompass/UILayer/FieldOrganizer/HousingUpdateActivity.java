package com.example.catastrophecompass.UILayer.FieldOrganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.DataLayer.Model.HousingInfo;
import com.example.catastrophecompass.R;

import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HousingUpdateActivity extends AppCompatActivity implements HousingInterface {
    private EditText noHouseEditText;
    private EditText ContainerEditText;
    private EditText HasHouseEditText;

    private Button doneButton;
    private UpdateHousingVM VM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_update);
        VM = new ViewModelProvider(this).get(UpdateHousingVM.class);

        noHouseEditText = findViewById(R.id.edt_Housing_Up_No_House);
        ContainerEditText = findViewById(R.id.edt_Housing_Up_Container);
        HasHouseEditText = findViewById(R.id.edt_Housing_Up_Has_House);


        doneButton = findViewById(R.id.btn_doneButtonHousingUpdate_ac_ho_up_ac);

        preDisplay();

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateHousingInfo();
            }
        });
    }

    public void preDisplay() {
        // Assuming you have a method to fetch a HousingInfo object in your VM
        HousingInfo houseInfo = VM.getHousingInfo();

        // Fill in the EditTexts
        noHouseEditText.setText(houseInfo.getNoHouse());
        ContainerEditText.setText(houseInfo.getContainer());
        HasHouseEditText.setText(houseInfo.getHasHouse());

    }

    private void updateHousingInfo() {
        String noHouse = noHouseEditText.getText().toString();
        String oneRoom = HasHouseEditText.getText().toString();
        String twoRoom = ContainerEditText.getText().toString();


        // Assuming you have a method to update a HousingInfo object in your VM
        VM.updateHousingInfo(new HousingInfo(noHouse, oneRoom, twoRoom));
    }

    @Override
    public void getHousingInfo(HousingInterface houseInt) {
        // Implementation for the housingInterface
    }
}