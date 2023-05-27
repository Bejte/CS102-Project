package com.example.catastrophecompass.UILayer.Common;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ChooseCityViewModel extends ViewModel {
    private ChooseCityRepository repository;
    private LiveData<List<String>> cityNames;

    public ChooseCityViewModel() {
        repository = new ChooseCityRepository();
        cityNames = repository.getCityNames();
    }

    public LiveData<List<String>> getCityNames() {
        return cityNames;
    }
}
