package com.example.catastrophecompass.UILayer.Common;

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
        try {
            return FBRepo.validateCode(code);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}