package com.example.catastrophecompass.UILayer.VolunteerInBase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VIBJobFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.Button;

import com.example.catastrophecompass.UILayer.Common.VIBJobInfoVM;
import com.example.catastrophecompass.UILayer.Login.LoginOptionsActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class VIBJobFragment extends Fragment {

    private VIBJobInfoVM vibJobInfoVM;

    public VIBJobFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vibJobInfoVM = new ViewModelProvider(this).get(VIBJobInfoVM.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vib_job, container, false);

        TextView jobInfoTitle = view.findViewById(R.id.txt_title_job_info_vib_job);
        TextView jobDescription = view.findViewById(R.id.job_description_vib_job);
        ImageView teamLeaderImage = view.findViewById(R.id.team_leader_image_vib_job);
        TextView teamLeaderInfo = view.findViewById(R.id.team_leader_info_vib_job);
        TextView teamLeaderName = view.findViewById(R.id.team_leader_name);
        Button quitToLogoutButton = view.findViewById(R.id.quit_to_logout_button_vib);


        jobInfoTitle.setText(vibJobInfoVM.getJobTitle());
        jobDescription.setText(vibJobInfoVM.getJobDescription());
        teamLeaderInfo.setText(vibJobInfoVM.getTeamLeaderInfo());
        teamLeaderName.setText(vibJobInfoVM.getTeamLeaderName());


        quitToLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginOptionsActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}