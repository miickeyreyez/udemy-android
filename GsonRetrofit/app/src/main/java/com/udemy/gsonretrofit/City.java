package com.udemy.gsonretrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by INSPIRON on 13/5/2018.
 */

public class City {

    @Expose
    private int id;
    private String name;
    @SerializedName("main")
    private Temperature temperature;

    public City() {
    }

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.name;
    }

    public static Temperature parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        Temperature temp = gson.fromJson(response, Temperature.class);
        return temp;
    }
}

