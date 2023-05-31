package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

import com.example.catastrophecompass.DataLayer.Model.Contact;
import com.example.catastrophecompass.DataLayer.Model.DriverItem;
import com.example.catastrophecompass.DataLayer.Model.HQO;
import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;
import com.example.catastrophecompass.DataLayer.Model.Member;
import com.example.catastrophecompass.DataLayer.Model.TeamOrganizator;
import com.example.catastrophecompass.DataLayer.Model.UserLogin;


public class HQAddEditMemberFBRepo
{
    public void addLogisticMember(LogisticInfo logistics, String organizationName, UserLogin userLogin)
    {
        String id = FirebaseDatabase.getInstance().getReference("UserList").child(userLogin.getUserName()).child("id").push().getKey();
        FirebaseDatabase.getInstance().getReference("UserList").child(userLogin.getUserName()).child("password").setValue(userLogin.getPassword());
        FirebaseDatabase.getInstance().getReference("UserList").child(userLogin.getUserName()).child("userType").setValue("logistic");
        FirebaseDatabase.getInstance().getReference("UserList").child(userLogin.getUserName()).child("organizationName").setValue(organizationName);

        DatabaseReference logisticsRef = FirebaseDatabase.getInstance().getReference("Logistics").child(logistics.getLogisticDriverName());
        logisticsRef.child("id").setValue(id);
        logisticsRef.child("TruckSize").setValue(logistics.getTruckSize());
        logisticsRef.child("getName").setValue(logistics.getGetName());
        logisticsRef.child("getAddress").setValue(logistics.getGetAddress());
        logisticsRef.child("dropName").setValue(logistics.getDropName());
        logisticsRef.child("dropAddress").setValue(logistics.getDropAddress());
        logisticsRef.child("status").setValue("available");
        logisticsRef.child("getStatus").setValue(null);
        logisticsRef.child("dropStatus").setValue(null);
        logisticsRef.child("registeredOrganization").setValue(organizationName);
        logisticsRef.child("Inventory").setValue(null);
        logisticsRef.child("pictureURL").setValue(logistics.getPictureUrl());

        FirebaseDatabase.getInstance().getReference("Organizations").child(organizationName).child("driversRecorded").child(userLogin.getUserName()).setValue(logistics.getPictureUrl());
    }

    public void addHQOrganizator(HQO organizator, String organizationName)
    {
        String id = FirebaseDatabase.getInstance().getReference("UserList").child(organizator.getName()).child("id").push().getKey();
        FirebaseDatabase.getInstance().getReference("UserList").child(organizator.getName()).child("password").setValue(organizator.getPassword());
        FirebaseDatabase.getInstance().getReference("UserList").child(organizator.getName()).child("userType").setValue("AHQO");
        FirebaseDatabase.getInstance().getReference("UserList").child(organizator.getName()).child("organizationName").setValue(organizationName);

        FirebaseDatabase.getInstance().getReference("Organizations").child(organizationName).child("OrganizatorsList").child(organizator.getName()).setValue(organizator.getPictureUrl());


    }

    public void addTeamOrganizatorMember(TeamOrganizator teamOrganizator, String organizationName)
    {
        String id = FirebaseDatabase.getInstance().getReference("UserList").child(teamOrganizator.getName()).child("id").push().getKey();
        FirebaseDatabase.getInstance().getReference("UserList").child(teamOrganizator.getName()).child("password").setValue(teamOrganizator.getPassword());
        FirebaseDatabase.getInstance().getReference("UserList").child(teamOrganizator.getName()).child("userType").setValue("TO");
        FirebaseDatabase.getInstance().getReference("UserList").child(teamOrganizator.getName()).child("organizationName").setValue(organizationName);

        FirebaseDatabase.getInstance().getReference("Organizations").child("OrganizatorsList").child(teamOrganizator.getName()).setValue(teamOrganizator.getPictureUrl());

    }

    public void editLogisticMember(LogisticInfo logistics, String organizationName, UserLogin userLogin)
    {

        DatabaseReference logisticsRef = FirebaseDatabase.getInstance().getReference("Logistics").child(userLogin.getUserName());
        logisticsRef.child("TruckSize").setValue(logistics.getTruckSize());
        logisticsRef.child("pictureURL").setValue(logistics.getPictureUrl());

        FirebaseDatabase.getInstance().getReference("Organizations").child(organizationName).child("driversRecorded").child(userLogin.getUserName()).setValue(logistics.getPictureUrl());
    }

    public void editHQOrganizator(HQO organizator, String organizationName)
    {
        if(organizator.isAdmin() == false)
            FirebaseDatabase.getInstance().getReference("UserList").child(organizator.getName()).child("userType").setValue("HQO");
        else if(organizator.isAdmin() == true)
            FirebaseDatabase.getInstance().getReference("UserList").child(organizator.getName()).child("userType").setValue("AHQO");
        FirebaseDatabase.getInstance().getReference("Organizations").child(organizationName).child("OrganizatorsList").child(organizator.getName()).setValue(organizator.getPictureUrl());

    }

    public void editTeamOrganizatorMember(TeamOrganizator teamOrganizator, String organizationName)
    {
        FirebaseDatabase.getInstance().getReference("UserList").child(teamOrganizator.getName()).child("organizationName").setValue(organizationName);
        FirebaseDatabase.getInstance().getReference("Organizations").child(organizationName).child("OrganizatorsList").child(teamOrganizator.getName()).setValue(teamOrganizator.getPictureUrl());
    }

    public Member getPredisplay(Contact contact)
    {
        Member member;
        String name = contact.getContactName();
        String[] userType = new String[1];
        String[] organizationName = new String[1];
        String[] password = new String[1];
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("UserList").child(name);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userType[0] = snapshot.child("userType").getValue(String.class);
                organizationName[0] = snapshot.child("organizationName").getValue(String.class);
                password[0] = snapshot.child("password").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        boolean isAdmin = true;
        if(userType[0].equals("TO"))
        {

            TeamOrganizator teamOrganizator = new TeamOrganizator();
            teamOrganizator.setName(contact.getContactName());
            teamOrganizator.setPassword(password[0]);
            teamOrganizator.setPictureUrl(contact.getPictureUrl());
            teamOrganizator.setPictureAsBitmap(null);
            member = teamOrganizator;

            return member;
        }

        else if(userType[0].equals("logistic"))
        {
            String[] status = new String[1];
            int[] size = new int[1];
            FirebaseDatabase.getInstance().getReference("Logistics").child(contact.getContactName()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    status[0] = snapshot.child("status").getValue(String.class);
                    size[0] = snapshot.child("TruckSize").getValue(Integer.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            return new DriverItem(contact.getContactName(), status[0], size[0] +"");
        }

        else if(userType[0].equals("HQO") || userType[0].equals("AHQO"))
        {
            if(userType[0].equals("HQO"))
                isAdmin = false;
            else if(userType[0].equals("AHQO"))
                isAdmin = true;
            member = new HQO(contact.getContactName(), password[0], null, contact.getPictureUrl(), isAdmin);
            return member;
        }
        return null;
    }
}
