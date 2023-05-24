package com.example.catastrophecompass.DataLayer.Dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.catastrophecompass.DataLayer.Model.RecentChatItem;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface ChatFragmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable pushToLocal(RecentChatItem recentChatItem);

    /**
     * Returns last added item
     */
    @Query("SELECT * FROM RecentChatItem ORDER BY time DESC LIMIT 1")
    Flowable<RecentChatItem> getRecentChats();
}
