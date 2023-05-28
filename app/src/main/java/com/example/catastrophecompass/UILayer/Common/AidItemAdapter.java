package com.example.catastrophecompass.UILayer.Common;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;

import java.util.List;

public class AidItemAdapter extends RecyclerView.Adapter<AidItemAdapter.AidItemViewHolder> {
    private List<AidItem> aidItemList;
    private OnAidItemClickListener onAidItemClickListener;

    public AidItemAdapter(List<AidItem> aidItemList, OnAidItemClickListener listener) {
        this.aidItemList = aidItemList;
        this.onAidItemClickListener = listener;
    }

    @NonNull
    @Override
    public AidItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ov_aid_item, parent, false);
        return new AidItemViewHolder(itemView, onAidItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AidItemViewHolder holder, int position) {
        AidItem currentItem = aidItemList.get(position);
        holder.placeNameTextView.setText(currentItem.getName());
        holder.addressTextView.setText(currentItem.getAddress());
        holder.mostUrgentTextView.setText(currentItem.getMostUrgentItem());
    }

    @Override
    public int getItemCount() {
        return aidItemList.size();
    }

    public static class AidItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView placeNameTextView;
        private TextView addressTextView;
        private TextView mostUrgentTextView;
        private OnAidItemClickListener onAidItemClickListener;

        public AidItemViewHolder(@NonNull View itemView, OnAidItemClickListener listener) {
            super(itemView);
            placeNameTextView = itemView.findViewById(R.id.txt_place_name_ov_ai_it);
            addressTextView = itemView.findViewById(R.id.txt_address_info_ov_ai_it);
            mostUrgentTextView = itemView.findViewById(R.id.txt_most_urgent_ov_aid_it);
            this.onAidItemClickListener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onAidItemClickListener.onAidItemClick(getAdapterPosition());
        }
    }

    public interface OnAidItemClickListener {
        void onAidItemClick(int position);
    }
}
