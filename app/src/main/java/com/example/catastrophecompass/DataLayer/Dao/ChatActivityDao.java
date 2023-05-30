package com.example.catastrophecompass.DataLayer.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.catastrophecompass.DataLayer.Model.ChatItem;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ChatActivityDao {

    @Insert
    Completable pushToLocal(ChatItem chatItem);

    /*
     Todo: getMessages() -> Fetch only recent message but also get every message when
                            user reenters the app. You may solve it via WHERE time > :lastFetchedTime
                            and start with 0, updating it each time.
     */

    @Query("SELECT * FROM ChatItem WHERE time > :lastMessageTime ORDER BY time DESC LIMIT 1")
    Flowable<List<ChatItem>> getMessages(int lastMessageTime);

    @Query("SELECT time FROM ChatItem ORDER BY time DESC LIMIT 1")
    Single<Integer> getLatest();



}