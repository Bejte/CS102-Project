package com.example.catastrophecompass.RemoteDataRepository.VectorDatabaseRepo;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.catastrophecompass.DataLayer.Model.FieldOrganization;
import com.example.catastrophecompass.DataLayer.Model.InventoryList;
import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;
import com.example.catastrophecompass.DataLayer.RemoteDataRepository.CloudFunctionRepo.CloudRestApi;
import com.example.catastrophecompass.RemoteDataRepository.VectorDatabaseRepo.VectorModels.PineconeUpsertFailedResponse;
import com.example.catastrophecompass.RemoteDataRepository.VectorDatabaseRepo.VectorModels.PineconeUpsertSucceedResponse;
import com.example.catastrophecompass.RemoteDataRepository.VectorDatabaseRepo.VectorModels.Vector;
import com.example.catastrophecompass.RemoteDataRepository.VectorDatabaseRepo.VectorModels.VectorUpsertRequest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class VectorDatabaseRepo {

    VectorDatabaseApiService vectorDatabaseApiService;
    CloudRestApi cloudRestApi;

    @Inject
    public VectorDatabaseRepo(CloudRestApi cloudRestApi) {
        this.vectorDatabaseApiService = RetrofitClientForVectorDB.createService();
        this.cloudRestApi = cloudRestApi;
    }

    @SuppressLint("CheckResult")
    public boolean updateAidStatusInfo(InventoryList list, String organizationName) {
        VectorUpsertRequest request = parseRequest(list, organizationName);
        final boolean[] status = {false};

        vectorDatabaseApiService.upsertVectors(request)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("VectorDatabaseRepo", "upsertVectors onSubscribe: ");

                    }

                    @Override
                    public void onSuccess(@NonNull Object o) {
                        Log.d("VectorDatabaseRepo", "upsertVectors onSuccess: ");

                        if (o instanceof PineconeUpsertSucceedResponse) {
                            status[0] = true;
                        }

                        if (o instanceof PineconeUpsertFailedResponse) {
                            String message = ((PineconeUpsertFailedResponse) o).getMessage();
                            Log.d("VectorDatabaseRepo", "onSuccess called yet error exists: " + message);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("VectorDatabaseRepo", "upsertVectors onError: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
        return status[0];
    }

    public VectorUpsertRequest parseRequest(InventoryList list, String name) {

        // create Vector object
        Vector vector = new Vector();

        List<Float> values = new ArrayList<>(8);
        values.add( (float) list.getFood() );
        values.add( (float) list.getHeater() );
        values.add( (float) list.getManCloth() );
        values.add( (float) list.getWomanCloth() );
        values.add( (float) list.getChildCloth() );
        values.add( (float) list.getHygene() );
        values.add( (float) list.getKitchenMaterial() );
        values.add( (float) list.getPowerbank() );

        vector.setValues(values);
        vector.setId(name);

        // create request

        VectorUpsertRequest request = new VectorUpsertRequest();

        List<Vector> vectors = new ArrayList<>(1);
        vectors.add(vector);

        request.setVectors(vectors);

        return request;
    }

    public boolean syncVectorDB(LogisticInfo driver, FieldOrganization dropPlace, InventoryList fieldList) {
        //Todo: reduce driver list from dropPlace
        InventoryList newList = new InventoryList();

        int food = fieldList.getFood() - driver.getInventoryList().getFood();
        newList.setFood(food);

        int heater = fieldList.getHeater() - driver.getInventoryList().getHeater();
        newList.setHeater(heater);

        int manCloth = fieldList.getManCloth() - driver.getInventoryList().getManCloth();
        newList.setManCloth(manCloth);

        int womanCloth = fieldList.getWomanCloth() - driver.getInventoryList().getWomanCloth();
        newList.setWomanCloth(womanCloth);

        int childCloth = fieldList.getChildCloth() - driver.getInventoryList().getChildCloth();
        newList.setChildCloth(childCloth);

        int hygene = fieldList.getHygene() - driver.getInventoryList().getHygene();
        newList.setHygene(hygene);

        int kitchenMaterial = fieldList.getKitchenMaterial() - driver.getInventoryList().getKitchenMaterial();
        newList.setKitchenMaterial(kitchenMaterial);

        int powerBank = fieldList.getPowerbank() - driver.getInventoryList().getPowerbank();
        newList.setPowerbank(powerBank);

        updateAidStatusInfo(newList, dropPlace.getName());
        Log.d("VectorDatabaseRepo", "syncVectorDB: it reaches to the statement after of updateAidStatusInfo, sequential nature works properly. ");

        boolean status = cloudRestApi.decideDropPlace(driver.getInventoryList(), driver.getGetName());

        return status;
    }
}
