package com.example.catastrophecompass.UILayer.Logistics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;


/**
 * Attach MissionFragment, ChatFragment, PeopleFragment
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.catastrophecompass.UILayer.Common.Chat.ChatActivity;
import com.example.catastrophecompass.UILayer.Common.Chat.PeopleFragment;
import com.example.catastrophecompass.UILayer.OutsideVolunteer.OVMainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LogisticsMainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics_main);

        bottomNavigationView = findViewById(R.id.app_bar_logistic);
        bottomNavigationView.setOnItemSelectedListener(navListener);



        //Open the first fragment initially
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_logistic, new MissionFragment())
                    .commit();
        }
    }

    private BottomNavigationView.OnItemSelectedListener navListener = new BottomNavigationView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.fouredsecond_bar_job_icon:
                    selectedFragment = new MissionFragment();
                    break;
                case R.id.fouredsecond_bar_chat_icon:
                    Intent intent = new Intent(LogisticsMainActivity.this, ChatActivity.class);
                    startActivity(intent);
                    return true; //return here since we don't need to replace the fragment
                case R.id.fouredsecond_bar_people_icon:
                    selectedFragment = new PeopleFragment();
                    break;
                case R.id.fouredsecond_bar_area_icon:
                    Intent intentOV = new Intent(LogisticsMainActivity.this, OVMainActivity.class);
                    startActivity(intentOV);
                    return true; //return here since we don't need to replace the fragment
            }
            //Switch the fragment
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_logistic, selectedFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
            return true;
        }
    };
}