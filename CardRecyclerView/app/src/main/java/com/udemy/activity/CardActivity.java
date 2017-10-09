package com.udemy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.udemy.cardrecyclerview.R;

/**
 * Created by angel on 8/10/17.
 */

public class CardActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_activity);

        TextView cardViewText = (TextView) findViewById(R.id.txtCardView);
        cardViewText.setText("Los Simpsons");
    }
}
