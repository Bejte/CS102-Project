package com.example.catastrophecompass.DataLayer.FBRepository;

import com.example.catastrophecompass.DataLayer.Model.TeamInfo;
import com.example.catastrophecompass.DataLayer.Model.VolunteerInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TLJobFBRepo {
    public VolunteerInfo getVolunteerInfo(String city, String place, String team) {
        DatabaseReference teamRef = FirebaseDatabase.getInstance().getReference().child("Teams").child(city).child(place).child(team);
        VolunteerInfo[] volunteerInfo = new VolunteerInfo[1];
        teamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot volunteerInfoSnapshot = dataSnapshot.child("volunteerInfo");

                    int crucial = volunteerInfoSnapshot.child("crucial").getValue(Integer.class);
                    int helpful = volunteerInfoSnapshot.child("helpful").getValue(Integer.class);
                    int unnecessary = volunteerInfoSnapshot.child("unnecessary").getValue(Integer.class);
                    int need = volunteerInfoSnapshot.child("need").getValue(Integer.class);

                    volunteerInfo[0] = new VolunteerInfo(crucial, helpful, unnecessary, need);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Invoke the callback with the database error
            }
        });

        return volunteerInfo[0];
    }

    public TeamInfo getTeamInfo(String city, String place, String team) {
        DatabaseReference teamRef = FirebaseDatabase.getInstance().getReference().child("Teams").child(city).child(place).child(team);
        TeamInfo[] teamInfo = new TeamInfo[1];
        teamRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot volunteerInfoSnapshot = dataSnapshot.child("volunteerInfo");
                    String areaInfo = dataSnapshot.child("areaInfo").getValue(String.class);
                    String jobInfo = dataSnapshot.child("jobInfo").getValue(String.class);
                    String teamLeaderName = dataSnapshot.child("teamLeaderName").getValue(String.class);
                    String foodInfo = dataSnapshot.child("foodInfo").getValue(String.class);

                    int crucial = volunteerInfoSnapshot.child("crucial").getValue(Integer.class);
                    int helpful = volunteerInfoSnapshot.child("helpful").getValue(Integer.class);
                    int unnecessary = volunteerInfoSnapshot.child("unnecessary").getValue(Integer.class);
                    int need = volunteerInfoSnapshot.child("need").getValue(Integer.class);

                    VolunteerInfo volunteerInfo = new VolunteerInfo(crucial, helpful, unnecessary, need);
                    teamInfo[] = new TeamInfo(volunteerInfo, areaInfo, jobInfo, teamLeaderName, foodInfo);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return teamInfo[0];
    }


}

