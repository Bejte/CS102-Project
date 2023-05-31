package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

import com.example.catastrophecompass.DataLayer.Model.InventoryList;
import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;
import com.example.catastrophecompass.DataLayer.Model.Request;
import com.example.catastrophecompass.UILayer.HQOrganizer.PlacesAvailableInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PlacesAvailableFBRepo
{
    public void getPlacesAvailable(String organizationName, PlacesAvailableInterface placesAvailableInterface)
    {
        DatabaseReference organizationsRef = FirebaseDatabase.getInstance().getReference("Organizations");
        DatabaseReference structureRef = FirebaseDatabase.getInstance().getReference("OrganizationStructure");
        DataSnapshot[] targetSnapshot = new DataSnapshot[1];
        List<DataSnapshot> downOrgs = new ArrayList<>();
        List<Request> requests = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("OrganizationStructure").child("BaseOrganization").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                searchOrganization(organizationName, snapshot, targetSnapshot[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        traverseOrganizationStructure(targetSnapshot[0], downOrgs);
        for(DataSnapshot orgs : downOrgs)
        {
            if(orgs.child("orgType").getValue().equals("team organization") && orgs.child("requests").getChildrenCount()>1)
            {
                for(DataSnapshot req : orgs.child("requests").getChildren())
                {
                    if(!req.getKey().equals("totalRequested"))
                    {
                        Request request = new Request(req.getKey(), orgs.getKey(), req.child("requestSize").getValue(Integer.class), req.child("collected").getValue(InventoryList.class));
                        requests.add(request);
                    }
                }
            }
        }

        placesAvailableInterface.setDisplay(requests);
    }

    private void traverseOrganizationStructure(DataSnapshot node, List<DataSnapshot> downOrgs) {
        if (node.hasChild("downList")) {
            DataSnapshot downListSnapshot = node.child("downList");
            for (DataSnapshot subOrgSnapshot : downListSnapshot.getChildren()) {
                downOrgs.add(subOrgSnapshot);
                traverseOrganizationStructure(subOrgSnapshot, downOrgs);
            }
        }
    }


    private void searchOrganization(String targetName, DataSnapshot parentNode, DataSnapshot targetRef)
    {
        if(parentNode.hasChild(targetName))
        {
            targetRef = parentNode.child(targetName);
        }
        else{
            for(DataSnapshot snapshot: parentNode.getChildren())
            {
                searchOrganization(targetName, snapshot, targetRef);
            }
        }
    }

    public void assign(LogisticInfo info, Request request, PlacesAvailableInterface placesAvailableInterface)
    {
        DatabaseReference logisticsRef = FirebaseDatabase.getInstance().getReference("Logistics").child();// TODO driverName add common
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
                logisticsRef.child("dropStatus").setValue(false).addOnFailureListener(new OnFailureListener() {
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

        FirebaseDatabase.getInstance().getReference("Organizations").child(request.getPlaceName()).child("requests").child(request.getRequestName()).removeValue();

    }
}
