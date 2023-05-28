package com.example.catastrophecompass.UILayer.Common;

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

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.Disposable;
@HiltViewModel
public class VIBJobSelectionVM extends ViewModel {

    private UC uc;
    private JobListFBRepo FBRepo;
    private VIBLocalRepo FBLocalRepo;

    @Inject
    public VIBJobSelectionVM(UC uc) {
        this.uc = uc;
    }
    public List<Job> fetchJobList(String city, String place){
        return FBRepo.fetchJobList(city,place);
    }
    public void updateJobUrgency (String city,String place,String teamName){
       FBRepo.updateJobUrgency (city, place, teamName);
    }
    public void recordCredenials (Credentials cre){
        FBLocalRepo.recordCredenials(cre);
    }
}