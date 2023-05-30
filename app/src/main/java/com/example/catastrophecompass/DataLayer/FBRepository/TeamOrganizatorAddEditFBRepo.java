package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

import com.example.catastrophecompass.DataLayer.Model.TeamInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeamOrganizatorAddEditFBRepo
{

    public void addTeam(String city, String place, TeamInfo team)
    {
        DatabaseReference userListRef = FirebaseDatabase.getInstance().getReference("UserList");
        userListRef.child(team.getTeamLeaderName()).child("organizationName").setValue(city + ", " + place + ", " + team.getTeamName());
        DatabaseReference teamsRef = FirebaseDatabase.getInstance().getReference("Teams");
        teamsRef.child(city).child(place).child(team.getTeamName()).setValue(team);
        findAndAddChild(FirebaseDatabase.getInstance().getReference("OrganizationStructure"), place, team.getTeamName(), place, city);
        FirebaseDatabase.getInstance().getReference("Organizations").child(team.getTeamName()).child("arrivingTruckList").setValue(null);
        FirebaseDatabase.getInstance().getReference("Organizations").child(team.getTeamName()).child("requests").child("totalRequested").setValue(0);
    }

    public void findAndAddChild(DatabaseReference parentRef, String targetChildKey, String newChildKey, String place, String city) {
        parentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    if (childSnapshot.hasChild(targetChildKey)) {
                        DatabaseReference targetChildRef = childSnapshot.child(targetChildKey).getRef();
                        targetChildRef.child(newChildKey).child("Type").setValue("team");
                        targetChildRef.child(newChildKey).child("up").setValue(place);
                        targetChildRef.child(newChildKey).child("city").setValue(city);
                        targetChildRef.child(newChildKey).child("place").setValue(place);
                        return;
                    } else {
                        findAndAddChild(childSnapshot.getRef(), targetChildKey, newChildKey, place, city);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void discardTeam(String city, String place, TeamInfo team)
    {
        DatabaseReference userListRef = FirebaseDatabase.getInstance().getReference("UserList");
        userListRef.child(team.getTeamLeaderName()).child("organizationName").setValue("");
        DatabaseReference teamsRef = FirebaseDatabase.getInstance().getReference("Teams");
        teamsRef.child(city).child(place).child(team.getTeamName()).removeValue();
        FirebaseDatabase.getInstance().getReference("Organizations").child(team.getTeamName()).removeValue();
        findAndDeleteChild(FirebaseDatabase.getInstance().getReference("OrganizationStructure"), team.getTeamName());
    }

    public void findAndDeleteChild(DatabaseReference parentRef, String targetChildKey) {
        parentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    if (childSnapshot.hasChild(targetChildKey)) {
                        DatabaseReference targetChildRef = childSnapshot.child(targetChildKey).getRef();
                        targetChildRef.removeValue();
                        return;
                    } else {
                        findAndAddChild(childSnapshot.getRef(), targetChildKey);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
