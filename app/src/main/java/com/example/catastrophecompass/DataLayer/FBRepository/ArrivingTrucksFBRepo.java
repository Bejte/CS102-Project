package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catastrophecompass.DataLayer.Model.TruckItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ArrivingTrucksFBRepo
{
    public List<TruckItem> getArrivingTrucks(String organizationName) throws NullPointerException
    {
        List<TruckItem>[] trucks = new ArrayList[1];

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Organizations").child(organizationName).child("arrivingTruckList");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot truckSnapshot : dataSnapshot.getChildren()) {
                    TruckItem truck = new TruckItem(truckSnapshot.getKey(), truckSnapshot.child("size").getValue(Integer.class));
                    trucks[0].add(truck);
                }
    }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return trucks[0];
    }
}
