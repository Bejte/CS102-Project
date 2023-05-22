package com.example.catastrophecompass.DomainLayer.Domain;

public class ManagerLoginUC {
    private ManagerLoginFBRepo FBRepo;
    private CurrentUserDao currentUserDao;

    public ManagerLoginUC(ManagerLoginFBRepo FBRepo, CurrentUserDao currentUserDao) {
        this.FBRepo = FBRepo;
        this.currentUserDao = currentUserDao;
    }

    public String validateLogin(UserLogin userLogin){
        User user = FBRepo.validateLogin(userLogin);
        if (user!=null)
        {
            currentUserDao.recordUser(user);
            return user.getUserType();
        }
        else
            return null;
    }
}
