package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.CityAdapter;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
public class OVFirstActivity extends AppCompatActivity {

    private List<String> cityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ovfirst);

        RecyclerView recyclerViewCities = findViewById(R.id.rec_cities_ac_ov_ac);
        recyclerViewCities.setHasFixedSize(true);
        recyclerViewCities.setLayoutManager(new LinearLayoutManager(this));

        List<String> cityList = new ArrayList<>();
        // Add city names to the cityList
        cityList.add("City 1");
        cityList.add("City 2");
        cityList.add("City 3");
        cityList.add("City 4");
        cityList.add("City 5");
        cityList.add("City 1");
        cityList.add("City 2");
        cityList.add("City 3");
        cityList.add("City 4");
        cityList.add("City 5");
        cityList.add("City 1");
        cityList.add("City 2");
        cityList.add("City 3");
        cityList.add("City 4");
        cityList.add("City 5");


        // ... add more city names as needed

        CityAdapter cityAdapter = new CityAdapter(cityList, new CityAdapter.OnCityItemClickListener() {
            @Override
            public void onCityItemClick(int position) {
                String cityName = cityList.get(position);
                showOptionsDialog(cityName);
            }
        });

        recyclerViewCities.setAdapter(cityAdapter);
    }

    private void showOptionsDialog(String cityName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Options for " + cityName);

        // Define the options
        final CharSequence[] options = new CharSequence[]{
                "See places in need of workforce",
                "See places in need of aid"
        };

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(OVFirstActivity.this, OVMainActivity.class);
                intent.putExtra("cityName", cityName);

                switch (which) {
                    case 0: // "See places in need of workforce"
                        intent.putExtra("selectedTabIndex", 0);
                        break;
                    case 1: // "See places in need of aid"
                        intent.putExtra("selectedTabIndex", 1);
                        break;
                }
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }






    private void initializeCityList() {
        cityList.add("City 1");
        cityList.add("City 2");
        cityList.add("City 3");
        cityList.add("City 4");
        cityList.add("City 5");
        // Add more city names as needed
    }
}