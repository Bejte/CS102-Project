package com.example.catastrophecompass.DataLayer.FBRepository;


import androidx.annotation.NonNull;

import com.example.catastrophecompass.DataLayer.LocalRepository.TLJobLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DataLayer.Model.TeamInfo;
import com.example.catastrophecompass.DataLayer.Model.VolunteerInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TLJobFBRepo {

    TLJobLocalRepo localRepo;

    public TLJobFBRepo(TLJobLocalRepo localRepo) {
        this.localRepo = localRepo;
    }

    public void attachListener(Credentials credentials)
    {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Teams").child(credentials.getCity()).child(credentials.getPlace()).child(credentials.getTeamName());
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                localRepo.pushToLocal(snapshot.getValue(TeamInfo.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

