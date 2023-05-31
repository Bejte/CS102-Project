package com.example.catastrophecompass.UILayer.Common.Chat;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.DataLayer.Model.Contact;
import com.example.catastrophecompass.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PeopleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.widget.SearchView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.catastrophecompass.UILayer.Common.PeopleAdapter;
import com.example.catastrophecompass.UILayer.Common.Person;
import com.example.catastrophecompass.UILayer.HQOrganizer.Admin.AddEditMemberActivity;

public class PeopleFragment extends Fragment {

    private ContactsVM contactsVM;
    private String organizationName = "YourOrganizationName";
    private String userName = "YourUserName";
    private String userType = "YourUserType";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_people);

        contactsVM = new ViewModelProvider(this).get(ContactsVM.class);

        List<Contact> contacts = contactsVM.getContacts(organizationName, userName);

        PeopleAdapter peopleAdapter = new PeopleAdapter(contacts, userType, contactsVM, getActivity());
        recyclerView.setAdapter(peopleAdapter);

        return view;
    }

}