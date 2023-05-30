package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import kotlinx.coroutines.Job;

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

    public boolean updateJobUrgency(String city, String place, String teamName)
    {
        DatabaseReference teamRef = databaseRef2.child(city).child(place).child(teamName).child("volunteerInfo");
        DatabaseReference urgencyRef = databaseRef.child(city).child(place).child(teamName);
        boolean[] done = {false};

        teamRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                // Get the current values of the crucial, helpful, unnecessary, and need subnodes
                Integer currentCrucial = mutableData.child("crucial").getValue(Integer.class);
                Integer currentHelpful = mutableData.child("helpful").getValue(Integer.class);
                Integer currentUnnecessary = mutableData.child("unnecessary").getValue(Integer.class);
                Integer currentNeed = mutableData.child("need").getValue(Integer.class);

                if (currentNeed != 0) {
                    mutableData.child("need").setValue(currentNeed - 1);
                    mutableData.child("crucial").setValue(currentCrucial + 1);
                } else {

                    int smallestValue = Math.min(Math.min(Math.min(currentCrucial, currentHelpful), currentUnnecessary), currentNeed);

                    if (smallestValue == currentCrucial) {
                        mutableData.child("crucial").setValue(currentCrucial + 1);
                    } else if (smallestValue == currentHelpful) {
                        mutableData.child("helpful").setValue(currentHelpful + 1);
                    } else if (smallestValue == currentUnnecessary) {
                        mutableData.child("unnecessary").setValue(currentUnnecessary + 1);
                    } else {
                        mutableData.child("need").setValue(currentNeed + 1);
                    }
                }

                // Return the updated data
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                if (databaseError != null) {
                    // Handle the error case
                } else {
                    int updatedUrgency = 5 * dataSnapshot.child("need").getValue(Integer.class)
                            + 4 * dataSnapshot.child("crucial").getValue(Integer.class)
                            + 3 * dataSnapshot.child("helpful").getValue(Integer.class)
                            + 1 * dataSnapshot.child("unnecessary").getValue(Integer.class);

                    urgencyRef.child("urgency").setValue(updatedUrgency);
                    done[0] = true;
                }
            }
        });
        return done[0];
    }
}
