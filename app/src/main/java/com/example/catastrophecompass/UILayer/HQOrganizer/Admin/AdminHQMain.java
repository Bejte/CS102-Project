package com.example.catastrophecompass.UILayer.HQOrganizer.Admin;

import androidx.appcompat.app.AppCompatActivity;

import com.example.catastrophecompass.R;

public class AdminHQMain extends AppCompatActivity {

    private RecyclerView organizationRecyclerView;
    private OrganizationAdapter organizationAdapter;
    private List<Organization> organizationList;  // initialize this list with your data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_general_info); // your xml file for this activity

        organizationRecyclerView = findViewById(R.id.organization_recycler_view_hq);
        organizationRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        organizationAdapter = new OrganizationAdapter(this, organizationList);
        organizationRecyclerView.setAdapter(organizationAdapter);
    }
    // ... other code ...
}
