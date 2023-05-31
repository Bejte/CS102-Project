package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catastrophecompass.DataLayer.LocalRepository.VIBLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DataLayer.Model.TeamInfo;
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
        volunteerListRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                teamName[0] = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
        String[] teamDescription = new String[1];
        String[] teamLeaderName = new String[1];
        String[] areaInfo = new String[1];
        String[] foodInfo = new String[1];
        String[] location = new String[1];
        String[] teamLeaderPicUrl = new String[1];
        boolean[] dispatch = new boolean[1];
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {
            teamDescription[0] = dataSnapshot.getValue(TeamInfo.class).getTeamDescription();
            teamLeaderName[0] = dataSnapshot.getValue(TeamInfo.class).getTeamLeaderName();
            areaInfo[0] = dataSnapshot.getValue(TeamInfo.class).getAreaInfo();
            foodInfo[0] = dataSnapshot.getValue(TeamInfo.class).getFoodInfo();
            location[0] = dataSnapshot.getValue(TeamInfo.class).getLocation();
            teamLeaderPicUrl[0] = dataSnapshot.getValue(TeamInfo.class).getUrl();
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

        VIBJobInfo info = new VIBJobInfo(teamName, teamDescription[0], teamLeaderName[0], areaInfo[0], foodInfo[0], location[0], teamLeaderPicUrl[0], false);
        localRepo.pushToLocal(info);
    }

    public boolean deleteUserFB(Credentials credentials)throws NullPointerException
    {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Teams").child(credentials.getCity()).child(credentials.getPlace());
        DatabaseReference[] toDeleteRef = new DatabaseReference[1];

        boolean[] returnValue = new boolean[1];
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
                Integer currentCrucial = dataSnapshot.child(credentials.getTeamName()).child("crucial").getValue(Integer.class);
                Integer currentHelpful = dataSnapshot.child(credentials.getTeamName()).child("helpful").getValue(Integer.class);
                Integer currentUnnecessary = dataSnapshot.child(credentials.getTeamName()).child("unnecessary").getValue(Integer.class);
                Integer currentNeed = dataSnapshot.child(credentials.getTeamName()).child("need").getValue(Integer.class);

                int smallestValue = findSmallestValue(currentCrucial, currentHelpful, currentNeed, currentUnnecessary);

                if (smallestValue == currentCrucial) {
                    dataSnapshot.child(credentials.getTeamName()).child("crucial").getRef().setValue(currentCrucial - 1);
                } else if (smallestValue == currentHelpful) {
                    dataSnapshot.child(credentials.getTeamName()).getRef().setValue(currentHelpful - 1);
                } else if (smallestValue == currentUnnecessary) {
                    dataSnapshot.child(credentials.getTeamName()).getRef().setValue(currentUnnecessary - 1);
                } else {
                    dataSnapshot.child(credentials.getTeamName()).getRef().setValue(currentNeed - 1);
                }
                returnValue[0] = true;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                returnValue[0] = false;
            }
        });
        return returnValue[0];
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

