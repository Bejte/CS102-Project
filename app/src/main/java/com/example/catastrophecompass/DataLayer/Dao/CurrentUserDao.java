package com.example.catastrophecompass.DataLayer.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.catastrophecompass.DataLayer.Model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface CurrentUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable recordUser(User user);

    @Query("SELECT * from User")
    Flowable<List<User>> getCurrentUser();

    @Query("SELECT userName FROM User")
    Single<String> getDriverName();

}
