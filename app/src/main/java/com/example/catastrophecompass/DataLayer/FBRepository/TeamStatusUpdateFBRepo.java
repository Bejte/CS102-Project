package com.example.catastrophecompass.DataLayer.FBRepository;

import com.example.catastrophecompass.DataLayer.Model.VolunteerInfo;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeamStatusUpdateFBRepo
{
    public boolean updateVolunteerInfo(String city, String place, String team, VolunteerInfo newVolunteerInfo) {
        DatabaseReference teamRef = FirebaseDatabase.getInstance().getReference().child("Teams").child(city).child(place).child(team).child("volunteerInfo");

        try {
            teamRef.child("crucial").setValue(newVolunteerInfo.getCrucial());
            teamRef.child("helpful").setValue(newVolunteerInfo.getHelpful());
            teamRef.child("unnecessary").setValue(newVolunteerInfo.getUnnecessary());
            teamRef.child("need").setValue(newVolunteerInfo.getNeed());
            return true;
        } catch (DatabaseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
