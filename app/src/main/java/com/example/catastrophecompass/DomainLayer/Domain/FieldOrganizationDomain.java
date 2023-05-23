package com.example.catastrophecompass.DomainLayer.Domain;

import com.example.catastrophecompass.DataLayer.Model.User;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class FieldOrganizationDomain {
    private FieldOrganizationLocalRepo localRepo;
    private FieldOrganizationFBRepo FBRepo;
    private VectorDatabaseRepo vectorRepo;

    public FieldOrganizationDomain(FieldOrganizationLocalRepo localRepo, FieldOrganizationFBRepo FBRepo, VectorDatabaseRepo vectorRepo) {
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
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        demographicInterface.warnUser();
                    }

                    @Override
                    public void onComplete() {

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
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        housingInterface.warnUser();
                    }

                    @Override
                    public void onComplete() {

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
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

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
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public boolean updateAidStatusInfo(Inventory inventory){
        if (FBRepo.updateAidStatusInfo(inventory))
        {
            if (vectorRepo.updateAidStatusInfo(inventory))
                return true;
            else
                FBRepo.revertChanges(inventory);
        }
        return false;
    }
}
