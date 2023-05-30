package com.example.catastrophecompass.RemoteDataRepository.VectorDatabaseRepo;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.catastrophecompass.DataLayer.Model.InventoryList;
import com.example.catastrophecompass.RemoteDataRepository.VectorDatabaseRepo.VectorModels.PineconeUpsertFailedResponse;
import com.example.catastrophecompass.RemoteDataRepository.VectorDatabaseRepo.VectorModels.PineconeUpsertSucceedResponse;
import com.example.catastrophecompass.RemoteDataRepository.VectorDatabaseRepo.VectorModels.VectorUpsertRequest;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class VectorDatabaseRepo {

    VectorDatabaseApiService apiService;

    public VectorDatabaseRepo() {
        this.apiService = RetrofitClientForVectorDB.createService();
    }

    @SuppressLint("CheckResult")
    public boolean updateAidStatusInfo(InventoryList list) {
        VectorUpsertRequest request = parseRequest(list);
        final boolean[] status = {false};

        apiService.upsertVectors(request)
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

    public VectorUpsertRequest parseRequest(InventoryList list) {
        return null;
    }
}
