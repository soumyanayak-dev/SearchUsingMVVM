package com.soumya.altimetriksearch.network;

import com.soumya.altimetriksearch.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static SearchAPI recipeApi = retrofit.create(SearchAPI.class);

    public static SearchAPI getSearchApi() {
        return recipeApi;
    }
}
