package com.example.pkk_sip;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {

    ImageView customMenu,timedMenu,classicMenu,ranking,sound;
    MediaPlayer ok;
    int soundSet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = findViewById(R.id.sound);
        customMenu = (ImageView) findViewById(R.id.custom);
        timedMenu = (ImageView) findViewById(R.id.time);
        classicMenu = (ImageView) findViewById(R.id.classic);
        ranking = (ImageView) findViewById(R.id.ranking);


        if (getIntent().getExtras() != null){

            Bundle backData;
            backData = getIntent().getExtras();
            int backSound = backData.getInt("sound");
            soundSet = backSound;

        }else{

            soundSet = 1;

        }



        if (soundSet == 0){
            sound.setColorFilter(Color.argb(180,192,174,167));

        }else if (soundSet == 1){
            sound.setColorFilter(Color.argb(0,255,255,255));

        }


        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundSet == 1){
                sound.setColorFilter(Color.argb(180,192,174,167));
                soundSet = 0;
                }else if (soundSet == 0){
                    sound.setColorFilter(Color.argb(0,255,255,255));
                    soundSet = (1);
                    backSound();
                }
            }
        });


        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soundVar = soundSet;
                Bundle data = new Bundle();
                data.putInt("sound",soundVar);
                Intent toRanking = new Intent(MainActivity.this,RankingDifficultyActivity.class);
                playSound();
                toRanking.putExtras(data);
                startActivity(toRanking);
                CustomIntent.customType(MainActivity.this,"fadein-to-fadeout");
            }
        });

        customMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCustom = new Intent(MainActivity.this,ChooseImageActivity.class);
                startActivity(toCustom);
                playSound();
                CustomIntent.customType(MainActivity.this,"fadein-to-fadeout");
            }
        });

        timedMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toTimed = new Intent(MainActivity.this,TimedDifficultyActivity.class);
                startActivity(toTimed);
                playSound();
                CustomIntent.customType(MainActivity.this,"fadein-to-fadeout");
            }
        });

        classicMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toClassic = new Intent(MainActivity.this,ClassicDifficultyActivity.class);
                startActivity(toClassic);
                playSound();
                CustomIntent.customType(MainActivity.this,"fadein-to-fadeout");
            }
        });

    }

    public void playSound(){

        if (soundSet == 1){

        ok = MediaPlayer.create(this,R.raw.tone);

        ok.start();}else if (soundSet == 0){

        }

    }

    public void backSound(){

        if (soundSet == 1){

            ok = MediaPlayer.create(this,R.raw.computer_error);

            ok.start();}else if (soundSet == 0){

        }
    }

}
