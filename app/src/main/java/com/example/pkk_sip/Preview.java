package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import maes.tech.intentanim.CustomIntent;

public class Preview extends AppCompatActivity {

    ImageView back,play;
    MediaPlayer voice;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        back = findViewById(R.id.back);
        play = findViewById(R.id.play);

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Preview.this,ChooseImageActivity.class);
                startActivity(back);
                CustomIntent.customType(Preview.this,"fadein-to-fadeout");
                backSound();
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("difficulty","custom");
                data.putString("mode","custom");

                Bitmap sentBitmap = getIntent().getParcelableExtra("Gambar");
                String p = getIntent().getStringExtra("p");
                String t = getIntent().getStringExtra("l");

                Intent play = new Intent(Preview.this,PuzzleActivity.class);
                play.putExtra("Gambar",sentBitmap);
                play.putExtra("p",String.valueOf(p));
                play.putExtra("l",String.valueOf(t));
                play.putExtras(data);
                startActivity(play);
                CustomIntent.customType(Preview.this,"fadein-to-fadeout");
                playSound();
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
