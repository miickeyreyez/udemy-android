package com.udemy.fragmentapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(getIntent().getExtras() != null){
            text = getIntent().getStringExtra("text");
        }

        //Llamar al método renderizarTexto del DetailsFragment pasándole el texto recibido
        DetailsFragment detailsFragment = (DetailsFragment) getFragmentManager().findFragmentById(R.id.detailsFragment);
        detailsFragment.renderText(text);
        
    }
}
