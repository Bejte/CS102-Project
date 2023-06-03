package com.example.Pinecone;

import com.example.Model.PineconeModel.PineconeQueryRequest;
import com.example.Model.PineconeModel.PineconeQueryResponse;
import com.example.Model.PineconeModel.VectorUpsertRequest;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PineconeRestApi {

    @Headers({
            "Content-Type: application/json",
            "Api-Key: 0d6b6c99-ea18-4725-b036-a503901925cb"
    })
    @POST("query")
    Single<PineconeQueryResponse> query(@Body PineconeQueryRequest body);

    @Headers({
            "Api-Key: 0d6b6c99-ea18-4725-b036-a503901925cb",
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("vectors/upsert")
    Single<Object> upsertVectors(@Body VectorUpsertRequest request);
}
