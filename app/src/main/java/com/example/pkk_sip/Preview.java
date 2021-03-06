package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import maes.tech.intentanim.CustomIntent;

public class Preview extends AppCompatActivity {

    ImageView back, play;
    MediaPlayer voice;
    SharedPreferences pref;

    Bundle dataBundle;
    int waktu;

    UjangEffect ujang = new UjangEffect();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        back = findViewById(R.id.back);
        play = findViewById(R.id.play);

        dataBundle = getIntent().getExtras();

        if (dataBundle != null) {
            waktu = dataBundle.getInt("waktu");
        } else {
            waktu = 0;
        }

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ujang.clickAnim(back);

                Runnable run = new Runnable() {
                    @Override
                    public void run() {
//
//                        Intent back = new Intent(Preview.this, TimeLimitActivity.class);
//                        back.putExtra("waktu", waktu);
//                        String p = getIntent().getStringExtra("p");
//                        String t = getIntent().getStringExtra("l");
//                        back.putExtra("p",p);
//                        back.putExtra("l",t);
                        onBackPressed();
                        //startActivity(back);
                        CustomIntent.customType(Preview.this, "fadein-to-fadeout");
                        backSound();

                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run, 300);
            }
        });

        ImageView img = findViewById(R.id.gambar);
        Bitmap sentBitmap = getIntent().getParcelableExtra("Gambar");
        img.setImageBitmap(sentBitmap);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("difficulty", "custom");
                data.putString("mode", "custom");

                Bitmap sentBitmap = getIntent().getParcelableExtra("Gambar");
                String p = getIntent().getStringExtra("p");
                String t = getIntent().getStringExtra("l");
                System.out.println("Preview P : " + p);
                System.out.println("Preview T : " + t);

                ujang.clickAnim(play);
                Intent play = new Intent(Preview.this, PuzzleActivity.class);
                play.putExtra("Gambar", sentBitmap);
                play.putExtra("p", p);
                play.putExtra("l", t);
                play.putExtras(data);
                play.putExtra("waktu", waktu);
                startActivity(play);
                CustomIntent.customType(Preview.this, "fadein-to-fadeout");
                playSound();
            }
        });

    }

    public void playSound() {

        if (pref.getString("soundSetting", null).equalsIgnoreCase("ON")) {
            voice = MediaPlayer.create(this, R.raw.adriantnt_bubble_clap);
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

    public void backSound() {

        if (pref.getString("soundSetting", null).equalsIgnoreCase("ON")) {
            voice = MediaPlayer.create(this, R.raw.bubble_cancel);
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
