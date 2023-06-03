package com.example.Model.PineconeModel;

import java.util.List;
public class PineconeQueryResponse {

    private List<Match> matches;
    private String namespace;

    public PineconeQueryResponse(List<Match> matches, String namespace) {
        this.matches = matches;
        this.namespace = namespace;
    }

    public PineconeQueryResponse() {
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
