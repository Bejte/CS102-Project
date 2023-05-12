package com.example.catastrophecompass.UILayer.Common;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;

public class ViewHolderForPlace extends RecyclerView.ViewHolder {
    TextView placeTextView;
    TextView addressTextView;

    public ViewHolderForPlace(@NonNull View itemView) {
        super(itemView);
        placeTextView = itemView.findViewById(R.id.place_text_view);
        addressTextView = itemView.findViewById(R.id.address_text_view);
    }
}