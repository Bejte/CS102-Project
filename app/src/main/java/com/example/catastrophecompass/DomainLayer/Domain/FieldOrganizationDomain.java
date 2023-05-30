package com.example.catastrophecompass.DomainLayer.Domain;

import android.util.Log;

import com.example.catastrophecompass.DataLayer.LocalRepository.FieldOrganizationInfoLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.DemographicInfo;
import com.example.catastrophecompass.DataLayer.Model.HousingInfo;
import com.example.catastrophecompass.DataLayer.Model.InventoryList;
import com.example.catastrophecompass.DataLayer.Model.User;
import com.example.catastrophecompass.RemoteDataRepository.VectorDatabaseRepo.VectorDatabaseRepo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class FieldOrganizationDomain {
    private FieldOrganizationInfoLocalRepo localRepo;
    private FieldOrganizationInfoFBRepo FBRepo;
    private VectorDatabaseRepo vectorRepo;

    public FieldOrganizationDomain(FieldOrganizationInfoLocalRepo localRepo, FieldOrganizationInfoFBRepo FBRepo, VectorDatabaseRepo vectorRepo) {
        this.localRepo = localRepo;
        this.FBRepo = FBRepo;
        this.vectorRepo = vectorRepo;
    }

    public void setupDatabaseConnection(User user){
        FBRepo.attachListeners(user);
    }

    public void getDemographicInfo(DemographicInterface demographicInterface){
        localRepo.getDemographicInfo()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<DemographicInfo>() {
                    @Override
                    public void onNext(DemographicInfo demographicInfo) {
                        demographicInterface.setDisplay(demographicInfo);
                        Log.d("FieldOrganizationDomain", "getDemographicInfo() onNext() called");
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        demographicInterface.warnUser();
                        Log.d("FieldOrganizationDomain", "getDemographicInfo() onError() called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("FieldOrganizationDomain", "getDemographicInfo() onComplete() called");
                    }
                });
    }

    public void getHousingInfo(HousingInterface housingInterface){
        localRepo.getHousingInfo()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<HousingInfo>() {
                    @Override
                    public void onNext(HousingInfo housingInfo) {
                        housingInterface.setDisplay(housingInfo);
                        Log.d("FieldOrganizationDomain", "getHousingInfo() onNext() called");
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        housingInterface.warnUser();
                        Log.d("FieldOrganizationDomain", "getHousingInfo() onError() called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("FieldOrganizationDomain", "getHousingInfo() onComplete() called");
                    }
                });
    }

    public void getInventoryInfo(InventoryInterface inventoryInterface){
        localRepo.getInventoryInfo()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<InventoryList>() {
                    @Override
                    public void onNext(InventoryList inventoryList) {
                        inventoryInterface.setDisplay(inventoryList);
                        Log.d("FieldOrganizationDomain", "getInventoryInfo() onNext() called");
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        Log.d("FieldOrganizationDomain", "getInventoryInfo() onError() called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("FieldOrganizationDomain", "getInventoryInfo() onComplete() called");
                    }
                });
    }

    public void getArrivingInfo(ArrivingInterface arrivingInterface){
        localRepo.getArrivingInfo()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<Inventory>() {
                    @Override
                    public void onNext(Inventory inventory) {
                        arrivingInterface.setDisplay(inventory);
                        Log.d("FieldOrganizationDomain", "getArrivingInfo() onNext() called");
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        Log.d("FieldOrganizationDomain", "getArrivingInfo() onError() called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("FieldOrganizationDomain", "getArrivingInfo() onComplete() called");
                    }
                });
    }

    public boolean updateAidStatusInfo(InventoryList inventory){
        if (FBRepo.updateAidStatusInfo(inventory))
        {
            if (vectorRepo.updateAidStatusInfo(inventory, FieldOrganizerCommon.organizationName))
                return true;
            else
                FBRepo.revertChanges(inventory);
        }
        return false;
    }
}
