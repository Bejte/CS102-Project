package com.example.catastrophecompass.UILayer.HQOrganizer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.DataLayer.Model.DriverItem;
import com.example.catastrophecompass.DomainLayer.Common.HQOrganizerCommon;
import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.RecyclerViewAdapterForHQ;
import com.example.catastrophecompass.UILayer.Common.TruckItemForHQ;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrganizeTrucksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class OrganizeTrucksFragment extends Fragment implements OrganizeTrucksInterface{

    private RecyclerView recyclerView;
    private TruckAdapter truckAdapter;
    public OrganizeTrucksVM vm;
    private List<DriverItem> truckList;
    private View view;

    public OrganizeTrucksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vm = new ViewModelProvider(this).get(OrganizeTrucksVM.class);
        view = inflater.inflate(R.layout.fragment_organize_trucks, container, false);
        vm.getAvailableDrivers(this, HQOrganizerCommon.organizationName);
        // TODO common
        recyclerView = view.findViewById(R.id.rec_organize_truck_fr);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        truckList = new ArrayList<>();
        truckAdapter = new TruckAdapter(getActivity(), truckList, this);
        recyclerView.setAdapter(truckAdapter);



        // Initialize the adapter with the truckList and set it to the RecyclerView





        return view;
    }

    @Override
    public void setDisplay(List<DriverItem> list) {
        // Initialize the RecyclerView, its layout manager and the adapter
        truckList = list;

        //truckList.clear();
        //truckList =new ArrayList<>(list);
        truckAdapter.notifyDataSetChanged(); // TODO test notifyDataSetChanged()



    }

    @Override
    public void warnUser() {

    }

    // You can add more methods for managing the Truck data here if needed
}