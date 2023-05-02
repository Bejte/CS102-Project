package com.example.catastrophecompass.UILayer.TeamLeader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamLeaderJobFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamLeaderJobFragment extends Fragment {
    private RecyclerView recyclerView;
    private UpdateTeamsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teams_for_teamleader);

        // Set the title
        setTitle("Update Teams");

        // Get the RecyclerView and set its layout manager
        recyclerView = findViewById(R.id.rec_teamsForTeamleader_ac_up_te_fo_te_ac);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create the adapter and set it to the RecyclerView
        adapter = new UpdateTeamsAdapter();
        recyclerView.setAdapter(adapter);
    }
}