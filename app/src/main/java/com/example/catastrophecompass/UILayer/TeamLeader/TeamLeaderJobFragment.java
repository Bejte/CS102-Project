package com.example.catastrophecompass.UILayer.TeamLeader;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.UpdateTeamsAdapter;

import io.reactivex.rxjava3.annotations.NonNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamLeaderJobFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamLeaderJobFragment extends Fragment {

    private Button updateTeamsButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jobfragment_for_appbar_tl, container, false);

        updateTeamsButton = view.findViewById(R.id.update_teams_button);
        updateTeamsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TeamUpdateActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}