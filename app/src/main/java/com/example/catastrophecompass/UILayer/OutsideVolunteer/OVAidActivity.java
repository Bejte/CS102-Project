package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.AidActivityVM;
import com.example.catastrophecompass.UILayer.Common.NeedItem;
import com.example.catastrophecompass.UILayer.Common.NeedItemAdapter;

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
        needItemAdapter = new NeedItemAdapter(new ArrayList<>());
        recyclerView.setAdapter(needItemAdapter);

        viewModel = new ViewModelProvider(this).get(AidActivityVM.class);

        // Get the placeName from the Intent extras
        String placeName = getIntent().getStringExtra("aidPlaceName");

        // Observe itemsList LiveData to update the UI when new data arrives
        viewModel.getItemsList(placeName).observe(this, this::onItemsListUpdated);
    }

    private void onItemsListUpdated(List<NeedItem> items) {
        needItemAdapter = new NeedItemAdapter(items);
        recyclerView.setAdapter(needItemAdapter);
    }
}