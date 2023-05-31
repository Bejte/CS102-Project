package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.DataLayer.Model.AItem;
import com.example.catastrophecompass.R;

import java.util.List;

public class OVAidFragment extends Fragment {

    private AidPlacesListVM viewModel;
    private RecyclerView recyclerView;
    private AidItemAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AidPlacesListVM.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_o_v_aid, container, false);

        recyclerView = view.findViewById(R.id.rec_aid_fr_ov_aid_fr);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //viewModel.getAidPlacesList("CityName").observe(getViewLifecycleOwner(), this::onAidPlacesListUpdated);

        return view;
    }

    private void onAidPlacesListUpdated(List<AItem> aidPlaces) {
        adapter = new AidItemAdapter(aidPlaces, position -> {
            AItem clickedAidPlace = aidPlaces.get(position);
            //viewModel.recordClickedPlace(clickedAidPlace);

            Intent intent = new Intent(getActivity(), OVAidActivity.class);


            //intent.putExtra("aid_place", clickedAidPlace);


            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }
}