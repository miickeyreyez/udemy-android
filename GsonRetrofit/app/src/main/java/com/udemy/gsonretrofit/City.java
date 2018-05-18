package com.udemy.gsonretrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.ref.PhantomReference;

/**
 * Created by INSPIRON on 13/5/2018.
 */

public class City {

    @Expose
    private int id;
    private String name;
    private String country;
    private String icon;
    private String description;
    private int temperature;
    //@SerializedName("main")
    //private Temperature temperature;

    public City() {
    }

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public City(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public City(int id, String name, String country, String icon, String description, int temperature) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.icon = icon;
        this.description = description;
        this.temperature = temperature;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    /*
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
    }*/
}

