package com.example.catastrophecompass.RemoteDataRepository.CloudFunctionRepo;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CloudApiService {

    @Headers("Content-Type: application/json")
    @POST("projectFunction")
    Single<Object> sendRequest(@Body Payload requestBody);
}

