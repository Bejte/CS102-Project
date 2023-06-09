package com.example.catastrophecompass.DataLayer.FBRepository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catastrophecompass.DataLayer.LocalRepository.ChatFragmentLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.RecentChatItem;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFragmentFBRepo
{

    ChatFragmentLocalRepo localRepo;

    public ChatFragmentFBRepo(ChatFragmentLocalRepo localRepo) {
        this.localRepo = localRepo;
    }

    public void setupDBConnection(String userName)
    {
        ArrayList<String>[] chatted = new ArrayList[1];
        ArrayList<String>[] combined = new ArrayList[1];

        DatabaseReference chattedRef = FirebaseDatabase.getInstance().getReference("Chats").child("Chatted").child(userName);
        DatabaseReference chatListRef = FirebaseDatabase.getInstance().getReference("ChatList");
        chattedRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot name : snapshot.getChildren())
                {
                    String userName = name.getKey();
                    chatted[0].add(userName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        for(String name: chatted[0])
        {
            String combinedStr;
            if(name.compareTo(userName)<0)
            {
                combinedStr = name+userName;
            }
            else
            {
                combinedStr = userName+name;
            }
            combined[0].add(combinedStr);
        }

        chatListRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for(DataSnapshot data:snapshot.getChildren())
                {
                    if(combined[0].contains(data.getKey()))
                    {
                        int smallestTime = -1;
                        DataSnapshot recentChat = data;
                        for(DataSnapshot chat: data.getChildren())
                        {
                            if(smallestTime < chat.child("time").getValue(Integer.class))
                            {
                                smallestTime = chat.child("time").getValue(Integer.class);
                                recentChat = chat;
                            }
                        }
                        Log.d("ChatFragmentFBRepo", "pushToLocal() RecentChatItem.class");
                        localRepo.pushToLocal(recentChat.getValue(RecentChatItem.class));
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
