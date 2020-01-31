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

    MediaPlayer bgm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainMenu = new Intent(SplashActivity.this, MainActivity.class);
                mainMenu.putExtra("firstTime", true);
                startActivity(mainMenu);
                CustomIntent.customType(SplashActivity.this, "fadein-to-fadeout");
                finish();
            }
        }, waktuloading);

    }
}
