package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

import com.example.catastrophecompass.DataLayer.Model.TruckRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.atomic.AtomicBoolean;

public class RecordAidRequestTruckFBRepo
{
    public boolean makeRequest(String organizationName, TruckRequest request)
    {
        boolean[] status = new boolean[1];
        status[0] = true;
        DatabaseReference orgRef = FirebaseDatabase.getInstance().getReference("Organizations").child(organizationName);
        DatabaseReference requestsRef = orgRef.child("requests");
        requestsRef.child("totalRequested").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer totalRequested = dataSnapshot.getValue(Integer.class);
                if (totalRequested == null) {
                    totalRequested = 1;
                } else {
                    totalRequested += 1;
                }
                requestsRef.child("totalRequested").setValue(totalRequested);

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

        String truckReqKey = requestsRef.push().getKey();
        requestsRef.child(truckReqKey).child("size").setValue(request.getSize(), (error, ref) -> {
            if(error != null)
            {
                status[0] = false;
            }
        });
        requestsRef.child(truckReqKey).child("Collections").setValue(request.getInventoryList(), (error, ref) -> {
            if(error != null)
            {
                status[0] = false;
            }
        });

        return status[0];
    }
}
