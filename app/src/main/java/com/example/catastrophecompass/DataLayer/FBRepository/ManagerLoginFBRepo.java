package com.example.catastrophecompass.DataLayer.FBRepository;

import com.example.catastrophecompass.DataLayer.Model.User;
import com.example.catastrophecompass.DataLayer.Model.UserLogin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ManagerLoginFBRepo {

    private static DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("UserList");
    int id;

    public User validateLogin(UserLogin userLogin) throws ExecutionException, InterruptedException {
            CompletableFuture<List<User>> future = new CompletableFuture<>();

            databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<User> users = new ArrayList<>();

                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String userName = userSnapshot.getKey();
                        String userType = userSnapshot.child("userType").getValue(String.class);

                        User newUser = new User(userName, userType);//?
                        users.add(newUser);
                    }

                    future.complete(users);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    future.completeExceptionally(databaseError.toException());
                }
            });

            List<User> users = (List<User>) future;

            if(checkCredentials(userLogin.getUserName(), userLogin.getPassword()))
            {
                for(User u:users)
                {
                    if(u.getUserName().equals(userLogin.getUserName()))
                    {
                        return u;
                    }
                }
            }
            return null;
    }

    public static boolean checkCredentials(String username, String password) throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        boolean result;

        databaseRef.child(username).child("password").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String storedPassword = dataSnapshot.getValue(String.class);
                    boolean isPasswordCorrect = storedPassword.equals(password);
                    future.complete(isPasswordCorrect);
                } else {
                    future.complete(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });

        try {
            // Wait for the result with a timeout of 5 seconds
            result = future.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            // Handle any exceptions that occur
            result = false;
            e.printStackTrace();
        }

        return result;
    }
}
