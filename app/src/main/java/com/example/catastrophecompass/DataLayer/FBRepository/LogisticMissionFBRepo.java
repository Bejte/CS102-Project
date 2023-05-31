package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogisticMissionFBRepo
{
    LogisticMissionLocalRepo localRepo;

    public LogisticMissionFBRepo(LogisticMissionLocalRepo localRepo) {
        this.localRepo = localRepo;
    }

    public void getLogisticInfo(String driverName)
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Logistics").child(driverName);
        String getName = databaseReference.child("getName").getValue(String.class);
        String getAddress = databaseReference.child("getAddress").getValue(String.class);
        String dropName = databaseReference.child("dropName").getValue(String.class);
        String dropAddress = databaseReference.child("dropAddress").getValue(String.class);
        String status = databaseReference.child("status").getValue(String.class);
        String pictureUrl = databaseReference.child("pictureURL").getValue(String.class);
        boolean getStatus = databaseReference.child("getStatus").getValue(boolean.class);
        boolean dropStatus = databaseReference.child("dropStatus").getValue(boolean.class);
        InventoryList inventoryList = databaseReference.child("Inventory").getValue() ;
        LogisticInfo[] info = {new LogisticInfo(getName, getAddress, dropName, dropAddress, status, pictureUrl, getStatus, dropStatus, inventoryList)};

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String getName = databaseReference.child("getName").getValue(String.class);
                String getAddress = databaseReference.child("getAddress").getValue(String.class);
                String dropName = databaseReference.child("dropName").getValue(String.class);
                String dropAddress = databaseReference.child("dropAddress").getValue(String.class);
                String status = databaseReference.child("status").getValue(String.class);
                String pictureUrl = databaseReference.child("pictureURL").getValue(String.class);
                boolean getStatus = databaseReference.child("getStatus").getValue(boolean.class);
                boolean dropStatus = databaseReference.child("dropStatus").getValue(boolean.class);
                InventoryList inventoryList = databaseReference.child("Inventory").getValue() ;
                info[0] = new LogisticInfo(getName, getAddress, dropName, dropAddress, status, pictureUrl, getStatus, dropStatus, inventoryList);
                localRepo.pushToLocal(info);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public boolean getChecked(String driverName)
    {
        //status : available, getChecked, dropClicked, assigned
        boolean[] updateStatus = {false};

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Logistics").child(driverName);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.child("getStatus").setValue(true);
                dataSnapshot.child("status").setValue("getChecked");
                updateStatus[0] = true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return updateStatus[0];
    }

    public boolean dropClicked(String driverName)
    {
        //status : available, getChecked, dropClicked, assigned
        boolean[] updateStatus = {false};

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Logistics").child(driverName);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.child("dropStatus").setValue(true);
                dataSnapshot.child("status").setValue("dropClicked");
                updateStatus[0] = true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return updateStatus[0];
    }
}
