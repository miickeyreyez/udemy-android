package android.examples.com.learningandroid;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnPermisions;
    private Button btnIntentImplicito;
    private Button btnIntentExplicito;
    private SeekBar seekBarAge;
    private TextView textViewAge;
    private RadioButton radioButtonGreeter;
    private RadioButton radioButtonFarewell;

    private int age = 18;
    private final int MAX_AGE = 60;
    private final int MIN_AGE = 16;

    Intent intent;

    // Para compartir
    public static final int GREETER_OPTION = 1;
    public static final int FAREWELL_OPTION = 2;

    private static Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        //Botón para intent implicito
        btnIntentImplicito = (Button) findViewById(R.id.btnIntentImplicito);
        //Botón para intent explícito
        btnIntentExplicito = (Button) findViewById(R.id.btnIntentExplicito);
        btnPermisions = (Button) findViewById(R.id.btnPermissions);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Botón para intent implicito
        btnIntentImplicito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent implicito = new Intent(MainActivity.this, ImplicitoActivity.class);
                implicito.putExtra("saludo", "Este es un ejemplo de intent implicito");
                startActivity(implicito);
            }
        });

        //Botón para intent explícito
        btnIntentExplicito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicito = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+"555555"));
                //Verificar si la versión de Android es igual o superior a Marshallow
                intent = explicito;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    //El código 100 es Phone Call
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 100);
                }
                else
                {
                    olderVersions();
                }
            }
        });

        //Botón para verificar los permisos de android
        btnPermisions.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent permisos = new Intent(MainActivity.this, PermissionActivity.class);
                startActivity(permisos);
            }
        });

        // Instanciamos los elementos de la UI con sus referencias
        seekBarAge = (SeekBar) findViewById(R.id.seekBar);
        textViewAge = (TextView) findViewById(R.id.textView2);
        radioButtonGreeter = (RadioButton) findViewById(R.id.radioButton);
        radioButtonFarewell = (RadioButton) findViewById(R.id.radioButton2);

        // Evento change para el SeekBar
        seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int currentAge, boolean b) {
                age = currentAge;
                textViewAge.setText(age + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Aunque no lo sobreescribamos con alguna funcionalidad, OnSeekBarChangeListener es una interfaz
                // y como interfaz que es, necesitamos sobreescribir todos sus métodos, aunque lo dejemos vacío.
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Declaramos nuestras restricciones de edad en el evento en que el usuario suelta/deja el seekbar.
                age = seekBar.getProgress();
                textViewAge.setText(age + "");

                if (age > MAX_AGE) {
                    btnPermisions.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "The max age allowed is: "+MAX_AGE+" years old.", Toast.LENGTH_LONG).show();
                } else if (age < MIN_AGE) {
                    btnPermisions.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "The min age allowed is: "+MIN_AGE+" years old.", Toast.LENGTH_LONG).show();
                } else {
                    btnPermisions.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Se sobreescribe el resultado de la verificación de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case 100:
                String permission = permissions[0];
                int result = grantResults[0];
                if (permission.equals(Manifest.permission.CALL_PHONE))
                {
                    if (result == PackageManager.PERMISSION_GRANTED)
                    {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                            return;
                        startActivity(intent);
                    }
                    else
                    {
                        Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        i.addCategory(Intent.CATEGORY_DEFAULT);
                        i.setData(Uri.parse("package:" + getPackageName()));
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        startActivity(i);
                        Toast.makeText(MainActivity.this,"Permission denied",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode,permissions,grantResults);
                break;
        }
    }

    private boolean CheckPermissions(String permission)
    {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void olderVersions() {
        if (CheckPermissions(Manifest.permission.CALL_PHONE))
            startActivity(intent);
        else
            Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
    }

}
