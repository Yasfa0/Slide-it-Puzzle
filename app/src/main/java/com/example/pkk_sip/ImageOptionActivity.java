package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import maes.tech.intentanim.CustomIntent;

public class ImageOptionActivity extends AppCompatActivity {

    ImageView back;
    CountDownTimer jeda;
    MediaPlayer voice;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    MediaPlayer bgm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_difficulty);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        back = (ImageView) findViewById(R.id.back);

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMenu = new Intent(ImageOptionActivity.this, MainActivity.class);
                startActivity(toMenu);
                backSound();
                CustomIntent.customType(ImageOptionActivity.this, "fadein-to-fadeout");
            }
        });

    }

    public void playSound() {

        if (pref.getString("soundSetting", null).equalsIgnoreCase("ON")) {
            voice = MediaPlayer.create(this, R.raw.tone);
            voice.start();
            voice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    voice.stop();
                    voice.release();
                }
            });
        } else {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void backSound() {

        if (pref.getString("soundSetting", null).equalsIgnoreCase("ON")) {
            voice = MediaPlayer.create(this, R.raw.computer_error);
            voice.start();
            voice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    voice.stop();
                    voice.release();
                }
            });
        } else {
        }

    }
}
