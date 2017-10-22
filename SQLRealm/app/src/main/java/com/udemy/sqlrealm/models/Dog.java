package com.udemy.sqlrealm.models;

import com.udemy.sqlrealm.application.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by angel on 17/10/17.
 */

public class Dog extends RealmObject
{
    @PrimaryKey
    private int id;
    private String name;

    //Realm necesita un constructor
    public Dog()
    {

    }

    public Dog(String name)
    {
        id = MyApplication.DogID.incrementAndGet();
        this.name = name;
    }

    public int getId()
    {
        return this.id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
