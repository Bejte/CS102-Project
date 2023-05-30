package com.example.catastrophecompass.UILayer.Common;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.ChooseCityFBRepo;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ChooseCityVM extends ViewModel {

    private ChooseCityFBRepo FBRepo;

    @Inject
    public ChooseCityVM(ChooseCityFBRepo chooseCityFBRepo) {
        this.FBRepo = chooseCityFBRepo;
    }
    public List<String> getCities(List<String> getCities) throws ExecutionException, InterruptedException {
        return FBRepo.getCities();
    }
}