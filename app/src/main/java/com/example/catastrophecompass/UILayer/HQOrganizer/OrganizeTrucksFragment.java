package com.example.catastrophecompass.UILayer.HQOrganizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrganizeTrucksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrganizeTrucksFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapterForHQ adapter;
    private List<TruckItemForHQ> truckItems;

    public FirstFragmentOfHQ() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hq_fragment_first, container, false);

        truckItems = new ArrayList<>();
        truckItems.add(new TruckItemForHQ("John Doe", "Medium", "Available"));
        truckItems.add(new TruckItemForHQ("Jane Smith", "Large", "Assigned"));
        truckItems.add(new TruckItemForHQ("Jack Brown", "Small", "In Transit"));

        recyclerView = view.findViewById(R.id.rec_organize_truck_fr);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new RecyclerViewAdapterForHQ(getActivity(), truckItems);
        recyclerView.setAdapter(adapter);

        return view;
    }
}