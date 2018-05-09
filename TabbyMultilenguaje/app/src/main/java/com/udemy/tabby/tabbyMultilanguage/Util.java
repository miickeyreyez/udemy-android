package com.udemy.tabby.tabbyMultilanguage;

import android.content.Context;

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

    public static List<Country> getCountries(final Context context){
        return new ArrayList<Country>() {{
            add(new Country(context.getString(R.string.spain),"ES"));
            add(new Country(context.getString(R.string.argentina),"AR"));
            add(new Country(context.getString(R.string.bolivia),"BO"));
            add(new Country(context.getString(R.string.chile),"CL"));
            add(new Country(context.getString(R.string.colombia),"CO"));
            add(new Country(context.getString(R.string.mexico),"MX"));
            add(new Country(context.getString(R.string.peru),"PE"));
            add(new Country(context.getString(R.string.uruguay),"UY"));
            add(new Country(context.getString(R.string.venezuela),"VE"));
        }};
    }
}
