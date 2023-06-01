package com.example.catastrophecompass.UILayer.Common;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;

import java.util.List;

public class ItemAdapterForTeamOrganizator extends RecyclerView.Adapter<ItemViewHolderForTeamOrganizator> {
    private List<ItemDataForTeamOrganizator> items;

    public ItemAdapterForTeamOrganizator(List<ItemDataForTeamOrganizator> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolderForTeamOrganizator onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teamor parent, false);
       // return new ItemViewHolderForTeamOrganizator(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolderForTeamOrganizator holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}