package com.example.pkk_sip;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {

    Context context;
    TextView testText;
    ImageView customMenu,timedMenu,classicMenu,ranking,sound;
    MediaPlayer ok,bgm;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Sound soundController = new Sound();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = findViewById(R.id.sound);
        testText = findViewById(R.id.soundTest);
        customMenu = (ImageView) findViewById(R.id.custom);
        timedMenu = (ImageView) findViewById(R.id.time);
        classicMenu = (ImageView) findViewById(R.id.classic);
        ranking = (ImageView) findViewById(R.id.ranking);

        pref = getSharedPreferences("gamePrefs",Context.MODE_PRIVATE);

        testText.setText(pref.getString("soundSetting",null));

        if (pref.getString("soundSetting",null).equalsIgnoreCase("ON")){

            sound.setColorFilter(Color.argb(0,255,255,255));

        }

        if (pref.getString("soundSetting",null).equalsIgnoreCase("OFF")){
            sound.setColorFilter(Color.argb(100,20,20,20));
        }

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pref.getString("soundSetting",null).equalsIgnoreCase("ON")){
                    editor = pref.edit();
                    editor.putString("soundSetting","OFF");
                    editor.apply();
                    sound.setColorFilter(Color.argb(100,20,20,20));
                    testText.setText(pref.getString("soundSetting",null));
                }else{
                    editor = pref.edit();
                    editor.putString("soundSetting","ON");
                    editor.apply();
                    sound.setColorFilter(Color.argb(0,255,255,255));
                    testText.setText(pref.getString("soundSetting",null));
                }
            }
        });

        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRanking = new Intent(MainActivity.this,RankingDifficultyActivity.class);
                startActivity(toRanking);
                playSound();
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

        //ok = MediaPlayer.create(this,R.raw.tone);
        //ok.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
          //  @Override
            //public void onCompletion(MediaPlayer mediaPlayer) {
              //  mediaPlayer.reset();
                //mediaPlayer.release();
                //ok = null;
            //}
       // });

    }

    public void playSound(){

        if (pref.getString("soundSetting",null).equalsIgnoreCase("ON")){
            ok = MediaPlayer.create(this,R.raw.tone);
            ok.start();
            ok.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    ok.stop();
                    ok.release();
                }
            });
        }else{

        }
    }

    public void backSound(){

        if (pref.getString("soundSetting",null).equalsIgnoreCase("ON")){
            ok = MediaPlayer.create(this,R.raw.computer_error);
            ok.start();
            ok.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    ok.stop();
                    ok.release();
                }
            });
        }else{}

    }



}
