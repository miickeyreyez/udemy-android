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

        import com.udemy.cardrecyclerview.JugadorAdapter;
        import com.udemy.cardrecyclerview.R;
        import com.udemy.models.Jugador;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by angel on 8/10/17.
 */

public class JugadoresActivity extends AppCompatActivity
{
    private List<Jugador> jugadores;
    private List<Jugador> bench;

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    private int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);

        jugadores = getPlayers();
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
            myLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
        }
        else
        {
            //Indicamos el número de columnas así como la orientación
            myLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        }
        myAdapter = new JugadorAdapter(jugadores, R.layout.jugadores_activity, new JugadorAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(Jugador jugador, int position)
            {
                Toast.makeText(JugadoresActivity.this,jugador.getName(),Toast.LENGTH_SHORT).show();
                deletePlayer(position);
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
                this.addPlayer(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
        //return super.onOptionsItemSelected(item);
    }

    private List<Jugador> getPlayers()
    {
        return new ArrayList<Jugador>()
        {{
            add(new Jugador("1.Marc André Ter Stegen",R.drawable.stegen));
            add(new Jugador("6. Denis Suárez",R.drawable.denis));
            add(new Jugador("20. Sergi Roberto",R.drawable.sergi));
            add(new Jugador("3. Gerard Piqué",R.drawable.pique));
            add(new Jugador("8. Andrés Iniesta",R.drawable.iniesta));
            add(new Jugador("10. Lionel Messi",R.drawable.messi));
        }};
    }

    private List<Jugador> getBench()
    {
        return new ArrayList<Jugador>()
        {{
            add(new Jugador("9. Luis Suárez",R.drawable.lucho));
            add(new Jugador("11. Ousmane Dembélé",R.drawable.dembele));
            add(new Jugador("23. Samuel Umtiti",R.drawable.umtiti));
        }};
    }

    private void addPlayer(int position)
    {
        if(contador < bench.size())
        {
            //Añadimos el nombre en el top
            jugadores.add(position,bench.get(contador));
            //Aumentamos el contador
            contador++;
            //Notificar el cambio del adaptador, esto optimiza el performance
            myAdapter.notifyItemInserted(position);
            //Cuando haga el scroll hay que modificarlo para ver el cambio
            myLayoutManager.scrollToPosition(position);
        }
        else
            Toast.makeText(JugadoresActivity.this,"No more players",Toast.LENGTH_SHORT).show();
    }

    private void deletePlayer(int position)
    {
        jugadores.remove(position);
        myAdapter.notifyItemRemoved(position);
    }

}