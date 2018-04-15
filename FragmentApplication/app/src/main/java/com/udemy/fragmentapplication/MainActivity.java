package com.udemy.sharedpreferences.fragmentapplication;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity implements DataFragment.DataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void sendData(String text) {
        //Llamar al método renderizarTexto del DetailsFragment pasándole el texto recibido
        DetailsFragment detailsFragment = (DetailsFragment)getFragmentManager().findFragmentById(R.id.detailsFragment);
        detailsFragment.renderText(text);
    }
}
