package com.example.catastrophecompass.UILayer.Common;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.UserLogin;
import com.example.catastrophecompass.DataLayer.Model.WItem;
import com.example.catastrophecompass.DomainLayer.Domain.ManagerLoginUC;

import java.util.List;

@HiltViewModel
public class ManagerLoginVM extends ViewModel {

    private ManagerLoginUC ucManager;


    @Inject
    public ManagerLoginVM(ManagerLoginUC ucManager) {
        this.ucManager = ucManager;
    }
    public String validateLogin(UserLogin userLogin){
        return ucManager.validateLogin(userLogin);
    }
}