package com.example.Model.PineconeModel;

import java.util.List;

public class PineconeQueryRequest {

    private List<Float> vector;
    private int topK;
    private boolean includeMetadata;
    private boolean includeValues;
    private String namespace;

    public PineconeQueryRequest(List<Float> vector, int topK, boolean includeMetadata, boolean includeValues, String namespace) {
        this.vector = vector;
        this.topK = topK;
        this.includeMetadata = includeMetadata;
        this.includeValues = includeValues;
        this.namespace = namespace;
    }

    public PineconeQueryRequest() {
    }

    public List<Float> getVector() {
        return vector;
    }

    public void setVector(List<Float> vector) {
        this.vector = vector;
    }

    public int getTopK() {
        return topK;
    }

    public void setTopK(int topK) {
        this.topK = topK;
    }

    public boolean isIncludeMetadata() {
        return includeMetadata;
    }

    public void setIncludeMetadata(boolean includeMetadata) {
        this.includeMetadata = includeMetadata;
    }

    public boolean isIncludeValues() {
        return includeValues;
    }

    public void setIncludeValues(boolean includeValues) {
        this.includeValues = includeValues;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
