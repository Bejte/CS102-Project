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

    public void deleteUserFB(Credentials credentials)
    {
        //TODO
    }

}

