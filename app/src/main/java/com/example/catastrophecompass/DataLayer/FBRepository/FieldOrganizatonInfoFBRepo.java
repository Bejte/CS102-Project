package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

import com.example.catastrophecompass.DataLayer.LocalRepository.FieldOrganizationInfoLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.DemographicInfo;
import com.example.catastrophecompass.DataLayer.Model.HousingInfo;
import com.example.catastrophecompass.DataLayer.Model.InventoryList;
import com.example.catastrophecompass.DataLayer.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FieldOrganizatonInfoFBRepo
{
    FieldOrganizationInfoLocalRepo localRepo;
    public FieldOrganizatonInfoFBRepo(FieldOrganizationInfoLocalRepo localRepo) {
        this.localRepo = localRepo;
    }

    public boolean updateDemographicInfo(DemographicInfo demographicInfo, String organizationName)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(organizationName).child("demographic");
        //child?????????????????
        final boolean[] success = {true};

        DemographicInfo newInfo = new DemographicInfo(ref.getValue().getM0_3() + demographicInfo.getM0_3(),
                ref.getValue().getM3_15() + demographicInfo.getM3_15(),
                ref.getValue().getM15_64() + demographicInfo.getM15_64(),
                ref.getValue().getM65() + demographicInfo.getM65(),
                ref.getValue().getW0_3() + demographicInfo.getW0_3(),
                ref.getValue().getW3_15() + demographicInfo.getW3_15(),
                ref.getValue().getW15_64() + demographicInfo.getW15_64(),
                ref.getValue().getW65() + demographicInfo.getW65());
        ref.setValue(newInfo).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                success[0] = false;
            }
        });
        return success[0];
    }

    public boolean updateHousingInfo(HousingInfo housingInfo, String organizationName)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(organizationName).child("housing");
        //child?????????????????

        final boolean[] success = {true};

        HousingInfo newInfo = new HousingInfo(ref.getValue().getHasHouse() + housingInfo.getHasHouse(),
                ref.getValue().getNoHouse() + housingInfo.getNoHouse(),
                ref.getValue().getBadCondition() + housingInfo.getBadCondition());
        ref.setValue(newInfo).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                success[0] = false;
            }
        });
        return success[0];
    }
    InventoryList currentList;
    public boolean updateAidStatusInfo(InventoryList inventoryList, String organizationName)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(organizationName).child("currentInventory");
        currentList = FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(organizationName).child("currentInventory").getValue();
        final boolean[] success = {true};


        InventoryList newList = new InventoryList(1, ref.getValue().getFood() + inventoryList.getFood(),
                ref.getValue().getHeater() + inventoryList.getHeater(),
                ref.getValue().getManCloth() + inventoryList.getManCloth(),
                ref.getValue().getWomenCloth() + inventoryList.getWomanCloth(),
                ref.getValue().getChildCloth() + inventoryList.getChildCloth(),
                ref.getValue().getHygene() + inventoryList.getHygene(),
                ref.getValue().getKitchenMaterial() + inventoryList.getKitchenMaterial(),
                ref.getValue().getPowerBank() + inventoryList.getPowerbank());
        ref.setValue(newList).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                success[0] = false;
            }
        });
        return success[0];

    }

    public void revertChanges(String organizationName)
    {
        FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(organizationName).child("currentInventory").setValue(currentList);
    }

    public void attachListeners(User user)
    {
        //TODO
    }
}
