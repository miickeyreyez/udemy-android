/**
 * Created by angel on 8/10/17.
 */

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

import com.udemy.cardrecyclerview.FrutaAdapter;
import com.udemy.cardrecyclerview.JugadorAdapter;
import com.udemy.cardrecyclerview.MyAdapter;
import com.udemy.cardrecyclerview.R;
import com.udemy.models.Fruta;
import com.udemy.models.Jugador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angel on 8/10/17.
 */

public class FruitsActivity extends AppCompatActivity
{
    private List<Fruta> frutas;

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    private int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);

        frutas = getFrutas();

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
            myLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
        }
        else
        {
            //Indicamos el número de columnas así como la orientación
            myLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        }
        myAdapter = new FrutaAdapter(frutas, R.layout.fruta_activity, this, new FrutaAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(Fruta fruta, int position)
            {
                //Toast.makeText(FruitsActivity.this,fruta.getName(),Toast.LENGTH_SHORT).show();
                fruta.addQuantity(1);
                //Notificamos el cambio
                myAdapter.notifyItemChanged(position);
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
                int position = frutas.size();
                frutas.add(position,new Fruta("Mora","Roja/Morada",R.drawable.strawberry_bg,R.mipmap.ic_strawberry,0));
                //Notificar el cambio del adaptador, esto optimiza el performance
                myAdapter.notifyItemInserted(position);
                //Cuando haga el scroll hay que modificarlo para ver el cambio
                myLayoutManager.scrollToPosition(position);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
        //return super.onOptionsItemSelected(item);
    }

    private List<Fruta> getFrutas()
    {
        return new ArrayList<Fruta>()
        {{
            add(new Fruta("Manzana","Roja",R.drawable.manzana,R.mipmap.ic_manzana,0));
            add(new Fruta("Fresa","Roja",R.drawable.strawberry_bg,R.mipmap.ic_strawberry,0));
            add(new Fruta("Naranja","Naranja",R.drawable.orange_bg,R.mipmap.ic_naranja,0));
            add(new Fruta("Banana","Amarilla",R.drawable.banana_bg,R.mipmap.ic_banana,0));
            add(new Fruta("Cereza","Roja",R.drawable.cherry_bg,R.mipmap.ic_cereza,0));
            add(new Fruta("Pera","Verde",R.drawable.pear_bg,R.mipmap.ic_pera,0));
            //add(new Fruta("Mora","Roja/Morada",R.drawable.strawberry_bg,R.mipmap.ic_strawberry,0));
        }};
    }

}