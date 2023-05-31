package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

import com.example.catastrophecompass.DataLayer.LocalRepository.LogisticMissionLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.InventoryList;
import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;
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
        LogisticInfo[] info = new LogisticInfo[1];

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String getName = snapshot.child("getName").getValue(String.class);
                String getAddress = snapshot.child("getAddress").getValue(String.class);
                String dropName = snapshot.child("dropName").getValue(String.class);
                String dropAddress = snapshot.child("dropAddress").getValue(String.class);
                String status = snapshot.child("status").getValue(String.class);
                String pictureUrl = snapshot.child("pictureURL").getValue(String.class);
                boolean getStatus = snapshot.child("getStatus").getValue(boolean.class);
                boolean dropStatus = snapshot.child("dropStatus").getValue(boolean.class);
                InventoryList inventoryList = snapshot.child("Inventory").getValue(InventoryList.class);
                int truckSize = snapshot.child("TruckSize").getValue(Integer.class);
                info[0] = new LogisticInfo(getName, getAddress, dropName, dropAddress, status, pictureUrl, getStatus, dropStatus, inventoryList, truckSize);
                localRepo.pushToLocal(info[0]);
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
                dataSnapshot.child("getStatus").getRef().setValue(true);
                dataSnapshot.child("status").getRef().setValue("getChecked");
                updateStatus[0] = true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                updateStatus[0] = false;
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
                dataSnapshot.child("dropStatus").getRef().setValue(true);
                dataSnapshot.child("status").getRef().setValue("dropClicked");
                updateStatus[0] = true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                updateStatus[0] = false;
            }
        });

        return updateStatus[0];
    }
}
