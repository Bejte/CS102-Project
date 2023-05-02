package com.example.catastrophecompass.UILayer.Common;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;


import android.content.Context;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private List<String> jobList;
    private LayoutInflater inflater;
    private OnJoinButtonClickListener onJoinButtonClickListener;

    public JobAdapter(Context context, List<String> jobList, OnJoinButtonClickListener onJoinButtonClickListener) {
        this.jobList = jobList;
        this.inflater = LayoutInflater.from(context);
        this.onJoinButtonClickListener = onJoinButtonClickListener;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.job_item, parent, false);
        return new JobViewHolder(itemView, onJoinButtonClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        String currentJob = jobList.get(position);
        holder.jobTextView.setText(currentJob);
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public interface OnJoinButtonClickListener {
        void onJoinButtonClick(int position);
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView jobTextView;
        private Button joinButton;
        private OnJoinButtonClickListener onJoinButtonClickListener;

        public JobViewHolder(@NonNull View itemView, OnJoinButtonClickListener onJoinButtonClickListener) {
            super(itemView);
            jobTextView = itemView.findViewById(R.id.tv_job_name);
            joinButton = itemView.findViewById(R.id.btn_join);
            this.onJoinButtonClickListener = onJoinButtonClickListener;
            joinButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onJoinButtonClickListener.onJoinButtonClick(getAdapterPosition());
        }
    }
}
