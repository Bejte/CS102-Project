package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

import com.example.catastrophecompass.DataLayer.Model.VolunteerInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class JobListFBRepo {
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("TeamListUrgency");
    DatabaseReference databaseRef2 = FirebaseDatabase.getInstance().getReference().child("Teams");

    public List<Job> fetchJobList(String city, String place) {
        ArrayList<Job>[] jobs = new ArrayList[1];

        databaseRef.child(city).child(place).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot teamSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot urgencySnapshot : teamSnapshot.getChildren()) {
                        int urgency = urgencySnapshot.getValue(Integer.class);
                        String teamName = teamSnapshot.getKey();
                        Job job = new Job(teamName, urgency);
                        jobs[0].add(job);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return jobs[0];
    }

    public boolean updateJobUrgency(String city, String place, String teamName) {
        DatabaseReference teamRef = databaseRef2.child(city).child(place).child(teamName);
        DatabaseReference urgencyRef = databaseRef.child(city).child(place).child(teamName);
        boolean[] done = {false};

        teamRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int currentCrucial = dataSnapshot.getValue().getVolunteerInfo().getCrucial();
                int currentHelpful = dataSnapshot.getValue().getVolunteerInfo().getHelpful();
                int currentUnnecessary = dataSnapshot.getValue().getVolunteerInfo().getUnnecessary();
                int currentNeed = dataSnapshot.getValue().getVolunteerInfo().getNeed();

                if (currentNeed != 0) {
                    dataSnapshot.getValue().getVolunteerInfo().setNeed(currentNeed - 1);
                    dataSnapshot.getValue().getVolunteerInfo().setCrucial(currentNeed + 1);
                } else {

                    int smallestValue = Math.min(Math.min(Math.min(currentCrucial, currentHelpful), currentUnnecessary), currentNeed);

                    if (smallestValue == currentCrucial) {
                        dataSnapshot.getValue().getVolunteerInfo().setCrucial(currentNeed + 1);
                    } else if (smallestValue == currentHelpful) {
                        dataSnapshot.getValue().getVolunteerInfo().setHelpful(currentNeed + 1);
                    } else if (smallestValue == currentUnnecessary) {
                        dataSnapshot.getValue().getVolunteerInfo().setUnnecessaryl(currentNeed + 1);
                    } else {
                        dataSnapshot.getValue().getVolunteerInfo().setNeed(currentNeed + 1);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                done[0] = false;
            }
        });

        urgencyRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int updatedUrgency = 5 * dataSnapshot.child("need").getValue(Integer.class)
                        + 4 * dataSnapshot.child("crucial").getValue(Integer.class)
                        + 3 * dataSnapshot.child("helpful").getValue(Integer.class)
                        + 1 * dataSnapshot.child("unnecessary").getValue(Integer.class);

                urgencyRef.child("urgency").setValue(updatedUrgency);
                done[0] = true;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                done[0] = false;
            }
        });

        return done[0];
    }
}
