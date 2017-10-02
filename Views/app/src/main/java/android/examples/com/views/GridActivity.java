package android.examples.com.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    protected List<String> names = new ArrayList<String>();
    protected List<String> bench = new ArrayList<String>();
    private int contador = 0;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        GridView lista = (GridView)findViewById(R.id.gridView);

        names.add("1. Marc André ter Stegen");
        names.add("2. Nelson Semedo");
        names.add("3. Gerard Piqué");
        names.add("23. Samuel Umtiti");
        names.add("18. Jordi Alba");
        names.add("4. Iván Rakitic");
        names.add("8. Andrés Iniesta");
        names.add("5. Sergio Busquets");
        names.add("9. Luis Suárez");
        names.add("10. Lionel Messi");
        names.add("11. Ousmane Dembélé");

        bench.add("13. Jasper Cillisen");
        bench.add("20. Sergi Roberto");
        bench.add("14. Javier Mascherano");
        bench.add("15. Paulinho");
        bench.add("16. Gerard Deulofeu");

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Clicked: " + names.get(i),Toast.LENGTH_SHORT).show();
            }
        });

        myAdapter = new MyAdapter(this,R.layout.list_item2,names);

        lista.setAdapter(myAdapter);

        //Para mantener presionado un item
        registerForContextMenu(lista);
    }

    //Inflamos el layout del menu de opciones (Add)
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        return true;
    }

    //Manejamos eventos click en el menu de opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.item:
                //Añadir nuevo nombre
                if(contador < bench.size())
                {
                    this.names.add(bench.get(contador));
                    contador++;
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No more players",Toast.LENGTH_SHORT).show();
                }
                //Notificar el cambio al adaptador
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Inflamos layout del context menu (Cuando mantenemos presionado un item)
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.names.get(info.position));
        inflater.inflate(R.menu.context_menu,menu);
    }

    //Manejamos eventos click del context menu
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.delete_item:
                //Borramos item clickeado
                this.names.remove(info.position);
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
