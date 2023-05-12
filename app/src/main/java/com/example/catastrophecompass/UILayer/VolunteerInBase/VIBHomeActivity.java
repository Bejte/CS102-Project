package com.example.catastrophecompass.UILayer.VolunteerInBase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;


import com.example.catastrophecompass.UILayer.Common.TabsPagerAdapter;

import androidx.viewpager2.widget.ViewPager2;
 import com.google.android.material.tabs.TabLayout;
 import com.google.android.material.tabs.TabLayoutMediator;

public class VIBHomeActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_vibhome);

TabLayout tabLayout = findViewById(R.id.tab_layout);
ViewPager2 viewPager = findViewById(R.id.view_pager);

viewPager.setAdapter(new TabsPagerAdapter(this));

new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
if (position == 0) {
tab.setText("Job Info");
} else {
tab.setText("Area Info");
}
}).attach();
}
}