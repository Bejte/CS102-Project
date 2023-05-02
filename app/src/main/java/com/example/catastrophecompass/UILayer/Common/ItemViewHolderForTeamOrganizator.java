package com.example.catastrophecompass.UILayer.Common;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;
import com.example.catastrophecompass.UILayer.TeamOrganizor.SmallItemFragment;

public class ItemViewHolderForTeamOrganizator extends RecyclerView.ViewHolder {
    private TextView textView;
    private FrameLayout fragmentContainer;
    private ImageButton editButton;

    public ItemViewHolderForTeamOrganizator(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.txt_teamName_to_te_it);
        fragmentContainer = itemView.findViewById(R.id.fr_İncludingSmallİtem_to_te_it);
        editButton = itemView.findViewById(R.id.btn_teamEditButton_to_te_it);
    }

    public void bind(final ItemDataForTeamOrganizator itemDataForTeamOrganizator) {
        textView.setText(itemDataForTeamOrganizator.getText());

        // Add the small fragment in the fragmentContainer
        // Replace 'YourSmallFragment' with the actual small fragment class
        FragmentTransaction transaction = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fr_İncludingSmallİtem_to_te_it, new SmallItemFragment());
        transaction.commit();

        editButton.setOnClickListener(v -> {
            // Handle edit button click
        });
    }
}