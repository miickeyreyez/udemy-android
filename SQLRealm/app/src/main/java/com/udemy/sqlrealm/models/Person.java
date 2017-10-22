package com.udemy.sqlrealm.models;

import com.udemy.sqlrealm.application.MyApplication;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by angel on 17/10/17.
 */

public class Person extends RealmObject
{
    @PrimaryKey
    private int id;
    private String name;
    private RealmList<Dog> dogs;

    public Person()
    {

    }

    public Person(String name)
    {
        id = MyApplication.PersonID.incrementAndGet();
        this.name = name;
        dogs = new RealmList<Dog>();
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public RealmList<Dog> getDogs()
    {
        return  dogs;
    }
}
