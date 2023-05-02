package com.example.catastrophecompass.UILayer.Common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NeedItemAdapter extends RecyclerView.Adapter<NeedItemAdapter.NeedItemViewHolder> {

    private List<NeedItem> needItems;

    public NeedItemAdapter(List<NeedItem> needItems) {
        this.needItems = needItems;
    }

    @NonNull
    @Override
    public NeedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.need_item, parent, false);
        return new NeedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NeedItemViewHolder holder, int position) {
        NeedItem needItem = needItems.get(position);
        holder.typeOfNeed.setText(needItem.getTypeOfNeed());
        holder.urgency.setText(needItem.getUrgency());
    }

    @Override
    public int getItemCount() {
        return needItems.size();
    }
    public static class NeedItemViewHolder extends RecyclerView.ViewHolder {

        TextView typeOfNeed;
        TextView urgency;

        public NeedItemViewHolder(@NonNull View itemView) {
            super(itemView);
            typeOfNeed = itemView.findViewById(R.id.need_item_aid_material);
            urgency = itemView.findViewById(R.id.need_item_urgency);
        }
    }
}
