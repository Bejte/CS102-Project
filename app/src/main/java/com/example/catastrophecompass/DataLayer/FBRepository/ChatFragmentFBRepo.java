package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;

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

        chatListRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren())
                {
                    if(combined[0].contains(data.getKey()))
                    {
                        //getLastMessage pushToLocal??
                        //TODO
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
