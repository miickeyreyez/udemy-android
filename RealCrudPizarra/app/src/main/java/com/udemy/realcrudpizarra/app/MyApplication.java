package com.udemy.realcrudpizarra.app;

import android.app.Application;

import com.udemy.realcrudpizarra.models.Board;
import com.udemy.realcrudpizarra.models.Note;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by angel on 22/10/17.
 */

public class MyApplication extends Application
{
    //Clase de configuración antes del mainActivity

    public static AtomicInteger boardID = new AtomicInteger();
    public static AtomicInteger noteID = new AtomicInteger();

    @Override
    public void onCreate()
    {
        super.onCreate();
        //Setear la configuración
        setUpRealmConfig();
        Realm realm = Realm.getDefaultInstance();
        //Leer de la base de datos, los max del ID
        boardID = getIDbyTable(realm, Board.class);
        noteID = getIDbyTable(realm, Note.class);
    }

    private void setUpRealmConfig()
    {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIDbyTable(Realm realm, Class<T> anyClass)
    {
        RealmResults<T> result = realm.where(anyClass).findAll();
        //Sacamos el max de la tabla, si no hay nada se crea un nuevo atomic integer
        return (result.size() > 0) ? new AtomicInteger(result.max("id").intValue()) : new AtomicInteger();
    }
}
