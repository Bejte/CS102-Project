package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

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

        driver.child("getStatus").setValue(null);
        driver.child("getAdress").setValue(null);
        String name  = driver.child("getName").getValue(String.class);

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
        reqRef.child("collected").setValue(driver.child("Inevtory").child("list"));

        return deletionStatus[0];
    }

    public boolean reassignDrop(String driverName)
    {
        //TODO
        DatabaseReference driver = databaseRef.child(driverName);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("FieldOrganizations").child(driver.child("dropName").getValue(String.class).child("ArrivingAid"));

    }

}
