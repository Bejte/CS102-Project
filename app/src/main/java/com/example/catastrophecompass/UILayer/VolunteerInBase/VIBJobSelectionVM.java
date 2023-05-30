package com.example.catastrophecompass.UILayer.VolunteerInBase;

import androidx.lifecycle.ViewModel;

import java.util.List;

import com.example.catastrophecompass.DataLayer.FBRepository.JobListFBRepo;
import com.example.catastrophecompass.DataLayer.LocalRepository.VIBLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DataLayer.Model.VIBJobInfo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class VIBJobSelectionVM extends ViewModel {

    private JobListFBRepo FBRepo;
    private VIBLocalRepo localRepo;

    @Inject
    public VIBJobSelectionVM(JobListFBRepo FBRepo, VIBLocalRepo localRepo) {
        this.FBRepo = FBRepo;
        this.localRepo = localRepo;
    }

    public List<VIBJobInfo> fetchJobList(String city, String place){
        return FBRepo.fetchJobList(city,place);
    }
    public boolean updateJobUrgency (String city,String place,String teamName){
       return FBRepo.updateJobUrgency (city, place, teamName);
    }

    public int recordID(){ // TODO return int or String
        return FBRepo.recordID();
    }

    public void recordCredentials (Credentials cre){
        localRepo.recordCredentials(cre);
    }
}