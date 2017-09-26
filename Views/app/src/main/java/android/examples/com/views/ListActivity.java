package android.examples.com.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    protected List<String> names = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView lista = (ListView)findViewById(R.id.listview);

        names.add("Angel");
        names.add("Miguel");
        names.add("Daniela");
        names.add("Carlos");
        names.add("Angel");
        names.add("Miguel");
        names.add("Daniela");
        names.add("Carlos");
        names.add("Angel");
        names.add("Miguel");
        names.add("Daniela");
        names.add("Carlos");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        //lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Clicked: " + names.get(i),Toast.LENGTH_LONG).show();
            }
        });

        MyAdapter myAdapter = new MyAdapter(this,R.layout.list_item,names);
        lista.setAdapter(myAdapter);
    }
}
