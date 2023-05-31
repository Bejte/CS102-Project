package com.example.catastrophecompass.UILayer.HQOrganizer.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.catastrophecompass.R;

public class IfTeamLeaderFragment extends Fragment {

    private TextView fragmentName;
    private RadioGroup materialRadioGroup;
    private RadioButton rdbCloth;
    private RadioButton rdbFood;
    private RadioButton rdbHeating;
    private RadioButton rdbTransportation;
    private RadioButton rdbHyg;
    private RadioButton rdbCustom;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_if_team_leader, container, false);


       rdbCloth=view.findViewById(R.id.cloth_radio_button);
        rdbFood=view.findViewById(R.id.food_radio_button);
        rdbCloth=view.findViewById(R.id.heating_radio_button);
        rdbCloth=view.findViewById(R.id.transport_radio_button);
        rdbCloth=view.findViewById(R.id.hyg_radio_button);
        rdbCloth=view.findViewById(R.id.custom_radio_button);



        return view;
    }
}
