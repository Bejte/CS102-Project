package com.example.Firebase;

import com.example.Model.RequestForGCP.InventoryList;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

import java.util.Map;

public interface FirebaseRestApi {

    @GET("FieldOrganizations/{fieldName}/arrivingAid.json")
    Single<InventoryList> getInventory(@Path("fieldName") String fieldName);

    @PATCH("FieldOrganizations/{fieldName}/arrivingAid.json")
    Single<Object> updateInventory(@Path("fieldName") String user, @Body InventoryList updatedList);

    @PATCH("Logistics/{driverName}.json")
    Single<Object> updateDriverStatus(@Path("driverName") String driver, @Body Map<String, String> status);

    @PATCH("Logistics/{driverName}.json")
    Single<Object> updateDriverGetStatus(@Path("driverName") String driver, @Body Map<String, Boolean> status);

    @PATCH("Logistics/{driverName}.json")
    Single<Object> updateDropAddress(@Path("driverName") String driver, @Body Map<String, String> drop);
}

