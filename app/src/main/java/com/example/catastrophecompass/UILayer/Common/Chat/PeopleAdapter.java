package com.example.catastrophecompass.UILayer.Common.Chat;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.DataLayer.Model.Contact;
import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.HQOrganizer.Admin.AddEditMemberActivity;

import java.util.List;
import java.util.function.Consumer;

import io.reactivex.rxjava3.annotations.NonNull;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private List<Contact> contactList;
    private String userType;
    private ContactsVM contactsVM;
    private FragmentActivity activity;

    public PeopleAdapter(List<Contact> contactList, String userType, ContactsVM contactsVM, FragmentActivity activity) {
        this.contactList = contactList;
        this.userType = userType;
        this.contactsVM = contactsVM;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.menuImageView.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(v.getContext(), holder.menuImageView);
            popup.getMenuInflater().inflate(R.menu.menu_people_item, popup.getMenu());

            MenuItem editItem = popup.getMenu().findItem(R.id.action_edit);
            editItem.setVisible("Admin".equals(userType));

            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        Intent intent = new Intent(activity, AddEditMemberActivity.class);
                        // Pass the contact information to the next activity
                        intent.putExtra("contact_name", contact.getName());
                        // Add more information as needed
                        activity.startActivity(intent);
                        return true;
                    case R.id.action_contact:
                        if (contactsVM.startChat(userName, contact.getName())) {
                            SingleChatFragment singleChatFragment = SingleChatFragment.newInstance(contact);
                            activity.getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, singleChatFragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                        return true;
                    default:
                        return false;
                }
            });

            popup.show();
        });
    }
    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class PeopleViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        ImageView menuImageView;

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.txt_name_people_name);
            menuImageView = itemView.findViewById(R.id.menu);
        }
    }
}