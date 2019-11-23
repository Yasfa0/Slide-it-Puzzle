package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

import maes.tech.intentanim.CustomIntent;

public class ClassicDifficultyActivity extends AppCompatActivity {

    ImageView back,easy,medium,hard,sip;
    CountDownTimer jeda;
    MediaPlayer voice;
    SharedPreferences pref;
    MediaPlayer bgm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_difficulty);



        back = (ImageView) findViewById(R.id.back);
        easy = (ImageView) findViewById(R.id.easy);
        medium = (ImageView) findViewById(R.id.medium);
        hard = (ImageView) findViewById(R.id.hard);
        sip = (ImageView) findViewById(R.id.sip);

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("difficulty","hard");
                data.putString("mode","classic");
                Intent toMediumClassic = new Intent(ClassicDifficultyActivity.this,PuzzleActivity.class);
                toMediumClassic.putExtras(data);
                startActivity(toMediumClassic);
                playSound();
                CustomIntent.customType(ClassicDifficultyActivity.this,"fadein-to-fadeout");
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("difficulty","medium");
                data.putString("mode","classic");
                Intent toMediumClassic = new Intent(ClassicDifficultyActivity.this,PuzzleActivity.class);
                toMediumClassic.putExtras(data);
                startActivity(toMediumClassic);
                playSound();
                CustomIntent.customType(ClassicDifficultyActivity.this,"fadein-to-fadeout");
            }
        });

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("difficulty","easy");
                data.putString("mode","classic");
                Intent toEasyClassic = new Intent(ClassicDifficultyActivity.this,PuzzleActivity.class);
                toEasyClassic.putExtras(data);
                startActivity(toEasyClassic);
                playSound();
                CustomIntent.customType(ClassicDifficultyActivity.this,"fadein-to-fadeout");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMenu = new Intent(ClassicDifficultyActivity.this,MainActivity.class);
                startActivity(toMenu);
                backSound();
                CustomIntent.customType(ClassicDifficultyActivity.this,"fadein-to-fadeout");
            }
        });

    }

    public void playSound(){

        if (pref.getString("soundSetting",null).equalsIgnoreCase("ON")){
            voice = MediaPlayer.create(this,R.raw.tone);
            voice.start();
            voice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    voice.stop();
                    voice.release();
                }
            });
        }else{

        }
    }

    public void backSound(){

        if (pref.getString("soundSetting",null).equalsIgnoreCase("ON")){
            voice = MediaPlayer.create(this,R.raw.computer_error);
            voice.start();
            voice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    voice.stop();
                    voice.release();
                }
            });
        }else{}

    }
}
