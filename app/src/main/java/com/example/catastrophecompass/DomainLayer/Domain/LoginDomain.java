package com.example.catastrophecompass.DomainLayer.Domain;

public class LoginDomain {
    private CurrentUserDao currentUserDao;
    private VIBDao vibDao;

    public LoginDomain(CurrentUserDao currentUserDao, VIBDao vibDao) {
        this.currentUserDao = currentUserDao;
        this.vibDao = vibDao;
    }

    public boolean isLoggedIn(){
        //TODO Using Single or simple return
    }
}
