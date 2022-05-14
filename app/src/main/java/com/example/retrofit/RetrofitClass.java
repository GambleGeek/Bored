package com.example.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass {
    private static JsonPlaceHolderApi instance;

    static JsonPlaceHolderApi getInstance() {
        if(instance == null) {
            instance = initInstance();
        }
        return instance;
    }

    private static JsonPlaceHolderApi initInstance(){
        return new Retrofit
                .Builder()
                .baseUrl("https://www.boredapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JsonPlaceHolderApi.class);
    }
}
