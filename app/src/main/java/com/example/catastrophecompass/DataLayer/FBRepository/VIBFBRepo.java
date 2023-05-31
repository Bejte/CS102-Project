package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catastrophecompass.DataLayer.LocalRepository.VIBLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DataLayer.Model.VIBJobInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VIBFBRepo {

    private VIBLocalRepo localRepo;

    public VIBFBRepo(VIBLocalRepo localRepo) {
        this.localRepo = localRepo;
    }

    public void updateFoodInfo(String city, String place, String team, String newFoodInfo) {
        DatabaseReference teamRef = FirebaseDatabase.getInstance().getReference()
                .child("Teams").child(city).child(place).child(team).child("foodInfo");

        teamRef.setValue(newFoodInfo);
    }

    public String attachToVolunteerList(String city, String place,String id)
    {
        DatabaseReference volunteerListRef = FirebaseDatabase.getInstance().getReference("Teams").child(city).child(place).child("VolunteerList");
        String[] teamName = new String[1];
        teamName[0] = volunteerListRef.child(id).getValue();
        volunteerListRef.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    teamName[0] = snapshot.getValue(String.class);
                    attachToTeam(city, place, teamName[0]);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return teamName[0];
    }

    public void attachToTeam(String city, String place, String teamName)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Teams").child(city).child(place).child(teamName);

        VIBJobInfo info = new VIBJobInfo(teamName, ref.getValue().getTeamDescription(), ref.getValue().getTeamLeaderName(), ref.getValue().getAreaInfo(), ref.getValue().getFoodInfo(), ref.getValue().getLocation(), ref.getValue().getTeamLeaderPicUrl(), ref.getValue().getDispatch());
        localRepo.pushToLocal(info);
    }

    public void deleteUserFB(Credentials credentials)throws NullPointerException
    {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Teams").child(credentials.getCity()).child(credentials.getPlace());
        DatabaseReference[] toDeleteRef = new DatabaseReference[1];
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for(DataSnapshot idSnapshot : dataSnapshot.child("volunteerList").getChildren())
                {
                    if(idSnapshot.getValue(Integer.class) == credentials.getId())
                    {
                        toDeleteRef[0] = idSnapshot.getRef();
                        toDeleteRef[0].removeValue();
                    }
                }
                Integer currentCrucial = mutableData.child("crucial").getValue(Integer.class);
                Integer currentHelpful = mutableData.child("helpful").getValue(Integer.class);
                Integer currentUnnecessary = mutableData.child("unnecessary").getValue(Integer.class);
                Integer currentNeed = mutableData.child("need").getValue(Integer.class);

                int smallestValue = findSmallestValue(currentCrucial, currentHelpful, currentNeed, currentUnnecessary);

                if (smallestValue == currentCrucial) {
                    mutableData.child("crucial").setValue(currentCrucial - 1);
                } else if (smallestValue == currentHelpful) {
                    mutableData.child("helpful").setValue(currentHelpful - 1);
                } else if (smallestValue == currentUnnecessary) {
                    mutableData.child("unnecessary").setValue(currentUnnecessary - 1);
                } else {
                    mutableData.child("need").setValue(currentNeed - 1);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public int findSmallestValue(int a, int b, int c, int d) {
        int smallest = Integer.MAX_VALUE;  // Initialize with a large value

        // Compare each variable with the current smallest value
        if (a != 0 && a < smallest) {
            smallest = a;
        }
        if (b != 0 && b < smallest) {
            smallest = b;
        }
        if (c != 0 && c < smallest) {
            smallest = c;
        }
        if (d != 0 && d < smallest) {
            smallest = d;
        }

        return smallest;
    }

}

