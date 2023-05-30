package com.example.catastrophecompass.UILayer.FieldOrganizer;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.DemographicInfo;

@HiltViewModel
public class DemographicUpdateVM extends ViewModel implements demographicInterface {

    private FieldOrganizationInfoFBRepo fo_info_repo;

    @Inject
    public DemographicUpdateVM(FieldOrganizationInfoFBRepo fo_info_repo) {
        this.fo_info_repo = fo_info_repo;
    }

    public void updateDemographicInfo(DemographicInfo demoInfo) {
        fo_info_repo.updateDemographicInfo(demoInfo);
    }

    @Override
    public void getDemographicInfo(demographicInterface demoint) {
        fo_info_repo.getDemographicInfo(this);
    }
}
