package com.example.catastrophecompass.RemoteDataRepository.VectorDatabaseRepo.VectorModels;

import com.google.gson.annotations.SerializedName;

public class PineconeUpsertSucceedResponse {

    @SerializedName("upsertedCount")
    private int upsertedCount;

    public int getUpsertedCount() {
        return upsertedCount;
    }

    public PineconeUpsertSucceedResponse(int upsertedCount) {
        this.upsertedCount = upsertedCount;
    }

    public PineconeUpsertSucceedResponse() {
    }
}
