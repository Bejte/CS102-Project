package com.example.catastrophecompass.UILayer.Common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;

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

    @Override                          // I don't know what exactly "SuppressLint" does but it fixed the error (Emir)
    public void onBindViewHolder(@NonNull ViewHolderForPlace holder, @SuppressLint("RecyclerView") int position) {
        PlaceItem placeItem = placeItems.get(position);
        holder.placeTextView.setText("Place: " + placeItem.getPlace());
        holder.addressTextView.setText("Address: " + placeItem.getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assign(Logistics,Request,this);
                //TODO logistic commona atılacak dendi
            }
        });
    }

    @Override
    public int getItemCount() {
        return placeItems.size();
    }
}