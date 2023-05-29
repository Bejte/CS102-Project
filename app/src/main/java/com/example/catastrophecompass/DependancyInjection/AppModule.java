package com.example.catastrophecompass.DependancyInjection;


import android.content.Context;

import androidx.room.Room;

import com.example.catastrophecompass.DataLayer.Dao.ChatActivityDao;
import com.example.catastrophecompass.DataLayer.Dao.ChatFragmentDao;
import com.example.catastrophecompass.DataLayer.Dao.CurrentUserDao;
import com.example.catastrophecompass.DataLayer.Dao.FieldOrganizationDao;
import com.example.catastrophecompass.DataLayer.Dao.MissionDao;
import com.example.catastrophecompass.DataLayer.Dao.TLJobLocalDao;
import com.example.catastrophecompass.DataLayer.Dao.VIBDao;
import com.example.catastrophecompass.DataLayer.LocalDB;
import com.example.catastrophecompass.DataLayer.LocalRepository.TLJobLocalRepo;
import com.example.catastrophecompass.DataLayer.LocalRepository.VIBAreaLocalRepo;
import com.example.catastrophecompass.DataLayer.LocalRepository.VIBLocalRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {


    // DI FOR LocalDB & DAOs

    @Provides
    @Singleton
    public LocalDB provideDB(@ApplicationContext Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                LocalDB.class, "LocalDB").fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public ChatActivityDao provideChatActDao(LocalDB db) {
        return db.chatActivityDao();
    }

    @Provides
    @Singleton
    public ChatFragmentDao provideChatFDao(LocalDB db) {
        return db.chatFragmentDao();
    }

    @Provides
    @Singleton
    public CurrentUserDao provideCurrUserDao(LocalDB db) {
        return db.currentUserDao();
    }

    @Provides
    @Singleton
    public FieldOrganizationDao provideFieldOrgDao(LocalDB db) {
        return db.fieldOrganizationDao();
    }

    @Provides
    @Singleton
    public MissionDao provideMissionDao(LocalDB db) {
        return db.missionDao();
    }

    @Provides
    @Singleton
    public TLJobLocalDao provideTLDao(LocalDB db) {
        return db.tlJobLocalDao();
    }

    @Provides
    @Singleton
    public VIBDao provideVibDao(LocalDB db) {
        return db.vibDao();
    }

    // DI FOR LOCAL REPOs

    @Provides
    @Singleton
    public VIBLocalRepo provideVibLocalRepo(LocalDB db, VIBTLProfilePicL vibtlProfilePicL) {
        return new VIBLocalRepo(db, vibtlProfilePicL);
    }

    @Provides
    @Singleton
    public VIBAreaLocalRepo provideVibAreaLocalRepo(LocalDB db) {
        return new VIBAreaLocalRepo(db);
    }

    @Provides
    @Singleton
    public TLJobLocalRepo provideTlJobLocalRepo(LocalDB db) {
        return new TLJobLocalRepo(db);
    }




}
