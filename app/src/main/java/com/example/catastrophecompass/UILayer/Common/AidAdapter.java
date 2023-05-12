package com.example.catastrophecompass.UILayer.Common;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;

import java.util.List;

public class AidAdapter extends RecyclerView.Adapter<AidAdapter.ViewHolder> {

    private final AidItem [] aidItems;
    private final OnAidItemClickListener onAidItemClickListener;

    public AidAdapter(AidItem [] aidItems, OnAidItemClickListener onAidItemClickListener) {
        this.aidItems = aidItems;
        this.onAidItemClickListener = onAidItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(aidItems[position], onAidItemClickListener);
    }

    @Override
    public int getItemCount() {
        return aidItems.length;
    }

    public interface OnAidItemClickListener {
        void onAidItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView placeName, address, mostUrgent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.aitem_place_name);
            address = itemView.findViewById(R.id.aitem_address);
            mostUrgent = itemView.findViewById(R.id.aitem_most_urgent);

        }

        public void bind(AidItem aidItem, OnAidItemClickListener onAidItemClickListener) {
            placeName.setText(aidItem.getName());
            address.setText(aidItem.getAddress());
            mostUrgent.setText(aidItem.getMostUrgentItem());
            itemView.setOnClickListener(v -> onAidItemClickListener.onAidItemClick(getAdapterPosition()));
        }
    }
