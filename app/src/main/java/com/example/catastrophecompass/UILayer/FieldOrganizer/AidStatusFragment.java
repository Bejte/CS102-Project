package com.example.catastrophecompass.UILayer.FieldOrganizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.R;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;



import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class AidStatusFragment extends Fragment {


    private HorizontalBarChart chart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aid_stats, container, false);
        chart = view.findViewById(R.id.aid_stats_chart);

        setData();

        return view;
    }

    private void setData(){
        ArrayList<BarEntry> dayLeft = new ArrayList<>();
        dayLeft.add(new BarEntry(0, 8));
        dayLeft.add(new BarEntry(1, 3));
        dayLeft.add(new BarEntry(2, 7));
        dayLeft.add(new BarEntry(3, 5));

        BarDataSet barDataSet = new BarDataSet(dayLeft, "INVENTORY");
        barDataSet.setColor(Color.BLUE);

        BarData data = new BarData(barDataSet);
        chart.setData(data);

        String[] inventory = new String[] {"Water", "Food Supply", "Hygiene Needs", "Energy"};
        XAxis xAxis = chart.getXAxis();
        chart.getAxisLeft().setAxisMinimum(0);

        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.getAxisLeft().setAxisMinimum(0f);
        chart.getAxisRight().setAxisMinimum(0f);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setData();
    }
}