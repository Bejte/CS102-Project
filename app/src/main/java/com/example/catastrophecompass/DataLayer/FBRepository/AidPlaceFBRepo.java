package com.example.catastrophecompass.DataLayer.FBRepository;

import android.content.ClipData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AidPlaceFBRepo
{
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("AidList");

    public List<ClipData.Item> getItemList(String place)
    {
        CompletableFuture<List<ClipData.Item>> future = new CompletableFuture<>();

        databaseRef.child(place).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ClipData.Item> placesList = new ArrayList<>();

                    for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {

                        String key = itemSnapshot.getKey();
                        String value = itemSnapshot.getValue(String.class);

                        String food = null;
                        String childCloth = null;
                        String womenCloth = null;
                        String menCloth = null;
                        String powerbank = null;
                        String heater = null;
                        String hygiene = null;
                        String kitchenMaterial = null;

                        if(key.equals("food"))
                            food = value;
                        if(key.equals("childCloth"))
                            childCloth = value;
                        if(key.equals("womenCloth"))
                            womenCloth = value;
                        if(key.equals("menCloth"))
                            menCloth = value;
                        if(key.equals("powerbank"))
                            powerbank = value;
                        if(key.equals("heater"))
                            heater = value;
                        if(key.equals("hygiene"))
                            hygiene = value;
                        if(key.equals("kitchenMaterial"))
                            kitchenMaterial = value;

                        ClipData.Item Item = new Item(food, childCloth, womenCloth, menCloth, powerbank, heater, hygiene, kitchenMaterial);
                        placesList.add(Item);
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
            return new ArrayList<ClipData.Item>(); // Return an empty list if an exception occurs
        }
    }
}
