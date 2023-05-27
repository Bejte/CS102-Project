package com.example.catastrophecompass.UILayer.Login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.OutsideVolunteer.OVFirstActivity;
import com.example.catastrophecompass.UILayer.OutsideVolunteer.OVMainActivity;

public class FragmentLoginHelpType extends Fragment {
    // ... Other methods and lifecycle callbacks here

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_help_type, container, false);

        Button btnSeePlacesWorkforce = view.findViewById(R.id.btn_see_places_workforce_login_fr);
        Button btnSeePlacesAid = view.findViewById(R.id.btn_see_places_aid_login_fr);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OVFirstActivity.class);
                startActivity(intent);
            }
        };

        btnSeePlacesWorkforce.setOnClickListener(clickListener);
        btnSeePlacesAid.setOnClickListener(clickListener);

        return view;
    }
}