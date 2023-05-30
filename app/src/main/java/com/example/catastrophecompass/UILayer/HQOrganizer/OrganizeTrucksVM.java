package com.example.catastrophecompass.UILayer.HQOrganizer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import static androidx.fragment.app.FragmentManager.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.room.Insert;

import com.example.catastrophecompass.DataLayer.Model.WItem;
import com.example.catastrophecompass.DomainLayer.Domain.VIBAreaDomain;
import com.example.catastrophecompass.UILayer.Common.VIBAreaInterface;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.Disposable;
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