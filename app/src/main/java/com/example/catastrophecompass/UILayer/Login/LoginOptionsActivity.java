package com.example.catastrophecompass.UILayer.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import com.example.catastrophecompass.R;

public class LoginOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the FragmentLoginVolunteerType
        FragmentLoginVolunteerType fragment = new FragmentLoginVolunteerType();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_main, fragment); // "fragment_container" is the id of FrameLayout inside your layout
        transaction.commit();

    }
}