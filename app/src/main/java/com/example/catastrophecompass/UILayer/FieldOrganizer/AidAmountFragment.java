package com.example.catastrophecompass.UILayer.FieldOrganizer;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class AidAmountFragment extends Fragment {

    private BarChart barChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aid_amount, container, false);
        barChart = view.findViewById(R.id.aid_amount_chart);

        setData();

        return view;
    }

    private void setData() {
        ArrayList<BarEntry> aidQuantity = new ArrayList<>();
        aidQuantity.add(new BarEntry(0, 300));
        aidQuantity.add(new BarEntry(1, 250));
        aidQuantity.add(new BarEntry(2, 400));
        aidQuantity.add(new BarEntry(3, 500));

        BarDataSet barDataSet = new BarDataSet(aidQuantity, "INVENTORY");
        barDataSet.setColor(Color.BLUE);

        BarData data = new BarData(barDataSet);
        barChart.setData(data);

        String[] inventory = new String[] {"Water", "Food Supply", "Hygiene Needs", "Energy"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(inventory));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        barChart.setDragEnabled(true);

        barChart.getXAxis().setAxisMinimum(0);
        barChart.getAxisLeft().setAxisMinimum(0);

        barChart.invalidate();

        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.getAxisLeft().setAxisMinimum(0f);
        barChart.getAxisRight().setAxisMinimum(0f);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setData();
    }
}