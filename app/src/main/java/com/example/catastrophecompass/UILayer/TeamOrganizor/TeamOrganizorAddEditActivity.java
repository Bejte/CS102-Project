package com.example.catastrophecompass.UILayer.TeamOrganizor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.catastrophecompass.DataLayer.Model.Team;
import com.example.catastrophecompass.R;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Display changes regarding to the intent starting source
 * For details, see sketches
 */
@AndroidEntryPoint
public class TeamOrganizorAddEditActivity extends AppCompatActivity {

    private EditText teamTypeEditText;
    private EditText descriptionEditText;
    private EditText teamLeaderEditText;
    private Button discardButton;
    private Button addButton;
    private Team team;

    // Inject the ViewModel
    @Inject
    TeamOrganizerAddEditVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_organizor_add_edit);

        // Get the Intent that started this activity and extract the team
        Intent intent = getIntent();
        team = (Team) intent.getSerializableExtra("team");

        // Here, you should replace the ids with the correct ones from your XML file
        teamTypeEditText = findViewById(R.id.teamTypeEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        teamLeaderEditText = findViewById(R.id.teamLeaderEditText);
        discardButton = findViewById(R.id.discardButton);
        addButton = findViewById(R.id.addButton);

        // Populate the fields with the team data
        teamTypeEditText.setText(team.getTeamName());
        descriptionEditText.setText(team.getDescription());
        teamLeaderEditText.setText(team.getTeamLeader());

        // Discard button returns to previous activity without saving changes
        discardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.discardTeam(team.getCity(), team.getPlace(), team);
                finish();
            }
        });

        // Add/Update button saves the changes and returns to previous activity
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team.setTeamName(teamTypeEditText.getText().toString());
                team.setDescription(descriptionEditText.getText().toString());
                team.setTeamLeader(teamLeaderEditText.getText().toString());

                // If the team has an id, it already exists and we should edit it. Otherwise, we should add a new team.
                    viewModel.addTeam(team.getCity(), team.getPlace(), team);

                finish();
            }
        });
    }
}