package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import maes.tech.intentanim.CustomIntent;

public class RankingDifficultyActivity extends AppCompatActivity {

    ImageView easy,medium,hard,back,sip;
    MediaPlayer voice;
    Bundle data;
    int soundSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_difficulty);


        back = (ImageView) findViewById(R.id.back);
        easy = (ImageView) findViewById(R.id.easy);
        medium = findViewById(R.id.medium);
        sip = findViewById(R.id.sip);
        hard = findViewById(R.id.hard);

        data = getIntent().getExtras();
        soundSet = data.getInt("sound");


        if (getIntent().getExtras() != null){

            Bundle backData;
            backData = getIntent().getExtras();
            int backSound = backData.getInt("sound");
            soundSet = backSound;

        }else{

            soundSet = 1;

        }


        sip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RankingActivity = new Intent(RankingDifficultyActivity.this, RankingActivity.class);
                Bundle data = new Bundle();
                data.putString("difficulty","sip");
                data.putInt("sound",soundSet);
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
                data.putInt("sound",soundSet);
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
                data.putInt("sound",soundSet);
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
                data.putInt("sound",soundSet);
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
                backData.putInt("sound",soundSet);
                Intent back = new Intent(RankingDifficultyActivity.this,MainActivity.class);
                back.putExtras(backData);
                startActivity(back);
                CustomIntent.customType(RankingDifficultyActivity.this,"fadein-to-fadeout");
                backSound();
            }
        });

    }

    public void playSound(){

        if (soundSet == 1){
        voice = MediaPlayer.create(this,R.raw.tone);

        voice.start();}else if (soundSet == 0){
        }

    }

    public void backSound(){

        if (soundSet == 1){
        voice = MediaPlayer.create(this,R.raw.computer_error);
        voice.start();}else if (soundSet == 0){

        }

    }

}
