package com.example.catastrophecompass.UILayer.Common.Chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.catastrophecompass.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private List<Chat> chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.rec_chat_ac);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initChatList();
        chatAdapter = new ChatAdapter(this, chatList);
        recyclerView.setAdapter(chatAdapter);
    }

    private void initChatList() {
        // You can replace this with actual data
        chatList = new ArrayList<>();
        chatList.add(new Chat("John Doe", "Hello!", 3, R.mipmap.ic_launcher_round));
        chatList.add(new Chat("Jane Smith", "How are you?", 1, R.mipmap.ic_launcher_round));
    }
}
