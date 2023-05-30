package com.example.catastrophecompass.DataLayer.FBRepository;

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


        DataSnapshot organizationSnapshot = organizationRef.getValue();
        if (organizationSnapshot == null) {
            return null;
        }

        List<DataSnapshot> teams = new ArrayList<>();
        VolunteerInfo volunteerInfo = new VolunteerInfo();

        traverseOrganizationStructure(organizationSnapshot, teams, volunteerInfo);

        if (teams.isEmpty()) {
            return volunteerInfo;
        }

        VolunteerInfo all = new VolunteerInfo(0,0,0,0);
        for(VolunteerInfo info : volunteerInfoList)
        {
            all.setNeed(all.getNeed()+info.getNeed());
            all.setCrucial(all.getCrucial()+info.getCrucial());
            all.setHelpful(all.getHelpful()+info.getHelpful());
            all.setUnnecessary(all.getUnnecessary()+info.getUnnecessary());
        }

        return all;
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

        // Assuming the team information is stored under the "Teams" node
        DatabaseReference teamRef = FirebaseDatabase.getInstance().getReference("Teams")
                .child(teamSnapshot.child("city").getValue(String.class))
                .child(teamSnapshot.child("place").getValue(String.class))
                .child(teamName);

        teamRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    VolunteerInfo volunteerInfo = dataSnapshot.child("volunteerInfo").getValue();

                    volunteerInfoList.add(volunteerInfo);

                } else {

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
        DataSnapshot organizationStructureNode = null;

        for (DataSnapshot snapshot : organizationRef.getChildren()) {
            if (snapshot.getKey().equals(organizationName)) {
                organizationStructureNode = snapshot;
                break;
            }
        }

        List<OrganizationAsNode> subOrganizations = new ArrayList<>();

        if (organizationStructureNode.hasChild("downList")) {
            DataSnapshot downListNode = organizationStructureNode.child("downList");
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


