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

public class CustomTimeActivity extends AppCompatActivity {

    TextView input1,input2,input3,input4;
    ImageView del,num0,num1,num2,num3,num4,num5,num6,num7,num8,num9,clear,left,right;
    int posisi = 0;
    MediaPlayer voice;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_time);


        input1 = (TextView) findViewById(R.id.input1);
        input2 = (TextView) findViewById(R.id.input2);
        input3 = (TextView) findViewById(R.id.input3);
        input4 = (TextView) findViewById(R.id.input4);

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        right = (ImageView) findViewById(R.id.right);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent preview = new Intent(CustomTimeActivity.this,ChooseImageActivity.class);
                startActivity(preview);
                playSound();
                CustomIntent.customType(CustomTimeActivity.this,"fadein-to-fadeout");
            }
        });

        left = (ImageView) findViewById(R.id.left);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(CustomTimeActivity.this,rowcolumninput.class);
                startActivity(back);
                backSound();
                CustomIntent.customType(CustomTimeActivity.this,"fadein-to-fadeout");
            }
        });

        input1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posisi =1;
                input1.setTextSize(70);
                input2.setTextSize(50);
                input3.setTextSize(50);
                input4.setTextSize(50);
                playSound();
            }
        });

       input2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               posisi =2;
               input1.setTextSize(50);
               input2.setTextSize(70);
               input3.setTextSize(50);
               input4.setTextSize(50);
               playSound();
           }
       });


       input3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               posisi =3;
               input1.setTextSize(50);
               input2.setTextSize(50);
               input3.setTextSize(70);
               input4.setTextSize(50);
               playSound();
           }
       });

       input4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               posisi =4;
               input1.setTextSize(50);
               input2.setTextSize(50);
               input3.setTextSize(50);
               input4.setTextSize(70);
               playSound();
           }
       });

        num0 = (ImageView) findViewById(R.id.num0);
        num1 = (ImageView) findViewById(R.id.num1);
        num2 = (ImageView) findViewById(R.id.num2);
        num3 = (ImageView) findViewById(R.id.num3);
        num4 = (ImageView) findViewById(R.id.num4);
        num5 = (ImageView) findViewById(R.id.num5);
        num6 = (ImageView) findViewById(R.id.num6);
        num7 = (ImageView) findViewById(R.id.num7);
        num8 = (ImageView) findViewById(R.id.num8);
        num9 = (ImageView) findViewById(R.id.num9);
        del = (ImageView) findViewById(R.id.del);
        clear = (ImageView) findViewById(R.id.clear);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input1.setText("0");
                input2.setText("0");
                input3.setText("0");
                input4.setText("0");
                posisi = 1;
                input1.setTextSize(70);
                input2.setTextSize(50);
                input3.setTextSize(50);
                input4.setTextSize(50);
                playSound();

            }
        });


        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("0");


                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){

                    input2.setText("0");


                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();

                }else if (posisi == 3){
                    input3.setText("0");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(70);
                    input4.setTextSize(50);
                    playSound();
                }else if (posisi == 4){
                    input4.setText("0");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(70);
                    playSound();
                }



                if (posisi >= 4){
                    posisi = 0;
                }

                posisi++;

            }
        });

        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("1");


                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){

                    input2.setText("1");


                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();

                }else if (posisi == 3){
                    input3.setText("1");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(70);
                    input4.setTextSize(50);
                    playSound();
                }else if (posisi == 4){
                    input4.setText("1");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(70);
                    playSound();
                }



                if (posisi >= 4){
                    posisi = 0;
                }

                posisi++;

            }
        });



        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("4");


                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){

                    input2.setText("4");


                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();

                }else if (posisi == 3){
                    input3.setText("4");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(70);
                    input4.setTextSize(50);
                    playSound();
                }else if (posisi == 4){
                    input4.setText("4");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(70);
                    playSound();
                }



                if (posisi >= 4){
                    posisi = 0;
                }

                posisi++;

            }
        });

        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("3");


                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){

                    input2.setText("3");


                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();

                }else if (posisi == 3){
                    input3.setText("3");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(70);
                    input4.setTextSize(50);
                    playSound();
                }else if (posisi == 4){
                    input4.setText("3");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(70);
                    playSound();
                }



                if (posisi >= 4){
                    posisi = 0;
                }

                posisi++;

            }
        });

        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("5");


                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){

                    input2.setText("5");


                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();

                }else if (posisi == 3){
                    input3.setText("5");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(70);
                    input4.setTextSize(50);
                    playSound();
                }else if (posisi == 4){
                    input4.setText("5");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(70);
                    playSound();
                }



                if (posisi >= 4){
                    posisi = 0;
                }

                posisi++;

            }
        });

        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("6");


                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){

                    input2.setText("6");


                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();

                }else if (posisi == 3){
                    input3.setText("6");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(70);
                    input4.setTextSize(50);
                    playSound();
                }else if (posisi == 4){
                    input4.setText("6");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(70);
                    playSound();
                }



                if (posisi >= 4){
                    posisi = 0;
                }

                posisi++;

            }
        });

        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("7");


                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){

                    input2.setText("7");


                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();

                }else if (posisi == 3){
                    input3.setText("7");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(70);
                    input4.setTextSize(50);
                    playSound();
                }else if (posisi == 4){
                    input4.setText("7");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(70);
                    playSound();
                }



                if (posisi >= 4){
                    posisi = 0;
                }

                posisi++;

            }
        });

        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("8");


                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){

                    input2.setText("8");


                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();

                }else if (posisi == 3){
                    input3.setText("8");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(70);
                    input4.setTextSize(50);
                    playSound();
                }else if (posisi == 4){
                    input4.setText("8");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(70);
                    playSound();
                }



                if (posisi >= 4){
                    posisi = 0;
                }

                posisi++;

            }
        });

        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("9");


                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){

                    input2.setText("9");


                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();

                }else if (posisi == 3){
                    input3.setText("9");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(70);
                    input4.setTextSize(50);
                    playSound();
                }else if (posisi == 4){
                    input4.setText("9");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(70);
                    playSound();
                }



                if (posisi >= 4){
                    posisi = 0;
                }

                posisi++;

            }
        });

        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 1){
                    input1.setText("2");


                    input1.setTextSize(70);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();
                }
                else if (posisi == 2){

                    input2.setText("2");


                    input1.setTextSize(50);
                    input2.setTextSize(70);
                    input3.setTextSize(50);
                    input4.setTextSize(50);
                    playSound();

                }else if (posisi == 3){
                    input3.setText("2");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(70);
                    input4.setTextSize(50);
                    playSound();
                }else if (posisi == 4){
                    input4.setText("2");


                    input1.setTextSize(50);
                    input2.setTextSize(50);
                    input3.setTextSize(50);
                    input4.setTextSize(70);
                    playSound();
                }



                if (posisi >= 4){
                    posisi = 0;
                }

                posisi++;

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
