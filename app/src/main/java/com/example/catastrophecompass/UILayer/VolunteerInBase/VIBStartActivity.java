package com.example.catastrophecompass.UILayer.VolunteerInBase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.UILayer.Common.JobAdapter;
import android.content.Intent;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.VIBSelectionVM;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;
import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.VIBSelectionVM;

public class VIBStartActivity extends AppCompatActivity {

    private VIBSelectionVM vibSelectionVM;
    private RecyclerView recyclerView;
    private TextView placeName;
    private TextView jobsAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibstart);

        placeName = findViewById(R.id.vib_place_name_vib_start);
        jobsAvailable = findViewById(R.id.vib_jobs_available_vib_start);

        recyclerView = findViewById(R.id.rec_teams_vib_start_ac);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtain ViewModel from ViewModelProviders
        vibSelectionVM = new ViewModelProvider(this).get(VIBSelectionVM.class);

        // Get job data from your ViewModel
        // TO DO
        List<Job> jobs = vibSelectionVM.fetchJobList(city, place);

        JobAdapter jobAdapter = new JobAdapter(jobs);
        recyclerView.setAdapter(jobAdapter);

        jobAdapter.setOnJoinClickListener(new JobAdapter.OnJoinClickListener() {
            @Override
            public void onJoinClick(int position) {

                vibSelectionVM.updateJobUrgency(city, place, teamName);
                vibSelectionVM.recordCredentials(credentials);

                Intent intent = new Intent(VIBStartActivity.this, VIBHomeActivity.class);


                startActivity(intent);
            }
        });
    }
}