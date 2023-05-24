package com.example.catastrophecompass.DataLayer.Dao;

import androidx.room.Insert;

import com.example.catastrophecompass.DataLayer.Model.ChatItem;

import io.reactivex.rxjava3.core.Completable;

public interface ChatActivityDao {

    @Insert
    Completable pushToLocal(ChatItem chatItem);

    /*
     Todo: getMessages() -> Fetch only recent message but also get every message when
                            user reenters the app. You may solve it via WHERE time > :lastFetchedTime
                            and start with 0, updating it each time.
     */

}