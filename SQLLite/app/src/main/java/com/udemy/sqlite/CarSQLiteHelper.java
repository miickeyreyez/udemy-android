package com.udemy.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by angel on 9/10/17.
 */

public class CarSQLiteHelper extends SQLiteOpenHelper
{
    //Creamos la tabla
    private String sqlCreate = "CREATE TABLE Cars (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "marca TEXT," +
            "modelo TEXT)";

    private String sqlDelete = "DROP TABLE IF EXISTS Cars";

    public CarSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        //Si existe una versión previa hay que eliminarla
        sqLiteDatabase.execSQL(sqlDelete);
        //Creamos una nueva versión
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
