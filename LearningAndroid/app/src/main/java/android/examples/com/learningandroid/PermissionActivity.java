package android.examples.com.learningandroid;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PermissionActivity extends AppCompatActivity {
    private EditText editTextPhone;
    private EditText editTextBrowser;
    private ImageButton imageButtonPhone;
    private ImageButton imageButtonBrowser;
    private ImageButton imageButtonCamera;
    private String phoneNumber;
    private Intent intentCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        editTextPhone = (EditText) findViewById(R.id.editText);
        editTextBrowser = (EditText) findViewById(R.id.editText2);
        imageButtonPhone = (ImageButton) findViewById(R.id.imageButton);
        imageButtonBrowser = (ImageButton) findViewById(R.id.imageButton2);
        imageButtonCamera = (ImageButton) findViewById(R.id.imageButton3);

        //Forzar y cargar icono en el action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Activar flecha ir atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber = editTextPhone.getText().toString();
                intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                if (phoneNumber != null) {
                    //Verificar si la versión de Android es igual o superior a Marshallow
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 100);
                    else
                        olderVersions();
                }
            }

            private void olderVersions() {
                if (CheckPermissions(Manifest.permission.CALL_PHONE))
                    startActivity(intentCall);
                else
                    Toast.makeText(PermissionActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        });

        imageButtonBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editTextBrowser.getText().toString();
                if (url != null || !url.isEmpty()) {
                    /*Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://" + url));
                    */
                    //Intent para abrir el browser: Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://" + url));
                    //Intent para abrir contactos: Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("content://contacts/people"));
                    //Intent para el envío de correo: Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:" + "isc.angelreyes@gmail.com"));
                    //Intent para el email completo:
                    /*Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Subject");
                    intent.putExtra(Intent.EXTRA_TEXT,"Mensaje de prueba");
                    intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"maxr@praxis.com.mx","miickeyreyez@gmail.com"});
                    startActivity(Intent.createChooser(intent,"Elige el cliente de correo"));
                    */
                    //Intent para hacer una llamada --> Primero se debe verificar que tenga permisos
                    /*Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "5531327501"));
                    if (ActivityCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    */
                    //Intent para el teléfono sin solicitar permisos
                    Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:5531327501"));
                    startActivity(intent);
                }
            }
        });

        imageButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para abrir camara
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent,50);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case 50:
                if( resultCode == Activity.RESULT_OK)
                {
                    String result = data.toUri(0);
                    Toast.makeText(PermissionActivity.this,"Result " + result,Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(PermissionActivity.this,"Error taking picture",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
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
                        startActivity(intentCall);
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
                        Toast.makeText(PermissionActivity.this,"Permission denied",Toast.LENGTH_SHORT).show();
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
}
