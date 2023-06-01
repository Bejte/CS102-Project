package com.example.catastrophecompass.UILayer.HQOrganizer.Admin;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.Organization;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.OrganizationViewHolder> {

    private List<Organization> organizationList;
    private Context context;

    public OrganizationAdapter(Context context, List<Organization> organizationList) {
        this.context = context;
        this.organizationList = organizationList;
    }

    @NonNull
    @Override
    public OrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_organization, parent, false);
        return new OrganizationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrganizationViewHolder holder, int position) {
        Organization organization = organizationList.get(position);
        holder.organizationName.setText(organization.getName());

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddEditOrganizationActivity.class);
//                intent.putExtra("Organization", organization);
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return organizationList.size();
    }

    public static class OrganizationViewHolder extends RecyclerView.ViewHolder {
        TextView organizationName;
        Button editButton;

        public OrganizationViewHolder(@NonNull View itemView) {
            super(itemView);
            organizationName = itemView.findViewById(R.id.txt_organization_name_it_or_it);
            editButton = itemView.findViewById(R.id.button_edit_organization_type_it_or_it);
        }
    }
}