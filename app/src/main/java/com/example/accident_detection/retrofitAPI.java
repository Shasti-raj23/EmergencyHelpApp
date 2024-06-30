package com.example.accident_detection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
public interface retrofitAPI {
    @GET
    Call<msgmodel> getMessage(@Url String url);

}
