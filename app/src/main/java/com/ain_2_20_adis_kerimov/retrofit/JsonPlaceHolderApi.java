package com.ain_2_20_adis_kerimov.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("activity/")
    Call<Activity> getActivity();
}
