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

public class TimedDifficultyActivity extends AppCompatActivity {

    ImageView back,easy,medium,hard,sip;
    CountDownTimer jeda;
    MediaPlayer voice;
    SharedPreferences pref;

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
                data.putString("mode","timed");
                Intent toHardClassic = new Intent(TimedDifficultyActivity.this,PuzzleActivity.class);
                toHardClassic.putExtras(data);
                startActivity(toHardClassic);
                CustomIntent.customType(TimedDifficultyActivity.this,"fadein-to-fadeout");
                playSound();
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("difficulty","medium");
                data.putString("mode","timed");
                Intent toMediumClassic = new Intent(TimedDifficultyActivity.this,PuzzleActivity.class);
                toMediumClassic.putExtras(data);
                startActivity(toMediumClassic);
                CustomIntent.customType(TimedDifficultyActivity.this,"fadein-to-fadeout");
                playSound();
            }
        });

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("difficulty","easy");
                data.putString("mode","timed");
                Intent toEasyClassic = new Intent(TimedDifficultyActivity.this,PuzzleActivity.class);
                toEasyClassic.putExtras(data);
                startActivity(toEasyClassic);
                CustomIntent.customType(TimedDifficultyActivity.this,"fadein-to-fadeout");
                playSound();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMenu = new Intent(TimedDifficultyActivity.this,MainActivity.class);
                startActivity(toMenu);
                CustomIntent.customType(TimedDifficultyActivity.this,"fadein-to-fadeout");
                backSound();
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
