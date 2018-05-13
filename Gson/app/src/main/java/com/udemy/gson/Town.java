package com.udemy.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by INSPIRON on 13/5/2018.
 */

public class Town {

    private int id;
    @SerializedName("city")
    private List<City> city;

    public Town() {
    }

    public Town(int id, List<City> city) {
        this.id = id;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    public static City parseJson(String response) {
        Gson gson = new GsonBuilder().create();
        City city = gson.fromJson(response,City.class);
        return city;
    }
}
