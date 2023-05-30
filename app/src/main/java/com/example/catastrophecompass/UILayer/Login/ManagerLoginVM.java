package com.example.catastrophecompass.UILayer.Login;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.UserLogin;
import com.example.catastrophecompass.DomainLayer.Domain.ManagerLoginUC;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ManagerLoginVM extends ViewModel {

    private ManagerLoginUC ucManager;


    @Inject
    public ManagerLoginVM(ManagerLoginUC ucManager) {
        this.ucManager = ucManager;
    }
    public String validateLogin(UserLogin userLogin){
        Log.d("ManagerLoginVM", "validateLogin() called");
        return ucManager.validateLogin(userLogin);
    }
}