package com.example.catastrophecompass.UILayer.FieldOrganizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.R;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class DemographicStatusFragment extends Fragment {

    private BarChart barChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demographic_status, container, false);
        barChart = view.findViewById(R.id.demogratic_chart);

        setData();

        return view;
    }

    private void setData() {
        ArrayList<BarEntry> men = new ArrayList<>();
        men.add(new BarEntry(0, 40));
        men.add(new BarEntry(8, 23));
        men.add(new BarEntry(15,60));
        men.add(new BarEntry(65, 30));

        ArrayList<BarEntry> women = new ArrayList<>();
        women.add(new BarEntry(0, 30));
        women.add(new BarEntry(8, 49));
        women.add(new BarEntry(15, 55));
        women.add(new BarEntry(65, 13));

        BarDataSet barDataSet1 = new BarDataSet(men, "MEN");
        barDataSet1.setColor(Color.BLUE);
        BarDataSet barDataSet2 = new BarDataSet(women, "WOMEN");
        barDataSet2.setColor(Color.RED);

        BarData data = new BarData(barDataSet1, barDataSet2);
        barChart.setData(data);

        String[] ageGap = new String[] { "0-3", "4-14", "15-64", "65+"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(ageGap));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        barChart.setDragEnabled(true);

        float barSpace = 0.08f;
        float groupSpace = 0.44f;
        data.setBarWidth(0.10f);

        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(0+barChart.getBarData().getGroupWidth(groupSpace, barSpace)*4);
        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.groupBars(0, groupSpace, barSpace);

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