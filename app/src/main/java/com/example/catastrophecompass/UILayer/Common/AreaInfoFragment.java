package com.example.catastrophecompass.UILayer.Common;



import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.catastrophecompass.R;

import java.util.ArrayList;
import java.util.List;

public class AreaInfoFragment extends Fragment {

    public AreaInfoFragment() {
        // Required empty public constructor
    }

    public static AreaInfoFragment newInstance() {
        AreaInfoFragment fragment = new AreaInfoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_area_info, container, false);

       // Button seeOtherJobsButton = view.findViewById(R.id.see_other_jobs_button);
        //seeOtherJobsButton.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
                // Handle button click action
         //   }
        //});
        //TextView areaInfoTextView = view.findViewById(R.id.info_text);
        //areaInfoTextView.setText("Info");

        // Get the TextViews for weather title and description
        //TextView weatherTitle = view.findViewById(R.id.weather_title);
        //TextView weatherDescription = view.findViewById(R.id.weather_description);

        // TODO: Fetch weather data and update the TextViews
        // For now, let's set some dummy data
        //weatherTitle.setText("Current weather:");
        //weatherDescription.setText("Sunny, 25°C");
        //food available
        //RecyclerView foodRecyclerView = view.findViewById(R.id.food_recycler_view);
        List<String> foodList = new ArrayList<>(); // Populate this list with food items
        FoodAdapter foodAdapter = new FoodAdapter(foodList);
        //foodRecyclerView.setAdapter(foodAdapter);
        //return view;
        return null;
    }
}