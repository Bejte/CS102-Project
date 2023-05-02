package com.example.catastrophecompass.UILayer.TeamOrganizor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JobInfoForTeamOrganizatorFragment#} factory method to
 * create an instance of this fragment.
 */
public class JobInfoForTeamOrganizatorFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    public JobInfoForTeamOrganizatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_info_for_team_organizator, container, false);

        // Initialize TeamOrganizatorMainGraph fragment
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.team_organizator_main_graph, new TeamOrganizatorMainGraph());
        transaction.commit();

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Create a list with some items
        List<ItemDataForTeamOrganizator> items = new ArrayList<>();
        items.add(new ItemDataForTeamOrganizator("Item 1"));
        items.add(new ItemDataForTeamOrganizator("Item 2"));
        items.add(new ItemDataForTeamOrganizator("Item 3"));

        // Set the adapter for the RecyclerView
        ItemAdapterForTeamOrganizator itemAdapterForTeamOrganizator = new ItemAdapterForTeamOrganizator(items);
        recyclerView.setAdapter(itemAdapterForTeamOrganizator);

        // Initialize FloatingActionButton
        fab = view.findViewById(R.id.fab_add);
        fab.setOnClickListener(v -> {
            // Add item logic
        });

        return view;
    }
}
