package com.soumya.altimetriksearch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.soumya.altimetriksearch.model.Search;
import com.soumya.altimetriksearch.repositories.SearchRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private SearchRepository mSearchRepository;
    private boolean mIsQueryingSearch;
    private Boolean mIsViewingSearch;

    public MainActivityViewModel() {
        mIsViewingSearch = false;
        mSearchRepository = SearchRepository.getInstance();
        mIsQueryingSearch = false;
    }

    public LiveData<List<Search>> getSearch() {
        return mSearchRepository.getSearch();
    }

    public Boolean IsViewingSearch() {
        return mIsViewingSearch;
    }

    public void setIsQueryingSearch(boolean mIsQueryingRecipes) {
        this.mIsQueryingSearch = mIsQueryingRecipes;
    }

    public void searchRecipesApi(String query){
        mIsViewingSearch = true;
        mSearchRepository.searchApi(query);
        mIsQueryingSearch = true;
    }
}
