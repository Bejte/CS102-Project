package com.example.catastrophecompass.DataLayer.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MissionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable recordLogisticInfo(LogisticInfo logisticInfo);

    @Query("SELECT * FROM LogisticInfo")
    Flowable<LogisticInfo> getLogisticInfo();


}
