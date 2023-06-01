package com.example.catastrophecompass.UILayer.Logistics;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;
import com.example.catastrophecompass.R;

import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MissionFragment extends Fragment implements LogisticMissionInterface{

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

        viewModel.getLogisticInfo(this);

        getCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                viewModel.getChecked(this);
            }
        });

        dropCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                viewModel.dropClicked(this);
            }
        });
    }

    @Override
    public void display(LogisticInfo logisticInfo) {

    }

    @Override
    public void warnUser() {

    }

    @Override
    public void notifyGetSuccess() {

    }

    @Override
    public void notifyGetFailed() {

    }

    @Override
    public void notifyDropSuccess() {

    }

    @Override
    public void notifyDropFailed() {

    }
}