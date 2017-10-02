package android.examples.com.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angel on 1/10/17.
 */

public class BasicListActivity extends AppCompatActivity
{

    protected List<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView lista = (ListView)findViewById(R.id.listview);

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

        //Necesitamos un adaptador para mappear el contenido de la lista, tenemos que pasar el layout de android (simple_list_item_1)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);

        //Establecer el adaptador con la lista
        lista.setAdapter(adapter);

        //Evento onClick()
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Toast.makeText(getApplicationContext(),"Clicked: " + names.get(i),Toast.LENGTH_SHORT).show();
            }
        });
    }
}

