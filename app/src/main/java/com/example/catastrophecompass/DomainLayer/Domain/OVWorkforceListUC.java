package com.example.catastrophecompass.DomainLayer.Domain;

import com.example.catastrophecompass.DataLayer.Model.WItem;
import com.example.catastrophecompass.DataLayer.Model.WItemFB;
import com.example.catastrophecompass.DataLayer.Model.WItemWeather;

import java.util.ArrayList;
import java.util.List;

public class OVWorkforceListUC {
    private OVWorkforceListFBRepo repo;
    private WeatherAPI weather;

    public OVWorkforceListUC(OVWorkforceListFBRepo repo, WeatherAPI weather){
        this.repo = repo;
        this.weather = weather;
    }

    public List<WItem> getWItemList(String cityName){
        List<WItemFB> wItemFBList = repo.getWItemList(cityName);
        List<WItemWeather> wItemWeatherList = weather.getWItemCurrentWeather(wItemFBList);
        List<WItem> wItemList = new ArrayList<>();
        for (int i = 0; i < wItemFBList.size(); i++)
            wItemList.add(new WItem(wItemFBList.get(i), wItemWeatherList.get(i)));
        return wItemList;
    }
}
