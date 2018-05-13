package com.udemy.gson;

import com.google.gson.annotations.Expose;

/**
 * Created by INSPIRON on 13/5/2018.
 */

public class City {

    @Expose
    public String counter;

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    @Expose
    public String id;
    public String name;

    public City() {
    }

    public City(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.name;
    }
}
