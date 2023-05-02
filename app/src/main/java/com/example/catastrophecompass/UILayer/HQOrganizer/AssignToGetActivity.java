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
        placeItems = new ArrayList<>();
        // Add PlaceItems to the list
        placeItems.add(new PlaceItem("Place 1", "Address 1"));
        placeItems.add(new PlaceItem("Place 2", "Address 2"));
        // ...

        recyclerViewAdapter = new RecyclerViewAdapterForPlace(this, placeItems, new RecyclerViewAdapterForPlace.OnPlaceItemClickListener() {
            @Override
            public void onPlaceItemClick(int position) {
                showConfirmationDialog(position);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void showConfirmationDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Open your Third Activity here
                ///Intent intent = new Intent(HQPlacesForTrucksActivity.this, EditOrganizatorActivity.class);
                //startActivity(intent);
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}