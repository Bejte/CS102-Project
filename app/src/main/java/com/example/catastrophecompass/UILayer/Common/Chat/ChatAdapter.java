package com.example.catastrophecompass.UILayer.Common.Chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private Context context;
    private List<Chat> chatList;

    public ChatAdapter(Context context, List<Chat> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        holder.sender.setText(chat.getSender());
        holder.lastMessage.setText(chat.getLastMessage());
        holder.unreadCount.setText(String.valueOf(chat.getUnreadCount()));
        holder.profileImage.setImageResource(chat.getProfileImage());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView sender;
        private TextView lastMessage;
        private TextView unreadCount;
        private CircleImageView profileImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.people_name);
            lastMessage = itemView.findViewById(R.id.last_message);
            unreadCount = itemView.findViewById(R.id.unread_count);
            profileImage = itemView.findViewById(R.id.profile_image);
        }
    }
}