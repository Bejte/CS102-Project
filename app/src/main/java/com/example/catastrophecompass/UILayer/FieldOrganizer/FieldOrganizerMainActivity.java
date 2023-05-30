package com.example.catastrophecompass.UILayer.FieldOrganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.catastrophecompass.R;

/**
 * Attach DemographicStatusFragment, AidStatusFragment, ChatFragment, ChatPeopleFragment.
 */

import android.content.Intent;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.catastrophecompass.UILayer.Common.Chat.ChatActivity;
import com.example.catastrophecompass.UILayer.Common.Chat.PeopleFragment;
import com.example.catastrophecompass.UILayer.OutsideVolunteer.OVMainActivity;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FieldOrganizerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_organizor_main);

        BottomNavigationView bottomNav = findViewById(R.id.app_bar_logistic);
        bottomNav.setOnItemSelectedListener(navListener);

        // Start with the first fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_field_organizer, new DemographicStatusFragment()).commit();
    }

    private BottomNavigationView.OnItemSelectedListener navListener =
            new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.fieldorg_bar_demog_icon:
                            selectedFragment = new DemographicStatusFragment();
                            break;
                        case R.id.fieldorg_bar_aid_icon:
                            Intent intent = new Intent(FieldOrganizerMainActivity.this, AidStatusActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.fieldorg_bar_chat_icon:
                            intent = new Intent(FieldOrganizerMainActivity.this, ChatActivity.class);
                            startActivity(intent); // Use Fragment here
                            break;
                        case R.id.fieldorg_bar_people_icon:
                            selectedFragment = new PeopleFragment();
                            break;
                        case R.id.fieldorg_bar_help_icon:
                          intent = new Intent(FieldOrganizerMainActivity.this, OVMainActivity.class);
                            startActivity(intent);
                            return true;
                    }

                    // If selectedFragment is not null, replace the fragment
                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_field_organizer, selectedFragment).commit();
                    }

                    return true;
                }
            };
}