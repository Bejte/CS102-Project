package com.example.catastrophecompass.DataLayer.FBRepository;

import com.google.firebase.database.*;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EnterCodeFBRepo {
    private static DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("Codes");

    public static CompletableFuture<HashMap<String, String>> getCodeMap() {
        CompletableFuture<HashMap<String, String>> future = new CompletableFuture<>();

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, String> codeMap = new HashMap<>();

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String value = childSnapshot.getValue(String.class);
                    codeMap.put(key, value);
                }

                future.complete(codeMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });

        return future;
    }

    public static CompletableFuture<String> validateCodeCompletable(String code) {
        CompletableFuture<HashMap<String, String>> codeMapFuture = getCodeMap();

        return codeMapFuture.thenApply(codeMap -> {
            if (codeMap.containsKey(code)) {
                return codeMap.get(code);
            } else {
                return null;
            }
        });
    }

    public static String validateCode(String code) throws ExecutionException, InterruptedException {
        CompletableFuture<String> futureResult = validateCodeCompletable(code);

        String value = futureResult.get();
        return value;
    }
}
