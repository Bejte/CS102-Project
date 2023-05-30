package com.example.catastrophecompass.UILayer.Common;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.WItem;
import com.example.catastrophecompass.DomainLayer.Domain.OVWorkforceListUC;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class OVWorkforceListVM extends ViewModel {

    private OVWorkforceListUC uc;

    @Inject
    public OVWorkforceListVM(OVWorkforceListUC uc) {
        this.uc = uc;
    }
    public List<WItem> getWItemList(String cityName){
        return uc.getWItemList(cityName);
    }
}