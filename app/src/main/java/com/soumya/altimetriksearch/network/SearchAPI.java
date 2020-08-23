package com.soumya.altimetriksearch.network;

import com.soumya.altimetriksearch.model.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchAPI {
    @GET("/search")
    Call<SearchResponse>
    searchResult(@Query("term") String term);
}
