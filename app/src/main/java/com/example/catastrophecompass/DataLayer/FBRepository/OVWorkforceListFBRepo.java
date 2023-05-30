package com.example.catastrophecompass.DataLayer.FBRepository;

import com.example.catastrophecompass.DataLayer.Model.WItemFB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class OVWorkforceListFBRepo {
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("PlacesAidWork");

    public List<WItemFB> getWItemList(String city) {
        CompletableFuture<List<WItemFB>> future = new CompletableFuture<>();

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<WItemFB> placesList = new ArrayList<>();

                for (DataSnapshot citySnapshot : dataSnapshot.getChildren()) {
                    if (city.equals(citySnapshot.getKey())) {
                        for (DataSnapshot placeSnapshot : citySnapshot.getChildren()) {
                            String place = placeSnapshot.getKey();
                            DataSnapshot placeDataSnapshot = placeSnapshot.child(place);
                            String location = placeDataSnapshot.child("location").getValue(String.class);
                            String address = placeDataSnapshot.child("address").getValue(String.class);
                            String mostUrgent = placeDataSnapshot.child("mostUrgent").getValue(String.class);
                            String workUrgency = placeDataSnapshot.child("workUrgency").getValue(String.class);

                            WItemFB wItem = new WItemFB(place, workUrgency, address, location);
                            placesList.add(wItem);
                        }
                        break;
                    }
                }

                future.complete(placesList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });

        try {
            return future.get(); // Wait for the future to complete and return the result
        } catch (Exception e) {
            // Handle any exceptions that occur
            e.printStackTrace();
            return new ArrayList<WItemFB>(); // Return an empty list if an exception occurs
        }
    }

}
