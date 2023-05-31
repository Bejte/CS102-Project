package com.example.catastrophecompass.UILayer.TeamOrganizor;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.ItemAdapterForTeamOrganizator;
import com.example.catastrophecompass.UILayer.Common.ItemDataForTeamOrganizator;
import com.example.catastrophecompass.UILayer.HQOrganizer.Admin.AddEditMemberActivity;
import com.example.catastrophecompass.UILayer.HQOrganizer.Admin.AddEditOrganizationActivity;
import com.example.catastrophecompass.UILayer.TeamLeader.TeamAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JobInfoForTeamOrganizatorFragment#} factory method to
 * create an instance of this fragment.
 */
public class JobInfoForTeamOrganizatorFragment extends Fragment {
    Button btnAddOrganization;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general_info, container, false);

        // find the button from the view
        btnAddOrganization = view.findViewById(R.id.add_organization_button_admin);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.organization_recycler_view_hq);
        TeamAdapter adapter = new TeamAdapter(yourTeamList, this);
        recyclerView.setAdapter(adapter);


        // set a click listener for the button
        btnAddOrganization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create an intent to open the activity
                Intent intent = new Intent(getActivity(), AddEditOrganizationActivity.class);

                startActivity(intent);
            }
        });


        return view;
    }
}
