package com.example.catastrophecompass.UILayer.HQOrganizer.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class AddEditMemberActivity extends AppCompatActivity {

    private Button doneButton;
    private HQAddEditMemberVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_member);
        String contactName = getIntent().getStringExtra("contact_name");

        // Initialize your ViewModel here
        viewModel = new ViewModelProvider(this).get(HQAddEditMemberVM.class);

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

                // You should have the required values for logistics, hqo, to, and organizationName by this point. They can be retrieved from the fragment or the inputs of the activity.

                switch (selectedRadioButton) {
                    case 0:
                        // Call viewModel method for IfFieldOrganizerFragment
                        break;
                    case 1:
                        Logistics logistics = // Initialize or get your Logistics instance here
                                String organizationName = // Get your organization name here
                            viewModel.addLogisticMember(logistics, organizationName);
                        break;
                    case 2:
                        // Call viewModel method for IfTeamLeaderFragment
                        break;
                    case 3:
                        HQO hqo = // Initialize or get your HQO instance here
                                viewModel.addHQOrganizator(hqo, organizationName);
                        break;
                    default:
                        // default case
                        break;
                }
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