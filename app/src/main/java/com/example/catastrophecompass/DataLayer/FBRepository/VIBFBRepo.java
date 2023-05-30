package com.example.catastrophecompass.DataLayer.FBRepository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VIBFBRepo {
    //TODO
    public void updateFoodInfo(String city, String place, String team, String newFoodInfo) {
        DatabaseReference teamRef = FirebaseDatabase.getInstance().getReference()
                .child("Teams").child(city).child(place).child(team).child("foodInfo");

        teamRef.setValue(newFoodInfo, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

            }
        });
    }

        public void attachToVolunteerList(String city, String place, String id) {
            DatabaseReference volunteerListRef = FirebaseDatabase.getInstance().getReference()
                    .child("Teams").child(city).child(place).child("volunteerList");

            volunteerListRef.child("id").setValue(id);
        }

    public void attachToTeam(String city, String place, String teamName) {//?
        DatabaseReference teamRef = FirebaseDatabase.getInstance().getReference()
                .child("Teams").child(city).child(place).child(teamName);

        teamRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve the necessary data from dataSnapshot and push locally
                String areaInfo = dataSnapshot.child("areaInfo").getValue(String.class);
                String jobInfo = dataSnapshot.child("jobInfo").getValue(String.class);
                String teamLeaderName = dataSnapshot.child("teamLeaderName").getValue(String.class);
                String foodInfo = dataSnapshot.child("foodInfo").getValue(String.class);

                VIBLocalRepo repo = new VIBLocalRepo();
                repo.pushToLocal(jobInfo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
    public void deleteUser(String userID) {
        DatabaseReference userRef = databaseRef.child("users").child(userID);
        userRef.removeValue();
        updateNodes(userID);
        removeFromDataStructures(userID);
    }

    private void updateNodes(String userID) {
        DatabaseReference teamsRef = databaseRef.child("Teams");

        teamsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot citySnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot placeSnapshot : citySnapshot.getChildren()) {
                        for (DataSnapshot teamSnapshot : placeSnapshot.getChildren()) {
                            DatabaseReference teamRef = teamSnapshot.getRef();

                            teamRef.child("volunteerList").child(userID).removeValue();
                            teamRef.child("teamLeaderID").setValue(null);

                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void removeFromDataStructures(String userID) {

    }
}

