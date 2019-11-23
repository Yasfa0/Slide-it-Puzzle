package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;

public class rowcolumninput extends AppCompatActivity {

    TextView input1,input2;
    ImageView del,num2,num3,num4,num5,num6,num7,num8,num9;
    int posisi = 1;
    ImageView next,back;
    MediaPlayer voice;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rowcolumninput);


        input1 = (TextView) findViewById(R.id.input1);
        input2 = (TextView) findViewById(R.id.input2);
        back = (ImageView) findViewById(R.id.left);

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(rowcolumninput.this,ChooseImageActivity.class);
                startActivity(back);
                CustomIntent.customType(rowcolumninput.this,"fadein-to-fadeout");
                backSound();
            }
        });

        input1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posisi =1;
                input1.setTextSize(70);
                input2.setTextSize(50);
                playSound();
            }
        });

        input2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posisi = 2;
                input1.setTextSize(50);
                input2.setTextSize(70);
                playSound();
            }
        });


        num2 = (ImageView) findViewById(R.id.num2);
        num3 = (ImageView) findViewById(R.id.num3);
        num4 = (ImageView) findViewById(R.id.num4);
        num5 = (ImageView) findViewById(R.id.num5);
        num6 = (ImageView) findViewById(R.id.num6);
        num7 = (ImageView) findViewById(R.id.num7);
        num8 = (ImageView) findViewById(R.id.num8);
        num9 = (ImageView) findViewById(R.id.num9);
        del = (ImageView) findViewById(R.id.del);
        next = (ImageView) findViewById(R.id.right);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input1.setText("2");
                input2.setText("2");
                input1.setTextSize(50);
                input2.setTextSize(50);
                backSound();
            }
        });

        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("4");
                    posisi = 2;

                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){

                    input2.setText("4");
                    posisi = 1;

                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    playSound();

                }
            }
        });

        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("3");
                    posisi = 2;

                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){
                    input2.setText("3");
                    posisi = 1;

                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    playSound();

                }
            }
        });

        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("5");
                    posisi = 2;

                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){
                    input2.setText("5");
                    posisi = 1;

                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    playSound();
                }
            }
        });

        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("6");
                    posisi = 2;


                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){
                    input2.setText("6");
                    posisi = 1;

                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    playSound();
                }
            }
        });

        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("7");
                    posisi = 2;

                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){
                    input2.setText("7");

                    posisi = 1;
                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    playSound();
                }
            }
        });

        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("8");
                    posisi = 2;

                    input1.setTextSize(70);
                    input2.setTextSize(50);
                }
                else if (posisi == 2){
                    input2.setText("8");
                    posisi = 1;
                    input1.setTextSize(50);
                    input2.setTextSize(70);

                    playSound();
                }
            }
        });

        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("9");
                    posisi = 2;

                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){
                    input2.setText("9");

                    posisi = 1;
                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    playSound();
                }
            }
        });

        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (posisi == 1){
                input1.setText("2");
                posisi = 2;

                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){
                 input2.setText("2");
                    posisi = 1;
                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    playSound();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rowcolumninput.this,CustomTimeActivity.class);
                startActivity(intent);
                playSound();
                CustomIntent.customType(rowcolumninput.this,"fadein-to-fadeout");
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
