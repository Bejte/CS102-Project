package com.example.catastrophecompass.UILayer.Login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import static androidx.fragment.app.FragmentManager.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.room.Insert;

import com.example.catastrophecompass.DomainLayer.Domain.LoginDomain;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.Disposable;
@HiltViewModel
public class LoginVM extends ViewModel {

    private LoginDomain loginDomain;

    @Inject
    public LoginVM(LoginDomain loginDomain) {
        this.loginDomain = loginDomain;
    }

    public boolean IsLoggedIn(){
        Log.d("LoginVM", "isLoggedIn() called");
        return loginDomain.isLoggedIn();
    }

    public void killDataFlow(){
        Log.d("LoginVM", "killDataFlow() called");
        loginDomain.killDataFlow();
    }
}