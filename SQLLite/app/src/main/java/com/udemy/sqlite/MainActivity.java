package com.udemy.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private Button btnCreate;
    private Button btnDelete;

    private CarSQLiteHelper carsHelper;
    private SQLiteDatabase db;

    private ListView listView;
    private MyAdapter adapter;

    private List<Car> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewCars);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        cars = new ArrayList<Car>();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
                update();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAll();
                update();
            }
        });

        //Abrir base de datos en modo escritura
        carsHelper = new CarSQLiteHelper(this, "CarsDB", null, 1);
        db = carsHelper.getWritableDatabase();
        adapter = new MyAdapter(this, cars, R.layout.itemdb);
        listView.setAdapter(adapter);
        update();

    }

    public void create()
    {
        //Verificar si existe la base de datos
        if(db != null)
        {
            List<String> marca = new ArrayList<String>();
            marca.add("Lamborghini");
            marca.add("Mc Laren");
            marca.add("Ferrari");
            marca.add("Bentley");
            marca.add("Mercedes");
            marca.add("Koenigsegg");
            marca.add("Mitsubishi");
            marca.add("Chevrolet");
            marca.add("Ford");
            marca.add("Porsche");
            List<String> modelo = new ArrayList<>();
            modelo.add("LP 700");
            modelo.add("P1");
            modelo.add("F12 Berlinetta");
            modelo.add("Continental");
            modelo.add("AMG GT");
            modelo.add("Agera");
            modelo.add("Lancer Evolution XII");
            modelo.add("Corvette C7 Stingray");
            modelo.add("Shelby GT");
            modelo.add("911");
            for(int i = 0; i < marca.size(); i++)
            {
                //Insertamos el registro
                ContentValues registro = new ContentValues();
                //Al declarar el id autoincrementable entonces no pasa nada
                registro.put("marca",marca.get(i));
                registro.put("modelo",modelo.get(i));
                //Insert
                db.insert("Cars",null,registro);
            }
        }
    }

    public void removeAll()
    {
        db.delete("Cars","",null);
    }

    public void update()
    {
        //Borrar todos los elementos
        cars.clear();
        //Obtener todo
        cars.addAll(getAllCars());
        //Notificar
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy()
    {
        //Cerrar conexión
        if(db != null)
        {
            db.close();
        }
        super.onDestroy();
    }

    private List<Car> getAllCars()
    {
        //Seleccionar todo de la tabla
        Cursor cursor = db.rawQuery("select * from Cars",null);
        List<Car> crs = new ArrayList<Car>();
        if(cursor.moveToFirst())
        {
            //Movemos el cursor al inicio para iterar los resultados
            //Mientras no sea el final
            while(cursor.isAfterLast() == false)
            {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String marca = cursor.getString(cursor.getColumnIndex("marca"));
                String modelo = cursor.getString(cursor.getColumnIndex("modelo"));
                crs.add(new Car(id,marca,modelo));
                cursor.moveToNext();
            }
        }
        return crs;
    }

}
