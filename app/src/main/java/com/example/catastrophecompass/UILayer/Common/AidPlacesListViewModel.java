package com.example.catastrophecompass.UILayer.Common;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.WItem;

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
public class AidPlacesListViewModel extends ViewModel {

    private UC uc;
    private REPO FBRepo;

    @Inject
    public AidPlacesListViewModel(UC uc) {
        this.uc = uc;
    }
    public List<AidItem> getAidPlacesList (List<AidItem> cityName){
        return FBRepo.getAidPlacesList(cityName);
    }
}
