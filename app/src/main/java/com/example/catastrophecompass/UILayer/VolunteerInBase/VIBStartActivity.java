package com.example.catastrophecompass.UILayer.VolunteerInBase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.JobAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {
    private TextView tvPlaceName;
    private RecyclerView recyclerViewJobs;
    private TextView tvReassignQuestion;
    private Switch switchReassign;
    private JobAdapter jobAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibstart);

        // Get the scanned QR code data from the intent extra
        String qrData = getIntent().getStringExtra("qrData");
        if (qrData != null) {
            String[] qrDataArray = qrData.split("\\|");
            String placeName = qrDataArray[0];
            String jobs = qrDataArray[1];

            tvPlaceName = findViewById(R.id.tv_place_name);
            tvPlaceName.setText(placeName);

            List<String> jobList = Arrays.asList(jobs.split(","));

            recyclerViewJobs = findViewById(R.id.recycler_view_jobs);
            recyclerViewJobs.setLayoutManager(new LinearLayoutManager(this));

            jobAdapter = new JobAdapter(ThirdActivity.this, jobList, new JobAdapter.OnJoinButtonClickListener() {
                @Override
                public void onJoinButtonClick(int position) {
                    String teamName = jobList.get(position);
                    String teamDescription = "Team description goes here"; // Replace with the actual team description

                    Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
                    intent.putExtra("teamName", teamName);
                    intent.putExtra("teamDescription", teamDescription);
                    startActivity(intent);
                }
            });
            recyclerViewJobs.setAdapter(jobAdapter);

            tvReassignQuestion = findViewById(R.id.tv_reassign_question);
            switchReassign = findViewById(R.id.switch_reassign);
        } else {
            Toast.makeText(this, "QR data not found", Toast.LENGTH_SHORT).show();
            finish();
        }

        // TODO: Parse the QR code data and extract the relevant information

        // Set up the UI elements
        tvPlaceName = findViewById(R.id.tv_place_name);
        recyclerViewJobs = findViewById(R.id.recycler_view_jobs);
        tvReassignQuestion = findViewById(R.id.tv_reassign_question);
        switchReassign = findViewById(R.id.switch_reassign);

        // Set the place name from the QR code data
        // For example, let's assume the QR data contains a string with format "place_name|job1,job2,job3"
        String[] qrDataArray = qrData.split("\\|");
        String placeName = qrDataArray[0];
        String jobs = qrDataArray[1];

        // Set the text of the place name TextView
        tvPlaceName.setText(placeName);

        // Create a job list from the QR code data
        List<String> jobList = Arrays.asList(jobs.split(","));

        // Set up the RecyclerView with the job list
        recyclerViewJobs.setLayoutManager(new LinearLayoutManager(this));
        jobAdapter = new JobAdapter(ThirdActivity.this, jobList, new JobAdapter.OnJoinButtonClickListener() {
            @Override
            public void onJoinButtonClick(int position) {
                String teamName = jobList.get(position);
                String teamDescription = "Team description goes here"; // Replace with the actual team description

                Intent intent2 = new Intent(ThirdActivity.this, FourthActivity.class);
                startActivity(intent2);

                Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
                intent.putExtra("teamName", teamName);
                intent.putExtra("teamDescription", teamDescription);
                startActivity(intent);
            }
        });
        recyclerViewJobs.setAdapter(jobAdapter);
    }
}