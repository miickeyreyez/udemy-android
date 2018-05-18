package com.udemy.gsonretrofit;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by INSPIRON on 13/5/2018.
 */

public class API {

    public static final String BASE_URL = "http://samples.openweathermap.org/data/2.5/";
    public static final String BASE_ICONS = "http://openweather.org/img/w/";
    public static final String EXTENSION_ICONS = ".png";

    private static Retrofit retrofit = null;

    public static Retrofit getApi() {
        if(retrofit == null) {

            /*Esto es solo para la deserializaciòn, el builder.create() tambièn*/
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(City.class,new Desearilizer());

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
        }
        return retrofit;
    }
}
