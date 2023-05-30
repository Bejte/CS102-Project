package com.example.catastrophecompass.UILayer.Login;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.EnterCodeFBRepo;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EnterCodeVM extends ViewModel {

    private EnterCodeFBRepo FBRepo;

    @Inject
    public EnterCodeVM(EnterCodeFBRepo FBRepo) {
        this.FBRepo = FBRepo;
    }

    public String validateCode(String code){
        Log.d("EnterCodeVM", "validateCode() called");
        return FBRepo.validateCode(code);
    }
}