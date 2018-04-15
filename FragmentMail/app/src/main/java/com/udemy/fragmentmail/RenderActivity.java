package com.udemy.fragmentmail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RenderActivity extends AppCompatActivity {

    private Button mobile;
    private Button tablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_render);

        mobile = (Button)findViewById(R.id.buttonMobileView);
        tablet = (Button)findViewById(R.id.buttonTabletView);

        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("isMobile",true);
                startActivity(intent);
            }
        });

        tablet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("isMobile",false);
                startActivity(intent);
            }
        });
    }
}
