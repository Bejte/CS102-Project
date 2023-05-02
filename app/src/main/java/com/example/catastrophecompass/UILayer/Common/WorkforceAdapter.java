package com.example.catastrophecompass.UILayer.Common;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;

import java.util.List;

public class WorkforceAdapter extends RecyclerView.Adapter<WorkforceAdapter.ViewHolder> {

    private final List<WorkforceItem> workforceItems;
    private final OnWorkforceItemClickListener onWorkforceItemClickListener;

    public WorkforceAdapter(List<WorkforceItem> workforceItems, OnWorkforceItemClickListener onWorkforceItemClickListener) {
        this.workforceItems = workforceItems;
        this.onWorkforceItemClickListener = onWorkforceItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_witem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(workforceItems.get(position), onWorkforceItemClickListener);
    }

    @Override
    public int getItemCount() {
        return workforceItems.size();
    }

    public interface OnWorkforceItemClickListener {
        void onWorkforceItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView placeName, placeAddress, placeTemperature;
        private ImageView weatherIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.witem_place_name);
            placeAddress = itemView.findViewById(R.id.witem_address);
            placeTemperature = itemView.findViewById(R.id.witem_temp);
            weatherIcon = itemView.findViewById(R.id.witem_img_logo);

        }

        public void bind(WorkforceItem workforceItem, OnWorkforceItemClickListener onWorkforceItemClickListener) {

            placeName.setText(workforceItem.getName());
            placeAddress.setText(workforceItem.getAddress());
            placeTemperature.setText(workforceItem.getDegree());

            // for choosing the icon. Extend the switch case later on
            switch (workforceItem.getWeatherType()) {
                case "PARTIAL_SUNNY": weatherIcon.setImageResource(R.drawable.sunny_logo);
            }

            itemView.setOnClickListener(v -> onWorkforceItemClickListener.onWorkforceItemClick(getAdapterPosition()));
        }
    }
}