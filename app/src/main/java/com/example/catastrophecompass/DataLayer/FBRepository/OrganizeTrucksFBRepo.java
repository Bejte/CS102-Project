package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

import com.example.catastrophecompass.DataLayer.Model.DriverItem;
import com.example.catastrophecompass.DataLayer.Model.InventoryList;
import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrganizeTrucksFBRepo {
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Logistics");
    public void getAvailableDrivers(OrganizeTrucksInterface interface, String organizationName)
    {
        ArrayList<DriverItem> drivers = new ArrayList<DriverItem>();
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot names: snapshot.getChildren())
                {
                    if(names.child("registeredOrganization").getValue().equals(targetOrgName) && names.child("status").getValue().equals("true"))
                    {
                        DriverItem driver = new DriverItem(names.getKey(), names.child("status").getValue(String.class));
                        drivers.add(driver);
                    }

                }
                interface.setDisplay(drivers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                interface.warnUser();
            }
        });

    }

    public boolean reassignGET(String driverName)
    {
        DatabaseReference driver = databaseRef.child(driverName);
        boolean[] deletionStatus = {false};

        String name  = driver.child("getName").getValue(String.class);
        driver.child("getName").setValue(null);
        driver.child("getAdress").setValue(null);

        DatabaseReference orgRef = FirebaseDatabase.getInstance().getReference("Organizations").child(name).child("arrivingTruckList").child(driverName);

        orgRef.removeValue()
            .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                deletionStatus[0] = true;
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    deletionStatus[0] = false;
                }
            });

        DatabaseReference reqRef = FirebaseDatabase.getInstance().getReference("Organizations").child(name).child("requests").child(driverName);
        reqRef.child("requestSize").setValue(driver.child("TruckSize"));
        reqRef.child("collected").setValue(driver.child("Inventory").child("list"));

        return deletionStatus[0];
    }

    public boolean reassignDrop(String driverName)
    {
        //TODO
        DatabaseReference driver = databaseRef.child(driverName);
        boolean[] status = new boolean[1];
        status[0] = false;
        String name = driver.child("dropName").getValue(String.class);
        driver.child("dropName").setValue(null);
        driver.child("dropAddress").setValue(null);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(name);

        int food = driver.child("Inventory").getValue().getFood();
        int heater = driver.child("Inventory").getValue().getHeater();
        int manCloth = driver.child("Inventory").getValue().getManCloth();
        int womanCloth = driver.child("Inventory").getValue().getWomanCloth();
        int childCloth = driver.child("Inventory").getValue().getChildCloth();
        int hygene = driver.child("Inventory").getValue().getHygene();
        int kitchenMaterial = driver.child("Inventory").getValue().getgetKitchenMaterial();
        int powerbank = driver.child("Inventory").getValue().getPowerbank();

        InventoryList list = new InventoryList(0, food, heater,manCloth, womanCloth, childCloth, hygene, kitchenMaterial, powerbank);
        ref.child("arrivingAid").setValue(list).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                status[0] = true;
                LogisticInfo driverItem = new LogisticInfo(driver.child("getName").getValue(String.class), driver.child("getAddress").getValue(String.class), driver.child("dropName").getValue(String.class), driver.child("dropAddress").getValue(String.class), driver.child("status").getValue(String.class), driver.child("pictureUrl").getValue(String.class));
                syncVectorDB(driverItem, ref.getKey().toString());
            }
        });

        return status[0];
    }

}
