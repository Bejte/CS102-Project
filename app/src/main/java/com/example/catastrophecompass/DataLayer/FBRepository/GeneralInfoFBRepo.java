package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

import com.example.catastrophecompass.DataLayer.Model.OrganizationAsNode;
import com.example.catastrophecompass.DataLayer.Model.VolunteerInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GeneralInfoFBRepo
{
    List<VolunteerInfo> volunteerInfoList = new ArrayList<>();
    public VolunteerInfo getGeneralInformation(String organizationName) {
        DatabaseReference organizationRef = FirebaseDatabase.getInstance().getReference("OrganizationStructure").child(organizationName);
        VolunteerInfo[] all = new VolunteerInfo[1];

        organizationRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<DataSnapshot> teams = new ArrayList<>();
                VolunteerInfo volunteerInfo = new VolunteerInfo();

                traverseOrganizationStructure(dataSnapshot, teams, volunteerInfo);

                all[0] = new VolunteerInfo(0,0,0,0,0);
                for(VolunteerInfo info : volunteerInfoList)
                {
                    all[0].setNeed(all[0].getNeed()+info.getNeed());
                    all[0].setCrucial(all[0].getCrucial()+info.getCrucial());
                    all[0].setHelpful(all[0].getHelpful()+info.getHelpful());
                    all[0].setUnnecessary(all[0].getUnnecessary()+info.getUnnecessary());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return all[0];
    }

    private void traverseOrganizationStructure(DataSnapshot node, List<DataSnapshot> teams, VolunteerInfo volunteerInfo) {
        if (node.child("Type").getValue(String.class).equals("team")) {
            teams.add(node);
            fetchTeamInformation(node, volunteerInfo);
        } else if (node.hasChild("downList")) {
            DataSnapshot downListSnapshot = node.child("downList");
            for (DataSnapshot subOrgSnapshot : downListSnapshot.getChildren()) {
                traverseOrganizationStructure(subOrgSnapshot, teams, volunteerInfo);
            }
        }
    }

    private void fetchTeamInformation(DataSnapshot teamSnapshot, VolunteerInfo volunteerInfo) {
        String teamName = teamSnapshot.getKey();

        DatabaseReference teamRef = FirebaseDatabase.getInstance().getReference("Teams")
                .child(teamSnapshot.child("city").getValue(String.class))
                .child(teamSnapshot.child("place").getValue(String.class))
                .child(teamName);

        teamRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    VolunteerInfo volunteerInfo = dataSnapshot.child("volunteerInfo").getValue(VolunteerInfo.class);

                    volunteerInfoList.add(volunteerInfo);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public List<OrganizationAsNode> getSubOrganizations(String organizationName)
    {
        DatabaseReference organizationRef = FirebaseDatabase.getInstance().getReference("OrganizationStructure");
        DataSnapshot[] organizationStructureNode = new DataSnapshot[1];

        organizationRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    if (snapshot.getKey().equals(organizationName)) {
                        organizationStructureNode[0] = s;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        List<OrganizationAsNode> subOrganizations = new ArrayList<>();

        if (organizationStructureNode[0].hasChild("downList")) {
            DataSnapshot downListNode = organizationStructureNode[0].child("downList");
            for (DataSnapshot subOrgSnapshot : downListNode.getChildren()) {
                String subOrgName = subOrgSnapshot.getKey();
                String type = subOrgSnapshot.child("Type").getValue(String.class);
                OrganizationAsNode org = new OrganizationAsNode(subOrgName, type);
                subOrganizations.add(org);
            }
        }

        return subOrganizations;
    }
}


