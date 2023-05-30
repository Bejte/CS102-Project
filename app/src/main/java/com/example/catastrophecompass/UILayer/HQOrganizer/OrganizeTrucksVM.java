package com.example.catastrophecompass.UILayer.HQOrganizer;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class OrganizeTrucksVM extends ViewModel {

    private OrganizeTrucksFBRepo organizeTrucksFBRepo;



    @Inject
    public OrganizeTrucksVM(OrganizeTrucksFBRepo organizeTrucksFBRepo) {
        this.organizeTrucksFBRepo = organizeTrucksFBRepo;
    }
    public void getAvailableDrivers ( OrganizeTrucksInterface organizeTrucksInterface, String organizationName){
        organizeTrucksFBRepo.getAvailableDrivers(organizeTrucksInterface,organizationName);
    }
    public boolean reassignGET (String driverName) {
       return organizeTrucksFBRepo.reassignGET(driverName);
    }
    public boolean reassignDROP (String driverName) {
        return organizeTrucksFBRepo.reassignDROP(driverName);
    }


}