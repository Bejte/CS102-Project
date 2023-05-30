package com.example.catastrophecompass.UILayer.FieldOrganizer;
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

import com.example.catastrophecompass.DataLayer.Model.DemographicInfo;
import com.example.catastrophecompass.DataLayer.Model.User;
import com.example.catastrophecompass.DataLayer.Model.WItem;
import com.example.catastrophecompass.DomainLayer.Domain.VIBAreaDomain;
import com.example.catastrophecompass.UILayer.Common.VIBAreaInterface;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.Disposable;
@HiltViewModel
public class DemographicVM extends ViewModel implements demographicInterface {

    private FieldOrganizationInfoDomain fo_info_do;

    @Inject
    public DemographicVM(FieldOrganizationInfoDomain fo_info_do) {
        this.fo_info_do = fo_info_do;
    }

    public void setupDatabaseConnection(User user) {
        fo_info_do.setupDatabaseConnection(user);
    }

    @Override
    public void getDemographicInfo(demographicInterface demoint) {
        fo_info_do.getDemographicInfo(this);
    }

    public void getHousingInfo(housingInterface houseint) {
        fo_info_do.getHousingInfo(houseint);
    }
}
