package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import maes.tech.intentanim.CustomIntent;

public class ChooseImageActivity extends AppCompatActivity{

    ImageView back,next;
    MediaPlayer ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);

        back = (ImageView) findViewById(R.id.back);
        next = (ImageView) findViewById(R.id.next_img);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(ChooseImageActivity.this,rowcolumninput.class);
                startActivity(next);
                playSound();
                CustomIntent.customType(ChooseImageActivity.this,"fadein-to-fadeout");
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(ChooseImageActivity.this,MainActivity.class);
                startActivity(back);
                backSound();
                CustomIntent.customType(ChooseImageActivity.this,"fadein-to-fadeout");
            }
        });

    }

    public void playSound(){

        ok = MediaPlayer.create(this,R.raw.tone);

        ok.start();

    }

    public void backSound(){

        ok = MediaPlayer.create(this,R.raw.computer_error);
        ok.start();

    }

}
