package com.example.catastrophecompass.UILayer.Common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private List<String> cityList;
    private OnCityItemClickListener onCityItemClickListener;

    public CityAdapter(List<String> cityList, OnCityItemClickListener onCityItemClickListener) {
        this.cityList = cityList;
        this.onCityItemClickListener = onCityItemClickListener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ov_city_item, parent, false);
        return new CityViewHolder(itemView, onCityItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.cityTextView.setText(cityList.get(position));
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public void updateCities(List<String> newCityList) {
        this.cityList = newCityList;
        notifyDataSetChanged();
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView cityTextView;
        private OnCityItemClickListener onCityItemClickListener;

        public CityViewHolder(@NonNull View itemView, OnCityItemClickListener onCityItemClickListener) {
            super(itemView);

            cityTextView = itemView.findViewById(R.id.txt_city_item_ov_ci_it);
            this.onCityItemClickListener = onCityItemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCityItemClickListener.onCityItemClick(getAdapterPosition());
        }
    }

    public interface OnCityItemClickListener {
        void onCityItemClick(int position);
    }
}