package com.example.catastrophecompass.UILayer.VolunteerInBase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;



 import com.example.myapplication.SectionsPagerAdapter;
 import android.os.Bundle;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.fragment.app.FragmentActivity;
 import androidx.viewpager2.widget.ViewPager2;
 import com.google.android.material.tabs.TabLayout;
 import com.google.android.material.tabs.TabLayoutMediator;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.viewpager2.widget.ViewPager2;
 import android.os.Bundle;
 import com.google.android.material.tabs.TabLayout;
 import com.google.android.material.tabs.TabLayoutMediator;

 public class FourthActivity extends AppCompatActivity {

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