package android.examples.com.learningandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by angel on 25/09/17.
 */

public class ImplicitoActivity extends AppCompatActivity {

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.implicito_activity);
        texto = (TextView)findViewById(R.id.textViewImplicito);
        Bundle b = getIntent().getExtras();
        String saludo = b.getString("saludo");
        texto.setText(saludo);
    }
}
