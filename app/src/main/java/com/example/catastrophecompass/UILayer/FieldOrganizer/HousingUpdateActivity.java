package com.example.catastrophecompass.UILayer.FieldOrganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.HousingItemAdapter;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class HousingUpdateActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HousingItemAdapter adapter;
    private List<HousingItem> housingItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_update);


        // Add your housing item data here (e.g., housingItems.add(new HousingItem("Title", "Default value"));)
        housingItems = new ArrayList<>();
        housingItems.add(new HousingItem("Title 1", "Default value 1"));
        housingItems.add(new HousingItem("Title 2", "Default value 2"));
        housingItems.add(new HousingItem("Title 3", "Default value 3"));
        recyclerView = findViewById(R.id.rec_chat_ac);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new HousingItemAdapter(housingItems);
        recyclerView.setAdapter(adapter);
        Log.d("UpdateHousingActivity", "Adapter set, item count: " + housingItems.size());
    }
}