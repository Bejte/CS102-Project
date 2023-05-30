package com.example.catastrophecompass.DataLayer.FBRepository;

import com.google.firebase.database.*;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EnterCodeFBRepo {
    private static DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("Codes");

    public static String validateCode(String code) {
        HashMap<String, String>[] codeMap = new HashMap[1];
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String value = childSnapshot.getValue(String.class);
                    codeMap[0].put(key, value);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        if(codeMap[0].containsKey(code))
        {
            return codeMap[0].get(code);
        }
        return "invalid code";
    }
}
