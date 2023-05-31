package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

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

        CityAdapter[] cityAdapter = new CityAdapter[1];
        List<String> cityList = viewModel.getCities();
        cityAdapter[0] = new CityAdapter(cityList, position -> {
            String cityName = cityAdapter[0].getCityList().get(position);
            showOptionsDialog(cityName);
        });
        recyclerViewCities.setAdapter(cityAdapter[0]);

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