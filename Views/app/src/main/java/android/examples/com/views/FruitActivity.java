package android.examples.com.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angel on 1/10/17.
 */

public class FruitActivity extends AppCompatActivity
{
    //Frutas
    private ListView listView;
    private GridView gridView;
    private FruitAdapter adapterListView;
    private FruitAdapter adapterGridView;
    private List<Fruit> fruits;
    private MenuItem itemListView;
    private MenuItem itemGridView;
    private boolean switchToGrid = false;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        //Mostrar el icono
        this.enforceIconBar();

        this.fruits = getAllFruits();

        this.listView = (ListView) findViewById(R.id.fruitListView);
        this.gridView = (GridView) findViewById(R.id.fruitGridView);

        this.listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        //Diferenciar entre frutas conocidas y desconocidas
                        if(fruits.get(i).getOrigin().trim().toLowerCase().equals("desconocido"))
                        {
                            Toast.makeText(getApplicationContext(),"Información no disponible",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Origen: " + fruits.get(i).getOrigin(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        this.gridView.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        //Diferenciar entre frutas conocidas y desconocidas
                        if(fruits.get(i).getOrigin().trim().toLowerCase().equals("desconocido"))
                        {
                            Toast.makeText(getApplicationContext(),"Información no disponible",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Origen: " + fruits.get(i).getOrigin(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        this.adapterListView = new FruitAdapter(this,R.layout.list_view_item_fruit,fruits);
        this.adapterGridView = new FruitAdapter(this,R.layout.grid_view_item_fruit,fruits);

        this.listView.setAdapter(adapterListView);
        this.gridView.setAdapter(adapterGridView);

        //Menú contextual
        registerForContextMenu(this.listView);
        registerForContextMenu(this.gridView);

    }

    private void enforceIconBar()
    {
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //@Override
    private void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
    {
            this.clickFruit(fruits.get(position));
    }

    private void clickFruit(Fruit fruit)
    {
        //Diferenciar entre frutas conocidas y desconocidas
        if(fruit.getOrigin().trim().toLowerCase().equals("desconocido"))
        {
            Toast.makeText(getApplicationContext(),"Información no disponible",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Origen: " + fruit.getOrigin(),Toast.LENGTH_SHORT);
        }
    }

    //Inflamos el layout del menu de opciones (Add)
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        this.itemListView = menu.findItem(R.id.list_view_fruit);
        this.itemGridView = menu.findItem(R.id.grid_view_fruit);
        return true;
    }

    //Manejamos eventos click en el menu de opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add_fruit:
                this.addFruit(new Fruit("Nueva fruta " + (++contador),R.mipmap.ic_launcher," Desconocido"));
                return true;
            case R.id.list_view_fruit:
                this.switchListGridView(false);
                return true;
            case R.id.grid_view_fruit:
                this.switchListGridView(true);
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
        menu.setHeaderTitle(this.fruits.get(info.position).getName());
        inflater.inflate(R.menu.context_menu_fruits,menu);
    }

    //Manejamos eventos click del context menu
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.delete_fruit_item:
                //Borramos item clickeado
                this.deleteFruit(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void switchListGridView(boolean flag)
    {
        //Si queremos que se muestre un list
        if(!flag)
        {
            //Si se quiere cambiar a listView pero esta es invisible
            if(this.listView.getVisibility() == View.INVISIBLE)
            {
                //Esconder el gridView y enseñar el botón de menú de acciones
                this.gridView.setVisibility(View.INVISIBLE);
                this.itemGridView.setVisible(true);
                //Enseñar el list view y esconder el botón en el menú
                this.listView.setVisibility(View.VISIBLE);
                this.itemListView.setVisible(false);
            }
        }
        //Si queremos que se muestre un grid
        else
        {
            //Si el grid está invisible
            if(this.gridView.getVisibility() == View.INVISIBLE)
            {
                //Esconder el listView y enseñar el botón de menú de acciones
                this.listView.setVisibility(View.INVISIBLE);
                this.itemListView.setVisible(true);
                //Enseñar el grid view y esconder el botón en el menú
                this.gridView.setVisibility(View.VISIBLE);
                this.itemGridView.setVisible(false);
            }

        }
    }

    private List<Fruit> getAllFruits()
    {
        List<Fruit> list = new ArrayList<Fruit>()
        {{
            add(new Fruit("Banana",R.mipmap.ic_launcher," Tabasco"));
            add(new Fruit("Naranja",R.mipmap.ic_launcher," Guerrero"));
            add(new Fruit("Mango",R.mipmap.ic_launcher," Veracruz"));
            add(new Fruit("Aguacate",R.mipmap.ic_launcher," Veracruz"));
        }};

        return  list;
    }

    private void addFruit(Fruit fruit)
    {
        this.fruits.add(fruit);
        this.adapterListView.notifyDataSetChanged();
        this.adapterGridView.notifyDataSetChanged();
    }

    private void deleteFruit(int index)
    {
        this.fruits.remove(index);
        this.adapterListView.notifyDataSetChanged();
        this.adapterGridView.notifyDataSetChanged();
    }
}
