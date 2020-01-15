package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import maes.tech.intentanim.CustomIntent;

public class RankingActivity extends AppCompatActivity {

    Bundle data;
    ImageView classic, timed, custom, sip, back;
    MediaPlayer voice;
    SharedPreferences pref;

    UjangEffect ujang = new UjangEffect();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        sip = findViewById(R.id.logo);
        back = findViewById(R.id.back);

        data = getIntent().getExtras();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ujang.clickAnim(back);
                Runnable run = new Runnable() {
                    @Override
                    public void run() {

                        Intent back = new Intent(RankingActivity.this, MainActivity.class);
                        startActivity(back);
                        CustomIntent.customType(RankingActivity.this, "fadein-to-fadeout");
                        backSound();

                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run, 300);

            }
        });
        if (getIntent().getExtras() != null) {

            Bundle backData;
            backData = getIntent().getExtras();
            int backSound = backData.getInt("sound");
            //soundSet = backSound;

        } else {

            //soundSet = 1;

        }


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
