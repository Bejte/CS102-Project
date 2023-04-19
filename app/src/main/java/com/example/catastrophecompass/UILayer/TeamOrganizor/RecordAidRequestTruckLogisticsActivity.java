package com.example.catastrophecompass.UILayer.TeamOrganizor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;

/**
 * Will be accessed from LogisticInfoFragment if the userType == TeamOrganizor & not available to HQ
 */

public class RecordAidRequestTruckLogisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_aid_request_truck_logistics);
    }
}