package com.example.catastrophecompass.UILayer.TeamLeader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.AreaFragment;
import com.example.catastrophecompass.UILayer.Common.Chat.ChatActivity;
import com.example.catastrophecompass.UILayer.Common.Chat.PeopleFragment;
import com.example.catastrophecompass.UILayer.OutsideVolunteer.OVMainActivity;
import com.example.catastrophecompass.UILayer.TeamOrganizor.JobFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


public class TeamLeaderMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_leader_main);

        BottomNavigationView bottomNav = findViewById(R.id.app_bar_logistic);
        bottomNav.setOnItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_tl,
                    new JobFragment()).commit();
        }
    }

    private BottomNavigationView.OnItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.fived_bar_job_icon:
                        selectedFragment = new JobFragment();
                        break;
                    case R.id.fived_bar_area_icon:
                        selectedFragment = new AreaInfoFragmentForTeamLeader();
                        break;
                    case R.id.fived_bar_chat_icon:
                        Intent intentChat = new Intent(TeamLeaderMainActivity.this, ChatActivity.class);
                        startActivity(intentChat);
                        return true;
                    case R.id.fived_bar_people_icon:
                        selectedFragment = new PeopleFragment();
                        break;
                    case R.id.fived_bar_fived_icon:
                        Intent intentOVMain = new Intent(TeamLeaderMainActivity.this, OVMainActivity.class);
                        startActivity(intentOVMain);
                        return true;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_tl,
                        selectedFragment).commit();

                return true;
            };
}