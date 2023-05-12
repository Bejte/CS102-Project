package com.example.catastrophecompass.UILayer.Common;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.FieldOrganizer.HousingItem;

import java.util.List;

public class HousingItemAdapter extends RecyclerView.Adapter<HousingItemAdapter.ViewHolder> {

    private List<HousingItem> housingItems;

    public HousingItemAdapter(List<HousingItem> housingItems) {
        this.housingItems = housingItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_housing, parent, false);
        Log.d("HousingItemAdapter", "ViewHolder created");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HousingItem currentItem = housingItems.get(position);

        holder.title.setText(currentItem.getTitle());
        holder.value.setText(currentItem.getValue());
        Log.d("HousingItemAdapter", "Item bound: " + currentItem.getTitle() + " - " + currentItem.getValue());
    }

    @Override
    public int getItemCount() {
        return housingItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public EditText value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            value = itemView.findViewById(R.id.value);
        }
    }
}