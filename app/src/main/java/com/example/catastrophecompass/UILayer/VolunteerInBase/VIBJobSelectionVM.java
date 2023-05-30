package com.example.catastrophecompass.UILayer.VolunteerInBase;

import android.util.Log;

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
        Log.d("VIBJobSelectionVM", "fetchJobList() called");
        return FBRepo.fetchJobList(city,place);
    }
    public boolean updateJobUrgency (String city,String place,String teamName){
        Log.d("VIBJobSelectionVM", "updateJobUrgency() called");
       return FBRepo.updateJobUrgency (city, place, teamName);
    }

    public int recordID(){ // TODO return int or String
        Log.d("VIBJobSelectionVM", "recordID() called");
        return FBRepo.recordID();
    }

    public void recordCredentials (Credentials cre){
        Log.d("VIBJobSelectionVM", "recordCredentials() called");
        localRepo.recordCredentials(cre);
    }
}