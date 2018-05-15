package com.udemy.gsonretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by INSPIRON on 13/5/2018.
 */

public class API {

    public static final String BASE_URL = "http://samples.openweathermap.org/data/2.5/";
    private static Retrofit retrofit = null;

    public static Retrofit getApi() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
