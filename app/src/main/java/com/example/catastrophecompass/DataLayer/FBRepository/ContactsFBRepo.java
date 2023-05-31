package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

import com.example.catastrophecompass.DataLayer.Model.Contact;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ContactsFBRepo {

    DataSnapshot targetOrgRef;

    public List<Contact> getContact(String organizationName)
    {
        DatabaseReference orgStructureRef = FirebaseDatabase.getInstance().getReference("OrganizationStructure");
        DatabaseReference orgsRef = FirebaseDatabase.getInstance().getReference("Organizations");
        ArrayList<Contact>[] peopleList = new ArrayList[1];

// Retrieve the organization structure
        orgStructureRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                findChild(orgStructureRef, organizationName);

                String upOrgName = targetOrgRef.child("up").getValue(String.class);

                DataSnapshot downList = targetOrgRef.child("downList");

                // Iterate through the down organizations
                for (DataSnapshot orgSnapshot : downList.getChildren()) {
                    String orgName = orgSnapshot.getKey();
                    // Retrieve people from the down organizations
                    retrievePeopleFromOrganization(orgsRef.child(orgName).child("organizersList"), peopleList[0]);
                }

                // Retrieve people from the up organization
                retrievePeopleFromOrganization(orgsRef.child(upOrgName).child("organizersList"), peopleList[0]);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });
        return peopleList[0];
    }

    private void retrievePeopleFromOrganization(DatabaseReference orgRef, List<Contact> peopleList) {
        orgRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Iterate through the organizers within the organization
                for (DataSnapshot organizerSnapshot : dataSnapshot.getChildren()) {
                    String personName = organizerSnapshot.getKey();
                    String pictureURL = organizerSnapshot.getValue(String.class);
                    Contact contact = new Contact(personName, pictureURL);
                    peopleList.add(contact);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });
    }

    public void findChild(DatabaseReference parentRef, String targetChildKey) {
        parentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    if (childSnapshot.hasChild(targetChildKey)) {
                        targetOrgRef = childSnapshot.child(targetChildKey);

                        return;
                    } else {
                        findChild(childSnapshot.getRef(), targetChildKey);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public boolean startChat(String userName, String contactName)
    {
        boolean[] status = {false};

        DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("Chats");

        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.child("Chatted").child(userName).hasChild(contactName))
                {
                    dataSnapshot.child("Chatted").child(userName).child(contactName).getRef().setValue("");
                }

                if(!dataSnapshot.child("Chatted").child(contactName).hasChild(userName))
                {
                    dataSnapshot.child("Chatted").child(contactName).child(userName).getRef().setValue("");
                }

                if(!dataSnapshot.child("ChatList").hasChild(contactName + userName) || !dataSnapshot.child("ChatList").hasChild(userName + contactName))
                {
                    if(userName.compareTo(contactName) < 0)
                    {
                        dataSnapshot.child("ChatList").child(userName + contactName).getRef().setValue(null);
                    }
                    else
                    {
                        dataSnapshot.child("ChatList").child(contactName + userName).getRef().setValue(null);
                    }
                }
                status[0] = true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return status[0];
    }
}
