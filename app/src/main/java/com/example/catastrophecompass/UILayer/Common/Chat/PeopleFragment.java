package com.example.catastrophecompass.UILayer.Common.Chat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PeopleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.widget.SearchView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.PeopleAdapter;
import com.example.myapplication.Person;
import com.example.myapplication.R;

public class PeopleFragment extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private PeopleAdapter peopleAdapter;
    private List<Person> peopleList;

    public PeopleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people, container, false);

        searchView = view.findViewById(R.id.search_view);
        recyclerView = view.findViewById(R.id.rec_UpdateDemographics_ac_de_up_ac);

        initData();
        peopleAdapter = new PeopleAdapter(peopleList);
        recyclerView.setAdapter(peopleAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Implement search functionality here if needed
                return false;
            }
        });

        return view;
    }

    private void initData() {
        peopleList = new ArrayList<>();
        // Add Person objects to the peopleList
        // You can replace the R.mipmap.ic_launcher with your own profile images
        peopleList.add(new Person("John Doe", R.mipmap.ic_launcher));
        peopleList.add(new Person("Jane Smith", R.mipmap.ic_launcher));
        // ... add more people as needed
    }
}