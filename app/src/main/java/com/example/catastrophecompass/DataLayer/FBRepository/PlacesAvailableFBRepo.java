package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;
import com.example.catastrophecompass.DataLayer.Model.Request;

public class PlacesAvailableFBRepo
{
    public void getPlacesAvailable(LogisticInfo info, PlacesAvailable placesAvailableInterface)
    {
        //TODO
    }

    public void assign(LogisticInfo info, Request request, PlacesAvailable placesAvailableInterface)
    {
        DatabaseReference logisticsRef = FirebaseDatabase.getInstance().getReference("Logistics").child(info.getName());
        boolean[] success = {true};

        logisticsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                logisticsRef.child("status").setValue("assigned").addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        success[0] = false;
                    }
                });
                logisticsRef.child("getStatus").setValue(false).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        success[0] = false;
                    }
                });
                logisticsRef.child("Inventory").setValue(request.getInventoryList()).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        success[0] = false;
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference orgRef = FirebaseDatabase.getInstance().getReference("Organizations").child(request.getPlaceName()).child("requests").child(request.getRequestName()).removeValue();

    }
}
