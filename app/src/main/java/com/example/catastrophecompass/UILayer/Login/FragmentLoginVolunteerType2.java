package com.example.catastrophecompass.UILayer.Login;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.catastrophecompass.R;

public class FragmentLoginVolunteerType2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_volunteer_type2, container, false);

        Button btnWhatCanIDo = view.findViewById(R.id.btn_what_can_I_do_login2_fr);
        Button btnIAmInField = view.findViewById(R.id.btn_I_am_infield_login2_fr);

        btnWhatCanIDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentLoginHelpType newFragment = new FragmentLoginHelpType();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_main, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnIAmInField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentLoginEnterCode newFragment = new FragmentLoginEnterCode();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_main, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}