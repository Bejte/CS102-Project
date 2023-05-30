package com.example.catastrophecompass.UILayer.HQOrganizer.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.Common.Organization;
import com.example.catastrophecompass.UILayer.Common.OrganizationAdapter;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class AddEditOrganizationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button doneButton;
    private RadioGroup radioGroup;
    private OrganizationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_organization);

        recyclerView = findViewById(R.id.rec_JobTeamOrganizator_fr_jo_in_fo_te_or_fr);
        doneButton = findViewById(R.id.btn_doneButton_ac_ad_ed_me_ac);
        radioGroup = findViewById(R.id.rdg_organizationType_ac_ad_ed_or_ac);

        List<Organization> sampleOrganizations = new ArrayList<>();


        adapter = new OrganizationAdapter(sampleOrganizations);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                int index = radioGroup.indexOfChild(selectedRadioButton);

                Intent intent = new Intent(AddEditOrganizationActivity.this, AddEditOrganizationActivity.class);
                intent.putExtra("selectedRadioButtonIndex", index);
                startActivity(intent);
            }
        });
    }
}