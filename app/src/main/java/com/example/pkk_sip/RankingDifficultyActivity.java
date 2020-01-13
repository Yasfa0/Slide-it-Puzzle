package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import maes.tech.intentanim.CustomIntent;

public class RankingDifficultyActivity extends AppCompatActivity {

    ImageView easy,medium,hard,back,sip;
    MediaPlayer voice;
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_difficulty);

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        back = (ImageView) findViewById(R.id.back);






        sip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RankingActivity = new Intent(RankingDifficultyActivity.this, RankingActivity.class);
                Bundle data = new Bundle();
                data.putString("difficulty","sip");
                RankingActivity.putExtras(data);
                startActivity(RankingActivity);
                CustomIntent.customType(RankingDifficultyActivity.this,"fadein-to-fadeout");
                playSound();
            }
        });

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RankingActivity = new Intent(RankingDifficultyActivity.this,RankingActivity.class);
                Bundle data = new Bundle();
                data.putString("difficulty","easy");
                RankingActivity.putExtras(data);
                startActivity(RankingActivity);
                CustomIntent.customType(RankingDifficultyActivity.this,"fadein-to-fadeout");
                playSound();
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RankingActivity = new Intent(RankingDifficultyActivity.this,RankingActivity.class);
                Bundle data = new Bundle();
                data.putString("difficulty","medium");
                RankingActivity.putExtras(data);
                startActivity(RankingActivity);
                CustomIntent.customType(RankingDifficultyActivity.this,"fadein-to-fadeout");
                playSound();
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RankingActivity = new Intent(RankingDifficultyActivity.this,RankingActivity.class);
                Bundle data = new Bundle();
                data.putString("difficulty","hard");
                RankingActivity.putExtras(data);
                startActivity(RankingActivity);
                CustomIntent.customType(RankingDifficultyActivity.this,"fadein-to-fadeout");
                playSound();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle backData = new Bundle();
                Intent back = new Intent(RankingDifficultyActivity.this,MainActivity.class);
                back.putExtras(backData);
                startActivity(back);
                CustomIntent.customType(RankingDifficultyActivity.this,"fadein-to-fadeout");
                backSound();
            }
        });

    }

    public void playSound(){

        if (pref.getString("soundSetting",null).equalsIgnoreCase("ON")){
            voice = MediaPlayer.create(this,R.raw.adriantnt_bubble_clap);
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
            voice = MediaPlayer.create(this,R.raw.bubble_cancel);
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


