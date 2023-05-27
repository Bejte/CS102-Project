package com.example.catastrophecompass.UILayer.Login;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;

import com.example.catastrophecompass.R;

public class FragmentLoginVolunteerType extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_volunteer_type, container, false);

        Button btnVolunteer = view.findViewById(R.id.btn_volunteer_login_fr);
        Button btnManager = view.findViewById(R.id.btn_manager_login_fr);

        btnVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentLoginVolunteerType2 nextFragment = new FragmentLoginVolunteerType2();
                getFragmentManager().beginTransaction().replace(R.id.fragment_main, nextFragment).addToBackStack(null).commit();
            }
        });

        btnManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManagerLoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}