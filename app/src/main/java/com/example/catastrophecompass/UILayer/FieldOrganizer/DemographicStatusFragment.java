package com.example.catastrophecompass.UILayer.FieldOrganizer;

import android.app.Activity;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.R;


import android.content.Intent;
import android.widget.Button;

import androidx.lifecycle.ViewModelProvider;

public class DemographicStatusFragment extends Fragment implements DemographicInterface, HousingInterface {
    private DemographicVM DVM;
    private ActivityResultLauncher<Intent> demographicUpdateActivityLauncher;
    private ActivityResultLauncher<Intent> housingUpdateActivityLauncher;

    public DemographicStatusFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_field_organizer_first, container, false);
        DVM = new ViewModelProvider(this).get(DemographicVM.class);

        Button button = view.findViewById(R.id.button);
        Button button2 = view.findViewById(R.id.button2);

        demographicUpdateActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // Handle the result from DemographicUpdateActivity here
                        }
                    }
                });

        housingUpdateActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // Handle the result from HousingUpdateActivity here
                        }
                    }
                });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DemographicUpdateActivity.class);
                demographicUpdateActivityLauncher.launch(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HousingUpdateActivity.class);
                housingUpdateActivityLauncher.launch(intent);
            }
        });

        return view;
    }

    @Override
    public void getDemographicInfo(DemographicInterface demoint) {
        DVM.getDemographicInfo(this);
    }

    @Override
    public void getHousingInfo(HousingInterface houseint) {
        DVM.getHousingInfo(this);
    }
}