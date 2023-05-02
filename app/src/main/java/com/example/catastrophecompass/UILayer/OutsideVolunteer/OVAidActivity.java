package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TenthActivity extends AppCompatActivity {

    private RecyclerView needsRecyclerView;
    private NeedItemAdapter needItemAdapter;
    private List<NeedItem> needItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ovaid);

        needItemList = new ArrayList<>();
        // Add sample data
        needItemList.add(new NeedItem("Type of need 1", "High"));
        needItemList.add(new NeedItem("Type of need 2", "Medium"));
        needItemList.add(new NeedItem("Type of need 3", "Low"));

        needsRecyclerView = findViewById(R.id.rec_needed_aid_ac_ov_ac);
        needItemAdapter = new NeedItemAdapter(needItemList);
        needsRecyclerView.setAdapter(needItemAdapter);
    }
}
