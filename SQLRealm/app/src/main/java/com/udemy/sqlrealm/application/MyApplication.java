package com.udemy.sqlrealm.application;

import android.app.Application;

import com.udemy.sqlrealm.models.Dog;
import com.udemy.sqlrealm.models.Person;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by angel on 17/10/17.
 */

//Esto se ejecuta antes del MainActivity
public class MyApplication extends Application
{
    //Objeto facilmente escalable
    public static AtomicInteger PersonID = new AtomicInteger();
    public static AtomicInteger DogID = new AtomicInteger();

    @Override
    public void onCreate()
    {
        super.onCreate();

        //Este constructor, ya no existe.
        //RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).deleteRealmIfMigrationNeeded().build();
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getInstance(config);
        PersonID = setAtomicId(realm, Person.class);
        DogID = setAtomicId(realm, Dog.class);
        realm.close();
    }

    private <T extends RealmObject> AtomicInteger setAtomicId(Realm realm, Class<T> anyClass)
    {
        //Select all
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }
}
