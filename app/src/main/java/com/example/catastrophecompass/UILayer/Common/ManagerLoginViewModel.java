package com.example.catastrophecompass.UILayer.Common;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.User;
import com.example.catastrophecompass.DataLayer.Model.UserLogin;

public class ManagerLoginViewModel extends ViewModel {

    private MutableLiveData<User> user = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginFailure = new MutableLiveData<>();

    public LiveData<User> getUser() {
        return user;
    }

    public LiveData<Boolean> getLoginFailure() {
        return loginFailure;
    }

///    public void validateLogin(UserLogin userLogin) {
        // Implement the logic to validate the login here.
        // This might involve interacting with your Firebase repository to check if the login credentials are correct,
        // then updating the user MutableLiveData with the result.

        // For now, I'll use a placeholder implementation.
   //     if (userLogin.getUsername().equals("manager") && userLogin.getPassword().equals("password")) {
     //       user.setValue(new User(userLogin.getUsername(), "Manager"));
      //  } else {
       //     loginFailure.setValue(true);
       // }
   // }
}