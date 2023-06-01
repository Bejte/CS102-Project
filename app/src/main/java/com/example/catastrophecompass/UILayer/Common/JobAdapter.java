package com.example.catastrophecompass.UILayer.Common;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.catastrophecompass.DataLayer.Model.Job;
import com.example.catastrophecompass.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.catastrophecompass.R;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private List<Job> jobs;
    private OnJoinClickListener onJoinClickListener;

    public JobAdapter(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void setOnJoinClickListener(OnJoinClickListener onJoinClickListener) {
        this.onJoinClickListener = onJoinClickListener;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item, parent, false);
        return new JobViewHolder(v, onJoinClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobs.get(position);
        holder.jobTitle.setText(job.getTeamName()); // Assuming the job object has a getTitle() method
        // Populate other fields as necessary
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public interface OnJoinClickListener {
        void onJoinClick(int position);
    }

    static class JobViewHolder extends RecyclerView.ViewHolder {

        TextView jobTitle;
        Button joinButton;

        public JobViewHolder(@NonNull View itemView, final OnJoinClickListener onJoinClickListener) {
            super(itemView);

            //jobTitle = itemView.findViewById(R.id.); // replace with actual ID
            //joinButton = itemView.findViewById(R.id.join_button); // replace with actual ID

            joinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onJoinClickListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            onJoinClickListener.onJoinClick(position);
                        }
                    }
                }
            });
        }
    }
}
