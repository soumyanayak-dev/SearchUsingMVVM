package com.soumya.altimetriksearch.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.soumya.altimetriksearch.model.Search;
import com.soumya.altimetriksearch.model.SearchResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.soumya.altimetriksearch.utils.Constants.NETWORK_TIMEOUT;

public class ApiClient {

    private static ApiClient instance;
    private MutableLiveData<List<Search>> mSearch;
    private RetrieveSearchRunnable mRetrieveSearchRunnable;

    public static ApiClient getInstance(){
        if(instance == null){
            instance = new ApiClient();
        }
        return instance;
    }

    private ApiClient() {
        mSearch = new MutableLiveData<>();
    }

    public LiveData<List<Search>> getSearch(){
        return mSearch;
    }

    public void searchApi(String query){
        if(mRetrieveSearchRunnable != null){
            mRetrieveSearchRunnable = null;
        }
        mRetrieveSearchRunnable = new RetrieveSearchRunnable(query);
        final Future handler = AppExecutors.getInstane().networkIO().submit(mRetrieveSearchRunnable);

        // Set a timeout for the data refresh
        AppExecutors.getInstane().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // let the user know it timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveSearchRunnable implements Runnable{

        private String query;
        private boolean cancelRequest;

        private RetrieveSearchRunnable(String query) {
            this.query = query;
            cancelRequest = false;
        }

        @Override
        public void run() {

            try {
                Response response = getSearch(query).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    List<Search> list = new ArrayList<>(((SearchResponse)response.body()).getSearch());
                    Log.d("URL", "run: " + response.raw().request().url());

                    List<Search> currentSearch = mSearch.getValue();
                    if(currentSearch == null){
                        currentSearch = new ArrayList<>();
                    }
                    currentSearch.addAll(list);
                    mSearch.postValue(currentSearch);
                }
                else{
                    mSearch.postValue(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                mSearch.postValue(null);
            }
        }

        private Call<SearchResponse>
        getSearch(String query){
            return ServiceGenerator.getSearchApi().searchResult(query);
        }
    }
}
