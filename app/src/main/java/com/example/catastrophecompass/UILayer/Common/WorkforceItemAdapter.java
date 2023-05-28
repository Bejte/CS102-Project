package com.example.catastrophecompass.UILayer.Common;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.catastrophecompass.R;

public class WorkforceItemAdapter extends RecyclerView.Adapter<WorkforceItemAdapter.WorkforceViewHolder> {
    private List<WItem> wItemList;
    OVWorkforceListViewModel ovvm;
    private OnWorkforceItemClickListener onWorkforceItemClickListener;

    public WorkforceItemAdapter(List<WItem> wItemList, OnWorkforceItemClickListener listener) {

        this.wItemList=ovvm.getWItemList();
        this.onWorkforceItemClickListener = listener;
    }

    @NonNull
    @Override
    public WorkforceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ov_workforce_item, parent, false);
        return new WorkforceViewHolder(itemView, onWorkforceItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkforceViewHolder holder, int position) {
        WItem currentItem = wItemList.get(position);
        holder.placeNameTextView.setText(currentItem.getName());
        holder.addressTextView.setText(currentItem.getAddress());
        holder.temperatureTextView.setText(currentItem.getCurrentTemp());
        // holder.weatherImageView.setImageResource(...); // Set the image based on the weather condition
    }

    @Override
    public int getItemCount() {
        return wItemList.size();
    }

    public static class WorkforceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView placeNameTextView;
        private TextView temperatureTextView;
        private ImageView weatherImageView;
        private TextView addressTextView;
        private OnWorkforceItemClickListener onWorkforceItemClickListener;

        public WorkforceViewHolder(@NonNull View itemView, OnWorkforceItemClickListener listener) {
            super(itemView);
            placeNameTextView = itemView.findViewById(R.id.txt_place_name_ov_wo_it);
            temperatureTextView = itemView.findViewById(R.id.txt_tempeture_ov_wo_it);
            weatherImageView = itemView.findViewById(R.id.img_sunny_logo_ov_wo_it);
            addressTextView = itemView.findViewById(R.id.txt_address_info_ov_wo_it);
            this.onWorkforceItemClickListener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onWorkforceItemClickListener.onWorkforceItemClick(getAdapterPosition());
        }
    }

    public interface OnWorkforceItemClickListener {
        void onWorkforceItemClick(int position);
    }
}