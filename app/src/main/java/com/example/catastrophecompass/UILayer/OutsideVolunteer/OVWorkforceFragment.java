package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.WorkforceAdapter;
import com.example.catastrophecompass.UILayer.Common.WorkforceItem;


//* A simple {@link Fragment} subclass.
 //* Use the {@link OVWorkforceFragment#newInstance} factory method to
 //* create an instance of this fragment.
 import android.content.Intent;
 import android.os.Bundle;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;

 import androidx.annotation.Nullable;
 import androidx.fragment.app.Fragment;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import java.util.ArrayList;
 import java.util.List;


 import android.content.Intent;
 import android.os.Bundle;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import androidx.annotation.NonNull;
 import androidx.annotation.Nullable;
 import androidx.fragment.app.Fragment;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;
 import java.util.Arrays;
 import java.util.List;

 public class OVWorkforceFragment extends Fragment {

 WorkforceItem w1, w2, w3, w4;

 @Nullable
 @Override
 public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
 View view = inflater.inflate(R.layout.fragment_o_v_workforce, container, false);

 RecyclerView recyclerView = view.findViewById(R.id.workforce_recyclerView);
 recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

 // Test for workforceItems
 w1 = new WorkforceItem("Place1", "Address Info For Place 1","17°C", "PARTIAl_SUNNY" );
 w2 = new WorkforceItem("Place2", "Address Info For Place 2","16°C", "PARTIAl_SUNNY" );
 w3 = new WorkforceItem("Place3", "Address Info For Place 3","29°C", "PARTIAl_SUNNY" );
 w4 = new WorkforceItem("Place4", "Address Info For Place 4","-2°C", "PARTIAl_SUNNY" );


 List<WorkforceItem> workforceItems = new ArrayList<>();
 workforceItems.add(w1);
 workforceItems.add(w2);
 workforceItems.add(w3);
 workforceItems.add(w4);
 WorkforceAdapter workforceAdapter = new WorkforceAdapter(workforceItems, position -> {
 Intent intent = new Intent(getActivity(), OVWorkforceActivity.class);
 startActivity(intent);
 });

 recyclerView.setAdapter(workforceAdapter);

 return view;
 }
 }