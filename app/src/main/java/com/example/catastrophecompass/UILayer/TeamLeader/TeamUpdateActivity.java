package com.example.catastrophecompass.UILayer.TeamLeader;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;

import java.util.ArrayList;

public class TeamUpdateActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TeamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teams_for_teamleader);

        recyclerView = findViewById(R.id.rec_teamsForTeamleader_ac_up_te_fo_te_ac);

        // Add some dummy data for the adapter
        ArrayList<String> teamList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            teamList.add("Team " + (i+1));
        }

        adapter = new TeamAdapter(teamList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
