package com.example.catastrophecompass.DataLayer.FBRepository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catastrophecompass.DataLayer.LocalRepository.ChatActivityLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.ChatItem;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivityFBRepo
{
    ChatActivityLocalRepo localRepo;

    public ChatActivityFBRepo(ChatActivityLocalRepo localRepo) {
        this.localRepo = localRepo;
    }

    public void setupDBConnection(String userName, String chattedUserName)
    {
        String combinedName;
        if(userName.compareTo(chattedUserName) < 0)
            combinedName = userName+chattedUserName;
        else
            combinedName = chattedUserName+userName;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Chats").child(combinedName);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ChatItem item = snapshot.getValue();
                localRepo.pushToLocal(item);
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

    public boolean sendMessage(ChatItem chatItem, String userName, String chattedUserName)
    {
        boolean[] status = {false};
        String combinedName;
        if(userName.compareTo(chattedUserName) < 0)
            combinedName = userName+chattedUserName;
        else
            combinedName = chattedUserName+userName;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Chats").child(combinedName);
        ref.push().setValue(chatItem).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                status[0] = true;
            }
        });
        return status[0];
    }
}
