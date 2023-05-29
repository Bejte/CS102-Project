package com.example.catastrophecompass.DataLayer.LocalRepository;

import com.example.catastrophecompass.DataLayer.Dao.VIBDao;
import com.example.catastrophecompass.DataLayer.LocalDB;
import com.example.catastrophecompass.DataLayer.Model.VIBJobInfo;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class VIBAreaLocalRepo {

    private LocalDB db;
    private VIBDao vibDao;

    @Inject
    public VIBAreaLocalRepo(LocalDB db) {
        this.db = db;
        vibDao = db.vibDao();
    }

    public Flowable<VIBJobInfo> getJobInfo() {
        return vibDao.getJobInfo();
    }


}
