package com.example.catastrophecompass.UILayer.HQOrganizer;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.Chat.ChatActivity;
import com.example.catastrophecompass.UILayer.Common.Chat.PeopleFragment;
import com.example.catastrophecompass.UILayer.OutsideVolunteer.OVMainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HQOrganizerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.app_bar_hq);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.fived_bar_job_icon:
                        selectedFragment = new OrganizeTrucksFragment();
                        break;

                    case R.id.fived_bar_chat_icon:
                        Intent chatIntent = new Intent(getApplicationContext(), ChatActivity.class);
                        startActivity(chatIntent);
                        return true;

                    case R.id.fived_bar_people_icon:
                        selectedFragment = new PeopleFragment();
                        break;

                    case R.id.fived_bar_fived_icon:
                        Intent ovIntent = new Intent(getApplicationContext(), OVMainActivity.class);
                        startActivity(ovIntent);
                        return true;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_hq, selectedFragment);
                transaction.commit();

                return true;
            }
        });
    }
}