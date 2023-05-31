package com.example.catastrophecompass.UILayer.Login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.VolunteerInBase.VIBStartActivity;

public class FragmentLoginEnterCode extends Fragment {

    private EnterCodeVM viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_enter_code, container, false);
        viewModel = new ViewModelProvider(this).get(EnterCodeVM.class);

        EditText etCode = view.findViewById(R.id.et_code);
        Button btnSubmitCode = view.findViewById(R.id.btn_submit_code);
        final String[] placeName = new String[1];

        btnSubmitCode.setOnClickListener(v -> {
            String code = etCode.getText().toString();
            placeName[0] = viewModel.validateCode(code);
            if (placeName[0] != null) {
                navigateToVIBStartPage(placeName[0]);
            } else {
                warnUser();
            }
        });

        return view;
    }

    private void navigateToVIBStartPage(String placeName) {
        Intent intent = new Intent(getActivity(), VIBStartActivity.class);
        intent.putExtra("placeName", placeName);
        startActivity(intent);
    }

    private void warnUser() {
        Toast.makeText(getActivity(), "Invalid code, please try again.", Toast.LENGTH_LONG).show();
    }
}