package com.example.catastrophecompass.RemoteDataRepository.VectorDatabaseRepo;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class VectorDatabaseApiService {

    /**
     * Rest Api for Pinecone
     */
    public interface PineconeApiService {
        @Headers({
                "Api-Key: 0d6b6c99-ea18-4725-b036-a503901925cb",
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @POST("vectors/upsert")
        Single<YourResponseModel> upsertVectors(@Body YourRequestModel request);

    }
}
