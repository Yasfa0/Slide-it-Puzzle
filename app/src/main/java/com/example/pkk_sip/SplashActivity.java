package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import maes.tech.intentanim.CustomIntent;

public class SplashActivity extends AppCompatActivity {

    private int waktuloading = 1000;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    MediaPlayer bgm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        bgm = MediaPlayer.create(this,R.raw.menubgm);
        bgm.setLooping(true);
        bgm.start();


        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        editor = pref.edit();
        editor.putString("soundSetting","ON");
        editor.apply();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainMenu = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(mainMenu);
                CustomIntent.customType(SplashActivity.this,"fadein-to-fadeout");
                finish();
            }
        },waktuloading);

    }
}
