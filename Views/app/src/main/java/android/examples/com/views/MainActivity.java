package android.examples.com.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private Button btnBasicListView;
    private Button btnListView;
    private Button btnBasicGridView;
    private Button btnGridView;
    private Button btnFruits;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBasicListView = (Button) findViewById(R.id.btnBasic);
        btnListView = (Button) findViewById(R.id.btnList);
        btnBasicGridView = (Button) findViewById(R.id.btnBasicGrid);
        btnGridView = (Button) findViewById(R.id.btnGrid);
        btnFruits = (Button) findViewById(R.id.btnFruits);

        btnBasicListView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MainActivity.this,BasicListActivity.class);
                startActivity(i);
            }
        });

        btnListView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MainActivity.this,ListActivity.class);
                startActivity(i);
            }
        });

        btnBasicGridView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MainActivity.this,BasicGridActivity.class);
                startActivity(i);
            }
        });

        btnGridView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MainActivity.this,GridActivity.class);
                startActivity(i);
            }
        });

        btnFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,FruitActivity.class);
                startActivity(i);
            }
        });

    }
}

