package com.example.catastrophecompass.UILayer.TeamLeader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private List<String> teamList;

    public TeamAdapter(List<String> teamList) {
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_update_team_item, parent, false);
//        return new TeamViewHolder(itemView);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        String team = teamList.get(position);
        holder.edtTeamType.setText(team);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    static class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView txtTeamType;
        EditText edtTeamType;

        TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTeamType = itemView.findViewById(R.id.txt_TeamType_it_up_te_it);
            edtTeamType = itemView.findViewById(R.id.edt_TeamType_it_up_te_it);
        }
    }
}