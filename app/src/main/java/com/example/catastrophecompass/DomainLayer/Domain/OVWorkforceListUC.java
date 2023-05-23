package com.example.catastrophecompass.DomainLayer.Domain;

import com.example.catastrophecompass.DataLayer.Model.WItem;
import com.example.catastrophecompass.DataLayer.Model.WItemFB;
import com.example.catastrophecompass.DataLayer.Model.WItemWeather;

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
        // TODO connect WItemFB and WItemWeather and return WItem (Emir)

        return null;
    }
}
