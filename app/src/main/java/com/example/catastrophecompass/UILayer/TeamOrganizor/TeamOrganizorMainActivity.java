package com.example.catastrophecompass.UILayer.TeamOrganizor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;


/**
 * Attach GeneralInfoFragment, AreaFragment, PeopleFragment, LogisticInfoFragment & ChatFragment
 */

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class TeamOrganizatorActivity extends AppCompatActivity {
    private BottomNavigationView bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_organizator);

        bottomAppBar = findViewById(R.id.bottom_navigation);

        // Set up the default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new JobInfoForTeamOrganizatorFragment())
                .commit();

        // Set the navigation listener
        bottomAppBar.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = menuItem -> {
        Fragment selectedFragment = null;

        switch (menuItem.getItemId()) {
            case R.id.icon_1:
                selectedFragment = new JobInfoForTeamOrganizatorFragment();
                break;
            case R.id.ic_TLbar_area_icon_te_le_ap_ba:
                selectedFragment = new AreaInfoForTeamOrganizerFragment();
                break;
            // Add the other icon cases here
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit();

        return true;
    };
}