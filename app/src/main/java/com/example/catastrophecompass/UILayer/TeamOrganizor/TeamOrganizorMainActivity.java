package com.example.catastrophecompass.UILayer.TeamOrganizor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.catastrophecompass.DataLayer.Model.Team;
import com.example.catastrophecompass.DataLayer.Model.TeamOrganizator;
import com.example.catastrophecompass.R;


/**
 * Attach GeneralInfoFragment, AreaFragment, PeopleFragment, LogisticInfoFragment & ChatFragment
 */

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.catastrophecompass.UILayer.Common.AreaInfoFragment;
import com.example.catastrophecompass.UILayer.Common.Chat.ChatActivity;
import com.example.catastrophecompass.UILayer.Common.Chat.PeopleFragment;
import com.example.catastrophecompass.UILayer.Common.GeneralInfoFragment;
import com.example.catastrophecompass.UILayer.OutsideVolunteer.OVMainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.reactivex.rxjava3.annotations.NonNull;


public class TeamOrganizorMainActivity extends AppCompatActivity {
    Button btnAddOrganization;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general_info, container, false);

        // find the add_organization_button_admin button from the view
        btnAddOrganization = view.findViewById(R.id.add_organization_button_admin);

        // set a click listener for the button
        btnAddOrganization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create an intent to open the activity
                Intent intent = new Intent(TeamOrganizorMainActivity.this, TeamOrganizorAddEditActivity.class);

                // start the activity
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_organizor_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.app_bar_main_to);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.fived_bar_job_icon:
                        openFragment(new GeneralInfoFragment());
                        return true;
                    case R.id.fived_bar_area_icon:
                        openFragment(new AreaInfoFragment());
                        return true;
                    case R.id.fived_bar_chat_icon:
                        startActivity(new Intent(TeamOrganizorMainActivity.this, ChatActivity.class));
                        return true;
                    case R.id.fived_bar_people_icon:
                        openFragment(new PeopleFragment());
                        return true;
                    case R.id.fived_bar_fived_icon:
                        startActivity(new Intent(TeamOrganizorMainActivity.this, ArrivingTrucksFragment.class));
                        return true;
                }
                return false;
            }
        });
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}