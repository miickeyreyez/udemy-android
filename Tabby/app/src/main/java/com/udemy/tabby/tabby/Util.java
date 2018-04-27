package com.udemy.tabby.tabby;

import java.util.HashMap;

/**
 * Created by INSPIRON on 26/4/2018.
 */

public class Util {

    public static HashMap<String,String> fillCountries(){
        HashMap<String,String> map = new HashMap<>();

        map.put("España","ES");
        map.put("Argentina","AR");
        map.put("Bolivia","BO");
        map.put("Chile","CL");
        map.put("Colombia","CO");
        map.put("Mexico","MX");
        map.put("Peru","PE");
        map.put("Uruguay","UY");
        map.put("Venezuela","VE");

        return map;
    }
}
