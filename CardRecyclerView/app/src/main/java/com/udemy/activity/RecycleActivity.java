package com.udemy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.udemy.cardrecyclerview.MyAdapter;
import com.udemy.cardrecyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angel on 8/10/17.
 */

public class RecycleActivity extends AppCompatActivity
{
    private List<String> names;
    private List<String> bench;
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    private int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);

        names = getNames();
        bench = getBench();

        //Obtenemos el intent
        Bundle b = getIntent().getExtras();
        int option = b.getInt("layout");


        myRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        if(option == 1)
        {
            myLayoutManager = new LinearLayoutManager(this);
        }
        else if(option == 2)
        {
            //Indicamos el número de columnas
            myLayoutManager = new GridLayoutManager(this,2);
        }
        else if(option == 3)
        {
            //Indicamos el número de columnas así como la orientación
            myLayoutManager = new StaggeredGridLayoutManager(8,StaggeredGridLayoutManager.HORIZONTAL);
        }
        else
        {
            //Indicamos el número de columnas así como la orientación
            myLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        }
        myAdapter = new MyAdapter(names, R.layout.recycle_view_item, new MyAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(String name, int position)
            {
                Toast.makeText(RecycleActivity.this,name,Toast.LENGTH_SHORT).show();
                deleteName(position);
            }
        });

        //Si sabemos que el layout va a ser fijo, por ejemplo nombre a 10 caracteres
        //Mejora el performance del recycle view
        myRecyclerView.setHasFixedSize(true);
        //Efecto de animación, en nuestro caso usamos el default
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(myAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add_name:
                this.addName(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
        //return super.onOptionsItemSelected(item);
    }

    private List<String> getNames()
    {
        return new ArrayList<String>()
        {{
            add("1. Marc André ter Stegen");
            add("20. Sergi Roberto");
            add("23. Samuel Umtiti");
            add("3. Gerard Piqué");
            add("18. Jordi Alba");
            add("4. Iván Rakitic");
            add("5. Sergio Busquets");
            add("8. Andrés Iniesta");
            add("10. Lionel Messi");
            add("9. Luis Suárez");
            add("11. Ousmane Dembélé");
        }};
    }

    private List<String> getBench()
    {
        return new ArrayList<String>()
        {{
            add("13. Jasper Cillisen");
            add("2. Nelson Semedo");
            add("14. Javier Mascherano");
            add("16. Gerard Deulofeu");
        }};
    }

    private void addName(int position)
    {
        if(contador < bench.size())
        {
            //Añadimos el nombre en el top
            names.add(position,bench.get(contador));
            //Aumentamos el contador
            contador++;
            //Notificar el cambio del adaptador, esto optimiza el performance
            myAdapter.notifyItemInserted(position);
            //Cuando haga el scroll hay que modificarlo para ver el cambio
            myLayoutManager.scrollToPosition(position);
        }
        else
            Toast.makeText(RecycleActivity.this,"No more players",Toast.LENGTH_SHORT).show();
    }

    private void deleteName(int position)
    {
        names.remove(position);
        myAdapter.notifyItemRemoved(position);
    }

}