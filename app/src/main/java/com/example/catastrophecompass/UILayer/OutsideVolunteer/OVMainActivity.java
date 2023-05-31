package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.catastrophecompass.UILayer.Common.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class OVMainActivity extends AppCompatActivity {

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ovmain);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        sectionsPagerAdapter = new SectionsPagerAdapter(this);

        // Set up your fragments for the EighthActivity here
        Fragment workforceFragment = new OVWorkforceFragment();
        Fragment aidFragment = new OVAidFragment();

        // Add your fragments and their titles to the adapter
        sectionsPagerAdapter.addFragment(workforceFragment, "Workforce");
        sectionsPagerAdapter.addFragment(aidFragment, "Aid");

        viewPager.setAdapter(sectionsPagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Workforce");
                    break;
                case 1:
                    tab.setText("Aid");
                    break;
            }
        }).attach();

        int selectedTabIndex = getIntent().getIntExtra("selectedTabIndex", 0);
        viewPager.setCurrentItem(selectedTabIndex);
    }
}