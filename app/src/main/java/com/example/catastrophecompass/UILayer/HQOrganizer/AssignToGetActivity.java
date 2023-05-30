package com.example.catastrophecompass.UILayer.HQOrganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.PlaceItem;
import com.example.catastrophecompass.UILayer.Common.RecyclerViewAdapterForPlace;
import com.example.catastrophecompass.UILayer.HQOrganizer.Admin.AddEditOrganizationActivity;


import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class AssignToGetActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<PlaceItem> placeItems;
    private PlacesAvailableVM vm;
    private RecyclerViewAdapterForPlace recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_to_get);
        Button doneButton = findViewById(R.id.btn_doneButton_ac_as_to_ge_ac);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssignToGetActivity.this,  AddEditOrganizationActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.rec_JobTeamOrganizator_fr_jo_in_fo_te_or_fr);

        // Add sample data to the placeItems list
        placeItems = vm.getPlacesAvailable();



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }


}