package com.example.catastrophecompass.UILayer.TeamOrganizor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.DataLayer.Model.Team;
import com.example.catastrophecompass.R;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {
    private List<Team> teamList;
    private Context context;

    public TeamAdapter(List<Team> teamList, Context context) {
        this.teamList = teamList;
        this.context = context;
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        public TextView teamName;
        public Button editButton;

        public TeamViewHolder(View view) {
            super(view);
            teamName = view.findViewById(R.id.txt_teamName_to_te_it);
            editButton = view.findViewById(R.id.btn_teamEditButton_to_te_it);
        }
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.to_team_item, parent, false);
        return new TeamViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TeamViewHolder holder, int position) {
        final Team team = teamList.get(position);
        holder.teamName.setText(team.getTeamName());

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TeamOrganizorAddEditActivity.class);
                intent.putExtra("team", team); // here we put the Team object to the intent
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }
}
