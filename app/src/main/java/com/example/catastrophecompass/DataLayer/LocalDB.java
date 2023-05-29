package com.example.catastrophecompass.DataLayer;


import androidx.room.Database;
import androidx.room.RoomDatabase;

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

@Database(entities = {ChatItem.class, RecentChatItem.class, User.class, FilePath.class,
        DemographicInfo.class, HousingInfo.class, InventoryList.class, Credentials.class,
        VIBJobInfo.class, TeamInfo.class, LogisticInfo.class}, version = 1, exportSchema = false)
public abstract class LocalDB extends RoomDatabase {

    public abstract ChatActivityDao chatActivityDao();
    public abstract ChatFragmentDao chatFragmentDao();
    public abstract CurrentUserDao currentUserDao();
    public abstract FieldOrganizationDao fieldOrganizationDao();
    public abstract MissionDao missionDao();
    public abstract TLJobLocalDao tlJobLocalDao();
    public abstract VIBDao vibDao();

}
