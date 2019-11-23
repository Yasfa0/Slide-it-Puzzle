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

public class RankingActivity extends AppCompatActivity {

    Bundle data;
    ImageView classic,timed,custom,sip,back;
    MediaPlayer voice;
    SharedPreferences pref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        sip = findViewById(R.id.logo);
        classic = findViewById(R.id.classic);
        custom = findViewById(R.id.custom);
        timed = findViewById(R.id.timed);
        back = findViewById(R.id.back);

        data = getIntent().getExtras();
        String difficulty = data.getString("difficulty");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(RankingActivity.this,RankingDifficultyActivity.class);
                startActivity(back);
                CustomIntent.customType(RankingActivity.this,"fadein-to-fadeout");
                backSound();

            }
        });
        if (getIntent().getExtras() != null){

            Bundle backData;
            backData = getIntent().getExtras();
            int backSound = backData.getInt("sound");
            //soundSet = backSound;

        }else{

            //soundSet = 1;

        }

        if (difficulty.equalsIgnoreCase("easy")){
            classic.setVisibility(View.VISIBLE);
            custom.setVisibility(View.VISIBLE);
            timed.setVisibility(View.VISIBLE);
            sip.setVisibility(View.INVISIBLE);
        }


        if (difficulty.equalsIgnoreCase("medium")){
            classic.setVisibility(View.VISIBLE);
            custom.setVisibility(View.VISIBLE);
            timed.setVisibility(View.VISIBLE);
            sip.setVisibility(View.INVISIBLE);
        }


        if (difficulty.equalsIgnoreCase("hard")){
            classic.setVisibility(View.VISIBLE);
            custom.setVisibility(View.VISIBLE);
            timed.setVisibility(View.VISIBLE);
            sip.setVisibility(View.INVISIBLE);
        }


        if (difficulty.equalsIgnoreCase("sip")){
            classic.setVisibility(View.INVISIBLE);
            custom.setVisibility(View.INVISIBLE);
            timed.setVisibility(View.INVISIBLE);
            sip.setVisibility(View.VISIBLE);
        }

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
