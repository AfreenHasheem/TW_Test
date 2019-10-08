package com.example.tw_test.network;

import com.example.tw_test.model.Hero;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("marvel")
    Call<List<Hero>> getHeroes();


}
