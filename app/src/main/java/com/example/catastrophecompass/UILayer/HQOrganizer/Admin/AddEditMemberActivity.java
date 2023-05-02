package com.example.catastrophecompass.UILayer.HQOrganizer.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class AddEditMemberActivity extends AppCompatActivity {

    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_member);

        doneButton = findViewById(R.id.btn_doneButton_ac_ad_ed_me_ac);
        Intent intent = getIntent();
        // Get the selected radio button index from the previous activity
        int selectedRadioButton = getIntent().getIntExtra("selectedRadioButton", -1);

        // Set the corresponding fragment
        Fragment fragment;
        switch (selectedRadioButton) {
            case 0:
                fragment = new IfFieldOrganizerFragment();
                break;
            case 1:
                fragment = new IfLogisticFragment();
                break;
            case 2:
                fragment = new IfTeamLeaderFragment();
                break;
            case 3:
                fragment = new IfHeadQuarterFragment();
                break;
            default:
                fragment = new IfFieldOrganizerFragment();
                break;
        }

        loadFragment(fragment);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navigate to the next activity when the done button is clicked
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}