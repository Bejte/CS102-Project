package com.example.catastrophecompass.UILayer.Common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;

import java.util.List;

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.OrganizationViewHolder> {

    private List<Organization> organizationList;

    public OrganizationAdapter(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    @NonNull
    @Override
    public OrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_organization, parent, false);
        return new OrganizationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrganizationViewHolder holder, int position) {
        Organization organization = organizationList.get(position);
        holder.nameTextView.setText(organization.getName());
        holder.typeTextView.setText(organization.getType());
    }

    @Override
    public int getItemCount() {
        return organizationList.size();
    }

    static class OrganizationViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView typeTextView;

        OrganizationViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.organization_name);
            typeTextView = itemView.findViewById(R.id.organization_type);
        }
    }
}
