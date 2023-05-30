package com.example.catastrophecompass.DataLayer.FBRepository;

public class ChatActivityFBRepo
{
    ChatActivityLocalRepo localRepo;

    public ChatActivityFBRepo(ChatActivityLocalRepo localRepo) {
        this.localRepo = localRepo;
    }

    public void setupDBConnection(String userName, String chattedUserName)
    {

    }

    public boolean sendMessage(ChatItem chatItem)
    {
        boolean[] status = {true};
        return status[0];
    }
}
