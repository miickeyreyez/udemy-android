package com.udemy.sharedpreferences.sharedpreferencessplashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by INSPIRON on 4/4/2018.
 */

public class MainActivity extends AppCompatActivity {

    private EditText correo;
    private EditText contrasenia;
    private Switch recordar;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindUI();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login(correo.getText().toString(),contrasenia.getText().toString())){
                    go2Main();
                }
            }
        });
    }

    private void bindUI(){
        correo = (EditText)findViewById(R.id.editTextUsuario);
        contrasenia = (EditText)findViewById(R.id.editTextContraseña);
        recordar = (Switch)findViewById(R.id.switchRecordar);
        login = (Button)findViewById(R.id.buttonLogin);
    }

    private boolean isValidEmail(String correo){
        return !TextUtils.isEmpty(correo) && correo.length() > 0 && Patterns.EMAIL_ADDRESS.matcher(correo).matches();
    }

    private boolean isValidPassword(String password){
        return password.length() > 4;
    }

    private boolean login(String email, String password){
        if(!isValidEmail(email)){
            Toast.makeText(this,"Credenciales incorrectas",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!isValidPassword(password)){
            Toast.makeText(this,"Contraseña incorrecta",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private void go2Main(){
        Intent intent = new Intent(this,PrincipalActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
