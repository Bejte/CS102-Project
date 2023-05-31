package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

import com.example.catastrophecompass.DataLayer.Model.OrganizationAsNode;
import com.example.catastrophecompass.DataLayer.Model.TeamOrganization;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddOrganizationFBRepo
{
    public List<OrganizationAsNode> getBuildsDownList(String baseOrganization)
    {
        List<DataSnapshot> downOrgs = new ArrayList<>();
        List<OrganizationAsNode> organizations = new ArrayList<>();
        DatabaseReference orgNode = FirebaseDatabase.getInstance().getReference("OrganizationStructure");
        DataSnapshot[] targetRef = new DataSnapshot[1];
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Organizations");
        orgNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                searchOrganization(baseOrganization, snapshot, targetRef[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        traverseOrganizationStructure(targetRef[0], downOrgs);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot org : downOrgs)
                {
                    OrganizationAsNode organization = new OrganizationAsNode(org.getKey().toString(), snapshot.child(org.getKey().toString()).child("orgType").getValue(String.class));
                    organizations.add(organization);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return organizations;
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

    public boolean createFieldOrganization(OrganizationAsNode org, String lastClicked)
    {
        boolean[] status = {true};

        FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(org.getName()).child("demographic").setValue(0).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });
        FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(org.getName()).child("housing").setValue(0).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });
        FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(org.getName()).child("currentInventory").setValue(0).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });
        FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(org.getName()).child("arrivingAid").setValue(0).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        FirebaseDatabase.getInstance().getReference("FiedlOrganizations").child(org.getName()).child("organizatorsList").setValue(0).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(org.getName()).child("adress").setValue(lastClicked).addOnFailureListener(new OnFailureListener() {//?
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        FirebaseDatabase.getInstance().getReference("Organizations").child(org.getName()).child("organizersList").setValue(0).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        FirebaseDatabase.getInstance().getReference("Organizations").child(org.getName()).child("orgType").setValue("FO").addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        DataSnapshot[] upOrg = new DataSnapshot[1];
        FirebaseDatabase.getInstance().getReference("OrganizationStructure").child("BaseOrganization").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                searchOrganization(lastClicked, snapshot, upOrg[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        upOrg[0].getRef().child(org.getName()).child("downList").setValue(0).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });
        upOrg[0].getRef().child(org.getName()).child("orgType").setValue("FO").addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        return status[0];
    }

    public boolean createHQOrganization(OrganizationAsNode org, String lastClicked)
    {
        boolean[] status = {true};

        FirebaseDatabase.getInstance().getReference("Organizations").child(org.getName()).child("organizersList").setValue(0).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        FirebaseDatabase.getInstance().getReference("Organizations").child(org.getName()).child("orgType").setValue("HQ").addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        DataSnapshot[] upOrg = new DataSnapshot[1];
        FirebaseDatabase.getInstance().getReference("OrganizationStructure").child("BaseOrganization").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                searchOrganization(lastClicked, snapshot, upOrg[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        upOrg[0].getRef().child(org.getName()).child("downList").setValue(0).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });
        upOrg[0].getRef().child(org.getName()).child("orgType").setValue("HQ").addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        return status[0];
    }

    public boolean createTeamOrganization(TeamOrganization org, String lastClicked)
    {
        boolean[] status = {true};

        FirebaseDatabase.getInstance().getReference("Organizations").child(org.getName()).child("organizersList").setValue(0).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        FirebaseDatabase.getInstance().getReference("Organizations").child(org.getName()).child("orgType").setValue("TO").addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        FirebaseDatabase.getInstance().getReference("Organizations").child(org.getName()).child("city").setValue(org.getCity()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        FirebaseDatabase.getInstance().getReference("Organizations").child(org.getName()).child("address").setValue(org).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        DataSnapshot[] upOrg = new DataSnapshot[1];
        FirebaseDatabase.getInstance().getReference("OrganizationStructure").child("BaseOrganization").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                searchOrganization(lastClicked, snapshot, upOrg[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        upOrg[0].getRef().child(org.getName()).child("downList").setValue(0).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });
        upOrg[0].getRef().child(org.getName()).child("orgType").setValue("TO").addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        FirebaseDatabase.getInstance().getReference("Teams").child(org.getCity()).child(org.getName()).setValue(0).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                status[0] = false;
            }
        });

        return status[0];
    }

}
