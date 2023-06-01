package com.example.catastrophecompass.UILayer.VolunteerInBase;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.DataLayer.Model.VIBJobInfo;
import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.AreaFragment;
import com.example.catastrophecompass.UILayer.Common.FoodAdapter;

import java.util.ArrayList;

public class VIBAreaFragment extends Fragment implements VIBAreaInterface{

    private RecyclerView foodRecyclerView, weatherRecyclerView;
    private FoodAdapter foodAdapter;
    //private WeatherAdapter weatherAdapter;
    private VIBAreaVM viewModel;

    public VIBAreaFragment() {
        // Required empty public constructor
    }

    public static AreaFragment newInstance() {
        AreaFragment fragment = new AreaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_v_i_b_area, container, false);

        foodRecyclerView = view.findViewById(R.id.rec_foodRecycler_fo_av_fr);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        foodAdapter = new FoodAdapter(new ArrayList<>()); // replace ArrayList with your food data
        foodRecyclerView.setAdapter(foodAdapter);

        weatherRecyclerView = view.findViewById(R.id.weather_recycler_view);
        weatherRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //weatherAdapter = new WeatherAdapter(new ArrayList<>()); // replace ArrayList with your weather data
        //weatherRecyclerView.setAdapter(weatherAdapter);

        viewModel = new ViewModelProvider(this).get(VIBAreaVM.class);

        viewModel.getAreaInfo(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.updateFoodInfo("Food Info");  // Replace "Food Info" with your actual data
    }

    @Override
    public void setDisplay(VIBJobInfo vibJobInfo) {

    }

    @Override
    public void warnUser() {

    }
}
