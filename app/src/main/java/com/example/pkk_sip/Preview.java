package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import maes.tech.intentanim.CustomIntent;

public class Preview extends AppCompatActivity {

    ImageView back,play;
    MediaPlayer voice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        back = findViewById(R.id.back);
        play = findViewById(R.id.play);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Preview.this,CustomTimeActivity.class);
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
                Intent play = new Intent(Preview.this,PuzzleActivity.class);
                play.putExtras(data);
                startActivity(play);
                CustomIntent.customType(Preview.this,"fadein-to-fadeout");
                playSound();
            }
        });

    }

    public void playSound(){

        voice = MediaPlayer.create(this,R.raw.tone);

        voice.start();

    }

    public void backSound(){

        voice = MediaPlayer.create(this,R.raw.computer_error);
        voice.start();

    }
}
