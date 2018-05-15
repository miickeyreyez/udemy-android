package com.udemy.gsonretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by INSPIRON on 13/5/2018.
 */

public interface weatherService {
    @GET("weather")
    Call<City> getCity(@Query("q") String city, @Query("appid") String key);

    @GET("find")
    Call<City> findCity(@Query("q") String city, @Query("appid") String key);
}
