package com.example.catastrophecompass.UILayer.Common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterForPlace extends RecyclerView.Adapter<ViewHolderForPlace> {
    private Context context;
    private List<PlaceItem> placeItems;
    private OnPlaceItemClickListener onPlaceItemClickListener;

    public interface OnPlaceItemClickListener {
        void onPlaceItemClick(int position);
    }

    public RecyclerViewAdapterForPlace(Context context, List<PlaceItem> placeItems, OnPlaceItemClickListener onPlaceItemClickListener) {
        this.context = context;
        this.placeItems = placeItems;
        this.onPlaceItemClickListener = onPlaceItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolderForPlace onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_card_view_item_hq, parent, false);
        return new ViewHolderForPlace(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForPlace holder, int position) {
        PlaceItem placeItem = placeItems.get(position);
        holder.placeTextView.setText("Place: " + placeItem.getPlace());
        holder.addressTextView.setText("Address: " + placeItem.getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlaceItemClickListener.onPlaceItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return placeItems.size();
    }
}