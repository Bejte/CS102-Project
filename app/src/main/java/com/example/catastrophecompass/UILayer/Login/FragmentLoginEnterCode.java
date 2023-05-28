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
import com.example.catastrophecompass.UILayer.Common.EnterCodeViewModel;
import com.example.catastrophecompass.UILayer.OutsideVolunteer.OVMainActivity;
import com.example.catastrophecompass.UILayer.VolunteerInBase.VIBStartActivity;

public class FragmentLoginEnterCode extends Fragment {

    private EnterCodeViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_enter_code, container, false);
        viewModel = new ViewModelProvider(this).get(EnterCodeViewModel.class);

        EditText etCode = view.findViewById(R.id.et_code);
        Button btnSubmitCode = view.findViewById(R.id.btn_submit_code);

        btnSubmitCode.setOnClickListener(v -> {
            String code = etCode.getText().toString();
            viewModel.validateCode(code);
        });

        viewModel.getPlaceName().observe(getViewLifecycleOwner(), placeName -> {
            if (placeName != null) {
                navigateToVIBStartPage(placeName);
            } else {
                warnUser();
            }
        });

        return view;

        if(List<User> .length == 0){

        }
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