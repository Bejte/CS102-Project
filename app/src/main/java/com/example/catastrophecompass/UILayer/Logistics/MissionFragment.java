package com.example.catastrophecompass.UILayer.Logistics;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.viewModels;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import your.package.name.R;

@AndroidEntryPoint
public class MissionFragment extends Fragment {

    private TextView logisticGet;
    private CheckBox getCheckbox, dropCheckbox;
    private LogisticMissionVM viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mission, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Instantiate your ViewModel using the 'by viewModels()' Kotlin property delegate
        viewModel = new ViewModelProvider(this).get(LogisticMissionVM.class);

        logisticGet = view.findViewById(R.id.logistic_get);
        getCheckbox = view.findViewById(R.id.getCheckbox);
        dropCheckbox = view.findViewById(R.id.dropCheckbox);

        viewModel.getLogisticInfo(new LogisticMissionInterface() {
            logisticGet.setText(getAddressInfo());
            //TO DO:
        });

        getCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                viewModel.getChecked(new LogisticMissionInterface() {
                    // TO DO
                });
            }
        });

        dropCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                viewModel.dropClicked();
            }
        });
    }
}