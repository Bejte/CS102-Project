package com.example.catastrophecompass.UILayer.Common;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;

import java.util.List;

public class RecyclerViewAdapterForHQ extends RecyclerView.Adapter<ViewHolderForHQ> {
    private Context context;
    private List<TruckItemForHQ> truckItems;

    public RecyclerViewAdapterForHQ(Context context, List<TruckItemForHQ> truckItems) {
        this.context = context;
        this.truckItems = truckItems;
    }

    @NonNull
    @Override
    public ViewHolderForHQ onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hq_card_view_item, parent, false);
        //return new ViewHolderForHQ(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForHQ holder, int position) {
        TruckItemForHQ truckItem = truckItems.get(position);
        holder.driverNameEditText.setText(truckItem.getDriverName());
        holder.sizeEditText.setText(truckItem.getSize());
        holder.statusEditText.setText(truckItem.getStatus());
        holder.assignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(context, HQPlacesForTrucksActivity.class);
                //context.startActivity(intent);
            }
        });
        // TODO: Set OnClickListener for the Assign button here
    }

    @Override
    public int getItemCount() {
        return truckItems.size();
    }
}