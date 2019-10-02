package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import maes.tech.intentanim.CustomIntent;

public class SplashActivity extends AppCompatActivity {

    private int waktuloading = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


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
