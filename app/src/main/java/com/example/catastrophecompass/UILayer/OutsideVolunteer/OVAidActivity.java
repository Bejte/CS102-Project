package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.DataLayer.Model.InventoryList;
import com.example.catastrophecompass.DataLayer.Model.NeedItem;
import com.example.catastrophecompass.R;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OVAidActivity extends AppCompatActivity {
    private AidActivityVM viewModel;
    private RecyclerView recyclerView;
    private NeedItemAdapter needItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ovaid);

        recyclerView = findViewById(R.id.rec_needed_aid_ac_ov_ac);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(AidActivityVM.class);

        // Get the placeName from the Intent extras
        String placeName = getIntent().getStringExtra("aidPlaceName");

        onItemsListUpdated(viewModel.getItemList(placeName));
    }

    private void onItemsListUpdated(InventoryList inventoryList) {
        List<NeedItem> items = new ArrayList<>();
        items.add(new NeedItem("Food", findUrgency(inventoryList.getFood())));
        items.add(new NeedItem("Heater", findUrgency(inventoryList.getHeater())));
        items.add(new NeedItem("Man Cloth", findUrgency(inventoryList.getManCloth())));
        items.add(new NeedItem("Woman Cloth", findUrgency(inventoryList.getWomanCloth())));
        items.add(new NeedItem("Hygiene", findUrgency(inventoryList.getHygene())));
        items.add(new NeedItem("Kitchen Material", findUrgency(inventoryList.getKitchenMaterial())));
        items.add(new NeedItem("Powerbank", findUrgency(inventoryList.getPowerbank())));
        needItemAdapter = new NeedItemAdapter(items);
        recyclerView.setAdapter(needItemAdapter);

    }

    private String findUrgency(int n){
        if (n > 3)
            return "High";
        else if (n > 1)
            return "Medium";
        else
            return "Low";
    }
}