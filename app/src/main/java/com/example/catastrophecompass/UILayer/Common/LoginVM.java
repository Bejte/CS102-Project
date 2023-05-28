package com.example.catastrophecompass.UILayer.Common;

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
public class LoginVM extends ViewModel {

    private UC uc;
    private REPO FBRepo;

    @Inject
    public LoginVM(UC uc) {
        this.uc = uc;
    }
    public void IsLoggedIn(){
        FBRepo.IsLoggedIn();
    }
}