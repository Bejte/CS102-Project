package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.DataLayer.Model.WItem;
import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.WorkforceAdapter;
import com.example.catastrophecompass.UILayer.Common.WorkforceItemAdapter;


//* A simple {@link Fragment} subclass.
 //* Use the {@link OVWorkforceFragment#newInstance} factory method to
 //* create an instance of this fragment.
 import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import androidx.annotation.NonNull;

public class OVWorkforceFragment extends Fragment {

 private OVWorkforceListVM viewModel;
 private RecyclerView recyclerView;
 private WorkforceItemAdapter adapter;

 @Override
 public void onCreate(@Nullable Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  viewModel = new ViewModelProvider(this).get(OVWorkforceListVM.class);
 }

 @Nullable
 @Override
 public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
  View view = inflater.inflate(R.layout.fragment_o_v_workforce, container, false);
  recyclerView = view.findViewById(R.id.rec_workforece_fr_ov_wo_fr);
  recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

  viewModel.getWItemList("CityName").observe(getViewLifecycleOwner(), this::onWorkforceListUpdated);

  return view;
 }

 private void onWorkforceListUpdated(List<WItem> wItemList) {
  adapter = new WorkforceItemAdapter(wItemList, position -> {
   WItem clickedWorkforceItem = wItemList.get(position);
   viewModel.recordClickedPlace(clickedWorkforceItem);
   // Assuming you will create WorkforceActivity to display details of clicked place
   Intent intent = new Intent(getActivity(), OVWorkforceActivity.class);
   intent.putExtra("clicked_workforce_item", clickedWorkforceItem);
   startActivity(intent);
  });
  recyclerView.setAdapter(adapter);
 }
}