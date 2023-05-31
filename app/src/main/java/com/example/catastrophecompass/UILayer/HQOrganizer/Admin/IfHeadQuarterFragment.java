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

public class IfHeadQuarterFragment extends Fragment {

    private TextView fragmentName1;
    private TextView fragmentName2;
    private CheckBox adminCheckBox;
    private CheckBox notAdminCheckBox;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_if_head_quarter, container, false);


        adminCheckBox = view.findViewById(R.id.checkbox_admin_hq);
        notAdminCheckBox = view.findViewById(R.id.checkbox_not_admin_hq);

        return view;
    }
}