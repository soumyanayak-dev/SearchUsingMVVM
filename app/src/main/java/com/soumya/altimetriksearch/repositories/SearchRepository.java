package com.soumya.altimetriksearch.repositories;

import androidx.lifecycle.LiveData;

import com.soumya.altimetriksearch.model.Search;
import com.soumya.altimetriksearch.network.ApiClient;

import java.util.List;

public class SearchRepository {

    private static SearchRepository instance;
    private ApiClient mApiClient;
    private String mQuery;
    private int mPageNumber;

    public static SearchRepository getInstance(){
        if(instance == null){
            instance = new SearchRepository();
        }
        return instance;
    }

    private SearchRepository() {
        mApiClient = ApiClient.getInstance();
    }

    public LiveData<List<Search>> getSearch(){
        return mApiClient.getSearch();
    }

    public void searchApi(String query){
        mQuery = query;
        mApiClient.searchApi(query);
    }
}
