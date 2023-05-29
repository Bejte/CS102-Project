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

    public CompletableFuture<ArrayList<String>> getCitiesCompletable() {
        CompletableFuture<ArrayList<String>> future = new CompletableFuture<>();

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> cities = new ArrayList<>();

                for (DataSnapshot citySnapshot : dataSnapshot.getChildren()) {
                    String city = citySnapshot.getKey();
                    cities.add(city);
                }

                future.complete(cities);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });

        return future;
    }

    public ArrayList<String> getCities() throws ExecutionException, InterruptedException {
        CompletableFuture<ArrayList<String>> citiesArray = getCitiesCompletable();
        ArrayList<String> cities = citiesArray.get();

        return cities;
    }


}

