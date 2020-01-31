package com.example.pkk_sip;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

public class BearApp extends Application implements LifecycleObserver {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    boolean startup = true;
    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        Runnable start = new Runnable() {
            @Override
            public void run() {
                startup =false;
            }
        };

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        editor = pref.edit();
        editor.putString("soundSetting", "ON");
        editor.putString("BGMSetting","ON");
        editor.apply();

        Handler hand = new Handler();
        hand.postDelayed(start,3000);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onAppBackgrounded() {
//        System.out.println("Berhasil Background");
        if(pref.getString("BGMSetting", null).equalsIgnoreCase("ON")&&startup==false){
            BGMClass.stopBGM();
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onAppForegrounded() {
        if(pref.getString("BGMSetting", null).equalsIgnoreCase("ON")&&startup==false){
            BGMClass.startBGM(this);
        }

//        Log.d("MyApp", "App in foreground");
    }
}