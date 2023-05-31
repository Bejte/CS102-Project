package com.example.catastrophecompass.UILayer.HQOrganizer.Admin;

import androidx.fragment.app.Fragment;

public class IfFieldOrganizerFragment extends Fragment {

    private CheckBox adminCheckBox;
    private CheckBox notAdminCheckBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_if_field_organizer, container, false);

        adminCheckBox = view.findViewById(R.id.admin_checkBox);
        notAdminCheckBox = view.findViewById(R.id.not_admin_checkBox);

        return view;
    }
}
