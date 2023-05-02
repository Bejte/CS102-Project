package com.example.catastrophecompass.UILayer.Common;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UpdateTeamsAdapter extends RecyclerView.Adapter<UpdateTeamsAdapter.ViewHolder> {

    private List<String> types;

    public UpdateTeamsAdapter() {
        // Initialize the list of types
        types = new ArrayList<>();
        types.add("Type 1");
        types.add("Type 2");
        types.add("Type 3");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for the item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_update_team, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind the data to the item
        holder.typeEditText.setText(types.get(position));
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the list
        return types.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private EditText typeEditText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Get references to the views in the item layout
            typeEditText = itemView.findViewById(R.id.typeEditText);
        }
    }
}