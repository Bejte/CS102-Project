package com.example.catastrophecompass.DataLayer.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DataLayer.Model.FilePath;
import com.example.catastrophecompass.DataLayer.Model.VIBJobInfo;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface VIBDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable recordCredentials(Credentials credentials);

    @Query("SELECT id FROM Credentials")
    Flowable<List<Integer>> getIdAsList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable pushToLocal(VIBJobInfo vibJobInfo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable recordFilePath(FilePath filePath);

    @Query("SELECT * FROM VIBJobInfo")
    Flowable<VIBJobInfo> getJobInfo();

    @Query("SELECT filePath FROM FilePath")
    Flowable<String> getPicturePath();

    @Query("DELETE FROM VIBJobInfo")
    Completable deleteVIBJobInfo();

    @Query("DELETE FROM FilePath")
    Completable deleteTLPicPath();

    @Query("SELECT * FROM Credentials")
    Single<Credentials> getCredentials();



}
