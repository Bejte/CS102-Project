package com.example.catastrophecompass.UILayer.Common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    private List<Person> peopleList;

    public PeopleAdapter(List<Person> peopleList) {
        this.peopleList = peopleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_people, parent, false);
        //return new ViewHolder(view);
        return null;
    }

    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person person = peopleList.get(position);
        holder.name.setText(person.getName());
        holder.profileImage.setImageResource(person.getProfileImage());
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView menu;
        CircleImageView profileImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //name = itemView.findViewById(R.id.name);
            menu = itemView.findViewById(R.id.menu);
            profileImage = itemView.findViewById(R.id.profile_image);
        }
    }
}