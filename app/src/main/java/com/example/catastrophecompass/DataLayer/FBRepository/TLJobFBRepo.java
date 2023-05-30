package com.example.catastrophecompass.DataLayer.FBRepository;

import com.example.catastrophecompass.DataLayer.Model.TeamInfo;
import com.example.catastrophecompass.DataLayer.Model.VolunteerInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public interface VolunteerInfoCallback {
    void onVolunteerInfoLoaded(VolunteerInfo volunteerInfo);
    void onCancelled(DatabaseError databaseError);
}

public class TLJobFBRepo {
    public void getVolunteerInfo(String city, String place, String team, VolunteerInfoCallback callback) {
        DatabaseReference teamRef = FirebaseDatabase.getInstance().getReference().child("Teams").child(city).child(place).child(team);

        teamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot volunteerInfoSnapshot = dataSnapshot.child("volunteerInfo");

                    int crucial = volunteerInfoSnapshot.child("crucial").getValue(Integer.class);
                    int helpful = volunteerInfoSnapshot.child("helpful").getValue(Integer.class);
                    int unnecessary = volunteerInfoSnapshot.child("unnecessary").getValue(Integer.class);
                    int need = volunteerInfoSnapshot.child("need").getValue(Integer.class);

                    VolunteerInfo volunteerInfo = new VolunteerInfo(crucial, helpful, unnecessary, need);

                    callback.onVolunteerInfoLoaded(volunteerInfo);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Invoke the callback with the database error
                callback.onCancelled(databaseError);
            }
        });
    }

    public interface TeamInfoCallback {
        void onTeamInfoReceived(TeamInfo teamInfo);
    }

    public void getTeamInfo(String city, String place, String team, final TeamInfoCallback callback) {
        DatabaseReference teamRef = FirebaseDatabase.getInstance().getReference().child("Teams").child(city).child(place).child(team);

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
                    TeamInfo teamInfo = new TeamInfo(volunteerInfo, areaInfo, jobInfo, teamLeaderName, foodInfo);

                    callback.onTeamInfoReceived(teamInfo);
                } else {
                    callback.onTeamInfoReceived(null);  // or handle the case when data doesn't exist
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error case
                callback.onTeamInfoReceived(null);
            }
        });
    }


}

