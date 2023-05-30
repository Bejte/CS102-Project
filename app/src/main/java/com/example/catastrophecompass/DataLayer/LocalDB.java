package com.example.catastrophecompass.DataLayer;


import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.catastrophecompass.DataLayer.Dao.ChatActivityDao;
import com.example.catastrophecompass.DataLayer.Dao.ChatFragmentDao;
import com.example.catastrophecompass.DataLayer.Dao.CurrentUserDao;
import com.example.catastrophecompass.DataLayer.Dao.FieldOrganizationDao;
import com.example.catastrophecompass.DataLayer.Dao.MissionDao;
import com.example.catastrophecompass.DataLayer.Dao.TLJobLocalDao;
import com.example.catastrophecompass.DataLayer.Dao.VIBDao;
import com.example.catastrophecompass.DataLayer.Model.ChatItem;
import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DataLayer.Model.DemographicInfo;
import com.example.catastrophecompass.DataLayer.Model.FilePath;
import com.example.catastrophecompass.DataLayer.Model.HousingInfo;
import com.example.catastrophecompass.DataLayer.Model.InventoryList;
import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;
import com.example.catastrophecompass.DataLayer.Model.RecentChatItem;
import com.example.catastrophecompass.DataLayer.Model.TeamInfo;
import com.example.catastrophecompass.DataLayer.Model.User;
import com.example.catastrophecompass.DataLayer.Model.VIBJobInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Database(entities = {ChatItem.class, RecentChatItem.class, User.class, FilePath.class,
        DemographicInfo.class, HousingInfo.class, InventoryList.class, Credentials.class,
        VIBJobInfo.class, TeamInfo.class, LogisticInfo.class}, version = 1, exportSchema = false)
public abstract class LocalDB extends RoomDatabase {


    private static volatile LocalDB instance;

    public abstract ChatActivityDao chatActivityDao();
    public abstract ChatFragmentDao chatFragmentDao();
    public abstract CurrentUserDao currentUserDao();
    public abstract FieldOrganizationDao fieldOrganizationDao();
    public abstract MissionDao missionDao();
    public abstract TLJobLocalDao tlJobLocalDao();
    public abstract VIBDao vibDao();

    @Module
    @InstallIn(SingletonComponent.class)
    public static class Factory {
        @Singleton
        @Provides
        public static LocalDB provideLocalDB(Application application) {
            if (instance == null) {
                instance = Room.databaseBuilder(application.getApplicationContext(),
                                LocalDB.class, "LocalDB")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build();
            }
            return instance;
        }

        private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                new PopulateDBAsyncTask(instance.chatActivityDao()).execute();
            }
        };
    }

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {

        private ChatActivityDao dao;

        @Inject
        public PopulateDBAsyncTask(ChatActivityDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // For newly installed app having 0 lastMessageTime (see ChatActivityDao)
            ChatItem chatItem = new ChatItem();
            chatItem.setTime(0);
            dao.pushToLocal(chatItem);
            return null;
        }
    }

}
