package com.udemy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.udemy.cardrecyclerview.R;

public class MainActivity extends AppCompatActivity
{
    private Button btnRecycleList;
    private Button btnRecycleGrid;
    private Button btnRecycleGridH;
    private Button btnRecycleGridV;
    private Button btnCardView;
    private Button btnJugadores;
    private Button btnFrutas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecycleList = (Button)findViewById(R.id.btnRecycleList);
        btnRecycleGrid = (Button)findViewById(R.id.btnRecycleGrid);
        btnRecycleGridH = (Button)findViewById(R.id.btnRecycleStaggedH);
        btnRecycleGridV = (Button)findViewById(R.id.btnRecycleStaggedV);
        btnCardView = (Button)findViewById(R.id.btnCardView);
        btnJugadores = (Button)findViewById(R.id.btnJugadores);
        btnFrutas = (Button)findViewById(R.id.btnFrutas);

        btnRecycleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RecycleActivity.class);
                intent.putExtra("layout",1);
                startActivity(intent);
            }
        });

        btnRecycleGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RecycleActivity.class);
                intent.putExtra("layout",2);
                startActivity(intent);
            }
        });

        btnRecycleGridH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RecycleActivity.class);
                intent.putExtra("layout",3);
                startActivity(intent);
            }
        });

        btnRecycleGridV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RecycleActivity.class);
                intent.putExtra("layout",4);
                startActivity(intent);
            }
        });

        btnCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CardActivity.class);
                startActivity(intent);
            }
        });

        btnJugadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,JugadoresActivity.class);
                intent.putExtra("layout",4);
                startActivity(intent);
            }
        });

        btnFrutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FruitsActivity.class);
                intent.putExtra("layout",4);
                startActivity(intent);
            }
        });
    }

}
