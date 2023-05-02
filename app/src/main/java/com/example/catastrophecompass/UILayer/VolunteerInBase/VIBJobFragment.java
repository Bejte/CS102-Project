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
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class JobsInfoFragment extends Fragment {

    public JobsInfoFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_i_b_job, container, false);

        // Initialize UI components
        TextView jobDescription = view.findViewById(R.id.job_description);
        ImageView teamLeaderImage = view.findViewById(R.id.team_leader_image);
        TextView teamLeaderInfo = view.findViewById(R.id.team_leader_info);
        Button switchJobsButton = view.findViewById(R.id.switch_jobs_button);
        Button quitToLogoutButton = view.findViewById(R.id.quit_to_logout_button);

        // Set job description text (use actual text instead of placeholder)
        jobDescription.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit...");

        // Set onClickListeners for the buttons
        switchJobsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog("Switch jobs?");
            }
        });

        quitToLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog("Quit to logout?");
            }
        });

        return view;
    }

    private void showConfirmationDialog(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(title);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add your code for Yes button click here
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add your code for No button click here
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Change the color of the positive button
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FFC107")); // Change the color according to your preference

        // Change the color of the negative button
        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        negativeButton.setTextColor(Color.parseColor("#FFFFFF")); // Change the color according to your preference
    }
}