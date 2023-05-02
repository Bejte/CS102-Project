package com.example.catastrophecompass.UILayer.Common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UpdateDemographicsAdapter extends RecyclerView.Adapter<UpdateDemographicsAdapter.ViewHolder> {
    private List<String> labelTexts;

    public UpdateDemographicsAdapter(List<String> labelTexts) {
        this.labelTexts = labelTexts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_update_demographics, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textLabel.setText(labelTexts.get(position));
    }

    @Override
    public int getItemCount() {
        return labelTexts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textLabel;
        EditText editText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textLabel = itemView.findViewById(R.id.text_label);
            editText = itemView.findViewById(R.id.edit_text);
        }
    }
}