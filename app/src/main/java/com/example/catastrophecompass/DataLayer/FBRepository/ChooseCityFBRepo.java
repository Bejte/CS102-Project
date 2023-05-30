package com.example.catastrophecompass.DataLayer.FBRepository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ChooseCityFBRepo
{
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("City");

    public ArrayList<String> getCities() {
        ArrayList<String>[] cities = new ArrayList[1];

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot citySnapshot : dataSnapshot.getChildren()) {
                    String city = citySnapshot.getKey();
                    cities[0].add(city);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return cities[0];
    }

}

