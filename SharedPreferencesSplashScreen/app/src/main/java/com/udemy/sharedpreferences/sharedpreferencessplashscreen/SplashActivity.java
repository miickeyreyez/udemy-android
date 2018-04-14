package com.udemy.sharedpreferences.sharedpreferencessplashscreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        Intent intentLogin = new Intent(this,MainActivity.class);
        Intent intentMain = new Intent(this,PrincipalActivity.class);

        if(!TextUtils.isEmpty(getUserMailPrefs()) && !TextUtils.isEmpty(getUserPassPrefs())){
            startActivity(intentMain);
        } else {
            intentLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentLogin);
        }
        finish();
    }

    private String getUserMailPrefs(){
        return prefs.getString("email","");
    }

    private String getUserPassPrefs(){
        return prefs.getString("password","");
    }
}
