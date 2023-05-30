package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.CityAdapter;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
public class OVFirstActivity extends AppCompatActivity {

    private ChooseCityVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ovfirst);



        RecyclerView recyclerViewCities = findViewById(R.id.rec_cities_ac_ov_ac);
        recyclerViewCities.setHasFixedSize(true);
        recyclerViewCities.setLayoutManager(new LinearLayoutManager(this));

        CityAdapter cityAdapter = new CityAdapter(new ArrayList<>(), position -> {
            String cityName = cityAdapter.getCityNameAt(position);
            showOptionsDialog(cityName);
        });
        recyclerViewCities.setAdapter(cityAdapter);

        viewModel.getCities().observe(this, cityNames -> {
            // Update the adapter with the new city names
            cityAdapter.updateCityNames(cityNames);
        });
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







}