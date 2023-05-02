package com.example.catastrophecompass.UILayer.Common;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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

import com.example.catastrophecompass.R;

import java.util.Arrays;
import java.util.List;

public class AidFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aid, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.aid_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        AidItem[] aidItems = {
                new AidItem("Place 1", "Address 1", "Cloth"),
                new AidItem("Place 2", "Address 2", "Food"),
                new AidItem("Place 3", "Address 3", "Food"),
                new AidItem("Place 4", "Address 4", "Cloth"),
                new AidItem("Place 5", "Address 5", "Cloth Baby"),
                new AidItem("Place 6", "Address 6", "Hygiene"),
        };


        AidAdapter aidAdapter = new AidAdapter(aidItems, position -> {
            Intent intent = new Intent(getActivity(), TenthActivity.class);
            startActivity(intent);
        });

        recyclerView.setAdapter(aidAdapter);

        return view;
    }
}