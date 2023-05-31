package com.example.catastrophecompass.DataLayer.FBRepository;

import android.content.ClipData;

import com.example.catastrophecompass.DataLayer.Model.InventoryList;
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

    public List<InventoryList> getItemList(String place)
    {
        ArrayList<InventoryList>[] itemList = new ArrayList[1];
        databaseRef.child(place).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {

                        String key = itemSnapshot.getKey();
                        int value = itemSnapshot.getValue(Integer.class);

                        int food = 0;
                        int childCloth = 0;
                        int womenCloth = 0;
                        int menCloth = 0;
                        int powerbank = 0;
                        int heater = 0;
                        int hygiene = 0;
                        int kitchenMaterial = 0;

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

                        InventoryList Item = new InventoryList(0, food, heater, menCloth, womenCloth, childCloth, hygiene, kitchenMaterial, powerbank);
                        itemList[0].add(Item);
                    }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return itemList[0];
    }
}
