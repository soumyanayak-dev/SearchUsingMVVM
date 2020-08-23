package com.soumya.altimetriksearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

    @SerializedName("results")
    @Expose
    private List<Search> results;
    public List<Search>
    getSearch() {
        return results;
    }
}
