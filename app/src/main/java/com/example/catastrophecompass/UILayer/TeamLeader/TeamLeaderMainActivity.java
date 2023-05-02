package com.example.catastrophecompass.UILayer.TeamLeader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.TeamOrganizor.JobFragment;

import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;

public class TeamLeaderMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_leader_main);

        Toolbar toolbar = findViewById(R.id.toolbar_TL_te_le_ap_bar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fr_teamLeaderFragment_ac_te_le_ma_ac, new JobFragment())
                    .commit();
        }

        ImageButton iconJob = findViewById(R.id.ic_TLbar_job_icon_te_le_ap_ba);
        ImageButton icon2 = findViewById(R.id.ic_TLbar_area_icon_te_le_ap_ba);
        ImageButton icon3 = findViewById(R.id.ic_TLbar_chat_icon_te_le_ap_ba);
        ImageButton icon4 = findViewById(R.id.ic_TLbar_people_icon_te_le_ap_ba);
        ImageButton icon5 = findViewById(R.id.ic_TLbar_help_icon_te_le_ap_ba);

        iconJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fr_teamLeaderFragment_ac_te_le_ma_ac, new JobFragment())
                        .commit();
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fr_teamLeaderFragment_ac_te_le_ma_ac, new AreaInfoFragmentForTeamLeader())
                        .commit();
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getSupportFragmentManager().beginTransaction()
                //.replace(R.id.team_leader_fragment_container, new peopleFragment())
                // .commit();
            }
        });

        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  getSupportFragmentManager().beginTransaction()
                //.replace(R.id.team_leader_fragment_container, new chatFragment())
                //  .commit();
            }
        });

        icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(TeamLeaderActivity.this, SixthActivity.class);
                // startActivity(intent);
            }
        });
    }
}