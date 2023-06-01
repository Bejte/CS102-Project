package com.example.catastrophecompass.DependancyInjection;


import com.example.catastrophecompass.DataLayer.Dao.ChatActivityDao;
import com.example.catastrophecompass.DataLayer.Dao.ChatFragmentDao;
import com.example.catastrophecompass.DataLayer.Dao.CurrentUserDao;
import com.example.catastrophecompass.DataLayer.Dao.FieldOrganizationDao;
import com.example.catastrophecompass.DataLayer.Dao.MissionDao;
import com.example.catastrophecompass.DataLayer.Dao.TLJobLocalDao;
import com.example.catastrophecompass.DataLayer.Dao.VIBDao;
import com.example.catastrophecompass.DataLayer.FBRepository.AddOrganizationFBRepo;
import com.example.catastrophecompass.DataLayer.FBRepository.ChatActivityFBRepo;
import com.example.catastrophecompass.DataLayer.FBRepository.ChatFragmentFBRepo;
import com.example.catastrophecompass.DataLayer.FBRepository.FieldOrganizatonInfoFBRepo;
import com.example.catastrophecompass.DataLayer.FBRepository.LogisticMissionFBRepo;
import com.example.catastrophecompass.DataLayer.FBRepository.ManagerLoginFBRepo;
import com.example.catastrophecompass.DataLayer.FBRepository.OVWorkforceListFBRepo;
import com.example.catastrophecompass.DataLayer.FBRepository.TLJobFBRepo;
import com.example.catastrophecompass.DataLayer.FBRepository.VIBFBRepo;
import com.example.catastrophecompass.DataLayer.LocalDB;
import com.example.catastrophecompass.DataLayer.LocalRepository.ChatActivityLocalRepo;
import com.example.catastrophecompass.DataLayer.LocalRepository.ChatFragmentLocalRepo;
import com.example.catastrophecompass.DataLayer.LocalRepository.FieldOrganizationInfoLocalRepo;
import com.example.catastrophecompass.DataLayer.LocalRepository.LogisticMissionLocalRepo;
import com.example.catastrophecompass.DataLayer.LocalRepository.TLJobLocalRepo;
import com.example.catastrophecompass.DataLayer.LocalRepository.VIBAreaLocalRepo;
import com.example.catastrophecompass.DataLayer.LocalRepository.VIBLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.ChatItem;
import com.example.catastrophecompass.DataLayer.RemoteDataRepository.WeatherRepository.WeatherAPI;
import com.example.catastrophecompass.DomainLayer.Domain.ChatActivityDomain;
import com.example.catastrophecompass.DomainLayer.Domain.ChatFragmentDomain;
import com.example.catastrophecompass.DomainLayer.Domain.FieldOrganizationDomain;
import com.example.catastrophecompass.DomainLayer.Domain.LoginDomain;
import com.example.catastrophecompass.DomainLayer.Domain.LogisticMissionDomain;
import com.example.catastrophecompass.DomainLayer.Domain.ManagerLoginUC;
import com.example.catastrophecompass.DomainLayer.Domain.OVWorkforceListUC;
import com.example.catastrophecompass.DomainLayer.Domain.TLJobInfoDomain;
import com.example.catastrophecompass.DomainLayer.Domain.VIBAreaDomain;
import com.example.catastrophecompass.DomainLayer.Domain.VIBJobDomain;
import com.example.catastrophecompass.RemoteDataRepository.CloudFunctionRepo.CloudRestApi;
import com.example.catastrophecompass.RemoteDataRepository.VectorDatabaseRepo.VectorDatabaseRepo;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {


    // DI FOR DAOs

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
    public VIBLocalRepo provideVibLocalRepo(LocalDB db) {
        return new VIBLocalRepo(db);
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

    @Provides
    @Singleton
    public FieldOrganizationInfoLocalRepo provideFieldOrganizationInfoLocalRepo(LocalDB db) {
        return new FieldOrganizationInfoLocalRepo(db);
    }

    @Provides
    @Singleton
    public LogisticMissionLocalRepo provideLogisticMissionLocalRepo(LocalDB db) {
        return new LogisticMissionLocalRepo(db);
    }

    @Provides
    @Singleton
    public ChatFragmentLocalRepo provideChatFragmentLocalRepo(LocalDB db) {
        return new ChatFragmentLocalRepo(db);
    }

    @Provides
    @Singleton
    public ChatActivityLocalRepo provideChatActivityLocalRepo( LocalDB db) {
        return new ChatActivityLocalRepo( db);
    }

    @Provides
    @Singleton
    public WeatherAPI provideWAPİ() {
        return new WeatherAPI();
    }

    // Domain

    @Provides
    @Singleton
    public ChatActivityDomain provideChatActivityDomain(ChatActivityLocalRepo localRepo, ChatActivityFBRepo FBRepo) {
        return new ChatActivityDomain(localRepo, FBRepo);
    }

    @Provides
    @Singleton
    public ChatFragmentDomain provideChatFragmentDomain(ChatFragmentLocalRepo localRepo, ChatFragmentFBRepo FBRepo) {
        return new ChatFragmentDomain(localRepo, FBRepo);
    }

    @Provides
    @Singleton
    public FieldOrganizationDomain provideFieldOrganizationDomain(FieldOrganizationInfoLocalRepo localRepo, FieldOrganizatonInfoFBRepo FBRepo, VectorDatabaseRepo vectorRepo) {
        return new FieldOrganizationDomain(localRepo, FBRepo, vectorRepo);
    }

    @Provides
    @Singleton
    public LoginDomain provideLoginDomain(CurrentUserDao currentUserDao, VIBDao vibDao) {
        return new LoginDomain(currentUserDao, vibDao);
    }

    @Provides
    @Singleton
    public LogisticMissionDomain provideLogisticMissionDomain(LogisticMissionLocalRepo localRepo, LogisticMissionFBRepo FBRepo, CloudRestApi restAPI) {
        return new LogisticMissionDomain(localRepo, FBRepo, restAPI);
    }

    @Provides
    @Singleton
    public ManagerLoginUC provideManagerLoginUC(ManagerLoginFBRepo FBRepo, LocalDB db) {
        return new ManagerLoginUC(FBRepo, db);
    }





    @Provides
    @Singleton
    public OVWorkforceListUC provideOVWorkforceListUC(OVWorkforceListFBRepo repo, WeatherAPI weather) {
        return new OVWorkforceListUC(repo, weather);
    }


    @Provides
    @Singleton
    public TLJobInfoDomain provideTLJobInfoDomain(TLJobLocalRepo localRepo, TLJobFBRepo FBRepo) {
        return new TLJobInfoDomain(localRepo, FBRepo);
    }


    @Provides
    @Singleton
    public VIBAreaDomain provideVibAreaDomain(VIBAreaLocalRepo localRepo, VIBFBRepo FBRepo, WeatherAPI weather) {
        return new VIBAreaDomain(localRepo, FBRepo, weather);
    }



    @Provides
    @Singleton
    public VIBJobDomain provideVıbJobDomain(VIBLocalRepo localRepo, VIBFBRepo FBRepo) {
        return  new VIBJobDomain(localRepo, FBRepo);
    }

    // cloud + vector


    @Provides
    @Singleton
    public CloudRestApi provideCloudRestApi() {
        return new CloudRestApi();
    }



    @Provides
    @Singleton
    public VectorDatabaseRepo provideVBDR(CloudRestApi cloudRestApi) {
        return new VectorDatabaseRepo(cloudRestApi);
    }


    // FB Repo
    @Provides
    @Singleton
    public FieldOrganizatonInfoFBRepo provideFieldOrganizatonInfoFBRepo(FieldOrganizationInfoLocalRepo localRepo) {
        return new FieldOrganizatonInfoFBRepo(localRepo);
    }

//    @Provides
//    @Singleton
//    public AddOrganizationFBRepo
//
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public
//
//    @Provides
//    @Singleton
//    public












}
