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
    Button btnAdd;

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
        btnAdd = view.findViewById(R.id.add_button_admin);

        // set a click listener for the button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create an intent to open the activity
                Intent intent = new Intent(getActivity(), AddEditOrganizationActivity.class);

                // use putExtra to add a key-value pair to the intent
                // replace "your_key" and "your_value" with your actual key and value


                // start the activity
                startActivity(intent);
            }
        });

        return view;
    }
}