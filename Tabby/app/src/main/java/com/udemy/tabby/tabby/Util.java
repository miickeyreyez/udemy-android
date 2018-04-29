package com.udemy.tabby.tabby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public static List<Country> getCountries(){
        return new ArrayList<Country>() {{
            add(new Country("España","ES"));
            add(new Country("Argentina","AR"));
            add(new Country("Bolivia","BO"));
            add(new Country("Chile","CL"));
            add(new Country("Colombia","CO"));
            add(new Country("Mexico","MX"));
            add(new Country("Peru","PE"));
            add(new Country("Uruguay","UY"));
            add(new Country("Venezuela","VE"));
        }};
    }
}
