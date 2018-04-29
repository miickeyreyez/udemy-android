package com.udemy.tabby.tabby;

import java.text.MessageFormat;

/**
 * Created by INSPIRON on 26/4/2018.
 */

public class Country {
    private String name;
    private String countryCode;

    public Country (String name, String countryCode){
        this.name = name;
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFlagUrl(){
        return MessageFormat.format("http://www.geognos.com/api/en/countries/flag/{0}.png",this.getCountryCode());

    }

    @Override
    public String toString() {
        return name;
    }
}
