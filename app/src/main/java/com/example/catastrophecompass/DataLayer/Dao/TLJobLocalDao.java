package com.example.catastrophecompass.DataLayer.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.catastrophecompass.DataLayer.Model.TeamInfo;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface TLJobLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertTeamInfo(TeamInfo teamInfo);

    @Query("SELECT * FROM TeamInfo")
    Flowable<TeamInfo> getTeamInfo();

}
