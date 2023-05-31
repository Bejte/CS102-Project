package com.example.catastrophecompass.UILayer.HQOrganizer.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.catastrophecompass.R;



public class GeneralInfoAdmin extends Fragment {
    Button btnAddOrganization;
    Button btnAddMember;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general_info, container, false);

        // find the button from the view
        btnAddOrganization = view.findViewById(R.id.add_organization_button_admin);
        btnAddMember=view.findViewById(R.id.add_member_button_admin);
        btnAddMember.setVisibility(View.VISIBLE);

        // set a click listener for the button
        btnAddOrganization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create an intent to open the activity
                Intent intent = new Intent(getActivity(), AddEditOrganizationActivity.class);

                startActivity(intent);
            }
        });
        btnAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create an intent to open the activity
                Intent intent = new Intent(getActivity(), AddEditMemberActivity.class);

                startActivity(intent);
            }
        });

        return view;
    }
}