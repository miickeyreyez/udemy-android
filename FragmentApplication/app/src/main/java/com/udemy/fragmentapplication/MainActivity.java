package com.udemy.fragmentapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements DataFragment.DataListener {

    private boolean isMultiPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isMultiPanel();
    }

    @Override
    public void sendData(String text) {

        if(isMultiPanel) {
            //Llamar al método renderizarTexto del DetailsFragment pasándole el texto recibido
            DetailsFragment detailsFragment = (DetailsFragment) getFragmentManager().findFragmentById(R.id.detailsFragment);
            detailsFragment.renderText(text);
        } else {
            Intent intent = new Intent(this,DetailsActivity.class);
            intent.putExtra("text",text);
            startActivity(intent);
        }
    }

    private void isMultiPanel(){
        isMultiPanel = (getFragmentManager().findFragmentById(R.id.detailsFragment) != null);
    }
}
