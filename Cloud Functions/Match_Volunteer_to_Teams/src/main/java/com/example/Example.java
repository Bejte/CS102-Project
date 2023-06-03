package com.example;


import com.google.cloud.functions.Context;
import com.google.cloud.functions.RawBackgroundFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.io.IOException;

public class Example implements RawBackgroundFunction {
  private static final Logger logger = Logger.getLogger(Example.class.getName());

  @Override
  public void accept(String json, Context context) throws InterruptedException {
    DatabaseReference ref = null;
        try {
            // Initialize the Firebase Admin SDK with the credentials
            FileInputStream serviceAccount = null;
            try {
                serviceAccount = new FileInputStream("serviceAccountKey.json");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("catastrophe-compass")
                .setDatabaseUrl("https://catastrophe-compass-default-rtdb.europe-west1.firebasedatabase.app")
                .build();
                FirebaseApp.initializeApp(options);
            } catch (IllegalStateException e) {
            }
    
            // Access the Firebase Realtime Database or perform other operations using the admin SDK
            FirebaseDatabase database = FirebaseDatabase.getInstance(FirebaseApp.getInstance());
            ref = database.getReference();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NeedTeam[] needTeam = new NeedTeam[1];
        ref.child("needs").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // TODO Auto-generated method stub
                DataSnapshot s = snapshot.getChildren().iterator().next();
                needTeam[0] = s.getValue(NeedTeam.class).setTeamName(s.getKey());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // TODO Auto-generated method stub
            }
            
        });
        Thread.sleep(4000);
        ref.child("needs").setValueAsync(null);
        List<VolunteerData> list = new ArrayList<>();
        final VolunteerData[] neededTeamData = new VolunteerData[1];
        // ref.child("needs").setValueAsync(null);
        ref = ref.child("teams").child(needTeam[0].getCity()).child(needTeam[0].getPlace());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // TODO Auto-generated method stub
                for (DataSnapshot team : snapshot.getChildren())
                {
                    if (!team.getKey().equals("volunteerList") && !team.getKey().equals(needTeam[0].getTeamName()))
                        list.add(team.getValue(VolunteerData.class).setTeamName(team.getKey()));
                    if (team.getKey().equals(needTeam[0].getTeamName()))
                        neededTeamData[0] = team.getValue(VolunteerData.class).setTeamName(team.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method 'onCancelled'");
            }
            
        });
        Thread.sleep(3000);
        DatabaseReference needTeamRef = ref.child(needTeam[0].getTeamName()).child("volunteerInfo");
        DatabaseReference unnecessaryTeamRef = ref;
        final int neededVolunteer[] = new int[1];
        neededVolunteer[0] = needTeam[0].getNeed();
        for (VolunteerData volunteerData : list)
        {
            unnecessaryTeamRef = ref.child(volunteerData.getTeamName()).child("volunteerInfo");
            int unnecessaryVolunteer = volunteerData.getVolunteerInfo().getUnnecessary();
            if (unnecessaryVolunteer > 0)
            {
                if (unnecessaryVolunteer >= neededVolunteer[0])
                {
                    neededTeamData[0].getVolunteerInfo().setNeed(0);
                    needTeamRef.child("need").setValueAsync(0);
                    neededTeamData[0].getVolunteerInfo().setCrucial(neededTeamData[0].getVolunteerInfo().getCrucial() + neededVolunteer[0]);
                    needTeamRef.child("crucial").setValueAsync(neededTeamData[0].getVolunteerInfo().getCrucial());
                    unnecessaryTeamRef.child("unnecessary").setValueAsync(unnecessaryVolunteer-neededVolunteer[0]);

                    final DatabaseReference reference = ref;
                    final DataSnapshot[] s = new DataSnapshot[1];

                    ref.child("volunteerList").addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            // TODO Auto-generated method stub
                            s[0] = snapshot;
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // TODO Auto-generated method stub
                            //throw new UnsupportedOperationException("Unimplemented method 'onCancelled'");
                        }
                        
                    });
                    Thread.sleep(5000);
                    int needed = neededVolunteer[0];
                            for (DataSnapshot snap : s[0].getChildren())
                            {
                                if (snap.getValue(String.class).equals(volunteerData.getTeamName()))
                                {
                                    reference.child("volunteerList").child(snap.getKey()).setValueAsync(neededTeamData[0].getTeamName());
                                    needed--;
                                }
                                if (needed == 0)
                                    break;
                            }
                    Thread.sleep(5000);
                    neededVolunteer[0] = 0;
                }

                else
                {
                    neededTeamData[0].getVolunteerInfo().setNeed(neededVolunteer[0] - unnecessaryVolunteer);
                    needTeamRef.child("need").setValueAsync(neededVolunteer[0] - unnecessaryVolunteer);
                    neededTeamData[0].getVolunteerInfo().setCrucial(neededTeamData[0].getVolunteerInfo().getCrucial() + unnecessaryVolunteer);
                    needTeamRef.child("crucial").setValueAsync(neededTeamData[0].getVolunteerInfo().getCrucial());
                    unnecessaryTeamRef.child("unnecessary").setValueAsync(0);

                    final DatabaseReference reference = ref;
                    final DataSnapshot[] s = new DataSnapshot[0];

                    ref.child("volunteerList").addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            // TODO Auto-generated method stub
                            s[0] = snapshot;
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // TODO Auto-generated method stub
                            //throw new UnsupportedOperationException("Unimplemented method 'onCancelled'");
                        }
                        
                    });
                    int needed = unnecessaryVolunteer;
                            for (DataSnapshot snap : s[0].getChildren())
                            {
                                if (snap.getValue(String.class).equals(volunteerData.getTeamName()))
                                {
                                    reference.child("volunteerList").child(snap.getKey()).setValueAsync(neededTeamData[0].getTeamName());
                                    needed--;
                                }
                                if (needed == 0)
                                    break;
                            }
                    Thread.sleep(5000);
                    neededVolunteer[0] -= unnecessaryVolunteer;
                }
            }

            if (neededVolunteer[0] == 0)
                break;
        }
  }
  
}
