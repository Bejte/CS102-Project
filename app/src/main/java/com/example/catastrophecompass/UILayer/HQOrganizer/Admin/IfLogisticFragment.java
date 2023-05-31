package com.example.catastrophecompass.UILayer.HQOrganizer.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.catastrophecompass.R;

public class IfLogisticFragment extends Fragment {

    private TextView fragmentName;
    private TextView carSize;
    private CheckBox smallCheckBox;
    private CheckBox midCheckBox;
    private CheckBox largeCheckBox;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_if_logistic, container, false);


        smallCheckBox = view.findViewById(R.id.checkbox_small_log);
        midCheckBox = view.findViewById(R.id.checkbox_mid_log);
        largeCheckBox = view.findViewById(R.id.checkbox_large_log);

        return view;
    }
}