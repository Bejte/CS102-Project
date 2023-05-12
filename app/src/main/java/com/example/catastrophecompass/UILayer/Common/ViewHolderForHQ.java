package com.example.catastrophecompass.UILayer.Common;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catastrophecompass.R;

public class ViewHolderForHQ extends RecyclerView.ViewHolder {
    public EditText driverNameEditText;
    public EditText sizeEditText;
    public EditText statusEditText;
    public Button assignButton;
    public LinearLayout firstLinearLayout;
    public LinearLayout secondLinearLayout;

    public ViewHolderForHQ(@NonNull View itemView) {
        super(itemView);

        driverNameEditText = itemView.findViewById(R.id.driver_name_edit_text);
        sizeEditText = itemView.findViewById(R.id.size_edit_text);
        statusEditText = itemView.findViewById(R.id.status_edit_text);
        assignButton = itemView.findViewById(R.id.assign_button);
        firstLinearLayout = itemView.findViewById(R.id.first_linear_layout);
        secondLinearLayout = itemView.findViewById(R.id.second_linear_layout);
    }
}