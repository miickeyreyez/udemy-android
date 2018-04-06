package com.udemy.sharedpreferences.sharedpreferencessplashscreen;

import android.app.Application;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

public class MyApplication extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        SystemClock.sleep(2000);
    }

}
