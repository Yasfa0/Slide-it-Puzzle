package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;

public class PuzzleActivity extends AppCompatActivity {


    Bundle bundle;
    ImageView no1,no2,no3,start,blur,classic,timed,custom,pause,timeBox;
    ImageView up,down,left,right;
    Button resume,toMenu,restart;
    CardView card,cardmain;
    TextView timer,skip;
    MediaPlayer voice;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);



        toMenu = findViewById(R.id.menu);
        restart = findViewById(R.id.restart);
        timeBox = findViewById(R.id.timer_show);
        timer = findViewById(R.id.timer);
        cardmain = findViewById(R.id.card);
        classic = findViewById(R.id.classic);
        timed = findViewById(R.id.timed);
        custom = findViewById(R.id.custom);
        no1 = (ImageView) findViewById(R.id.no1);
        no2 = (ImageView) findViewById(R.id.no2);
        no3 = (ImageView) findViewById(R.id.no3);
        start = (ImageView) findViewById(R.id.start);
        blur = (ImageView) findViewById(R.id.blur);
        up = findViewById(R.id.pad_up);
        down = findViewById(R.id.pad_down);
        right = findViewById(R.id.pad_right);
        left = findViewById(R.id.pad_left);

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        resume = findViewById(R.id.resume);

        //Tombol Skip hanya untuk keperluan Testing
        skip = findViewById(R.id.skipStage);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result = new Intent(PuzzleActivity.this,HasilActivity.class);
                startActivity(result);
                PuzzleActivity.super.finish();
                CustomIntent.customType(PuzzleActivity.this,"fadein-to-fadeout");
            }
        });

        //Pause Sementara (Nanti ganti sama Fragment)
        pause = findViewById(R.id.pause);
        card = findViewById(R.id.difficulty);

        blur.setVisibility(View.VISIBLE);
        no1.setVisibility(View.INVISIBLE);
        no2.setVisibility(View.INVISIBLE);
        no3.setVisibility(View.INVISIBLE);
        start.setVisibility(View.INVISIBLE);
        pause.setVisibility(View.INVISIBLE);

        bundle = getIntent().getExtras();
        String difficulty = bundle.getString("difficulty");
        String mode = bundle.getString("mode");


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card.setVisibility(View.VISIBLE);
                blur.setVisibility(View.VISIBLE);
                cardmain.setVisibility(View.INVISIBLE);
            }
        });

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card.setVisibility(View.INVISIBLE);
                blur.setVisibility(View.INVISIBLE);
                cardmain.setVisibility(View.VISIBLE);
                playSound();
            }
        });

        toMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMenu = new Intent(PuzzleActivity.this,MainActivity.class);
                startActivity(toMenu);
                backSound();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent restart = getIntent();
                finish();
                startActivity(restart);
                playSound();
            }
        });

        if (mode.equalsIgnoreCase("timed")){

            custom.setVisibility(View.INVISIBLE);
            classic.setVisibility(View.INVISIBLE);
            timed.setVisibility(View.VISIBLE);

        }


        if (mode.equalsIgnoreCase("classic")){

            custom.setVisibility(View.INVISIBLE);
            classic.setVisibility(View.VISIBLE);
            timed.setVisibility(View.INVISIBLE);

        }


        if (mode.equalsIgnoreCase("custom")){

            custom.setVisibility(View.VISIBLE);
            classic.setVisibility(View.INVISIBLE);
            timed.setVisibility(View.INVISIBLE);

        }

        new CountDownTimer(5000,1000) {

            public void onTick(long waktu){
                no1.setVisibility(View.VISIBLE);
                //chimeSound();


                if (waktu < 4000){
                    no2.setVisibility(View.VISIBLE);
                    //chimeSound();
                }

                if (waktu < 3000){
                    no3.setVisibility(View.VISIBLE);
                    //chimeSound();
                }

                if (waktu < 2000){
                    start.setVisibility(View.VISIBLE);
                    //chimeSound();
                }

                }

            public void onFinish(){
                timeBox.setVisibility(View.VISIBLE);
                timer.setVisibility(View.VISIBLE);
                blur.setVisibility(View.INVISIBLE);
                no1.setVisibility(View.INVISIBLE);
                no2.setVisibility(View.INVISIBLE);
                no3.setVisibility(View.INVISIBLE);
                start.setVisibility(View.INVISIBLE);
                cardmain.setVisibility(View.VISIBLE);
                pause.setVisibility(View.VISIBLE);
                skip.setVisibility(View.VISIBLE);
            }

        }.start();


        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound();
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound();
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    public void chimeSound(){
        if (pref.getString("soundSetting",null).equalsIgnoreCase("ON")){
            voice = MediaPlayer.create(this,R.raw.electronic_chime);
            voice.start();
            voice.setLooping(false);
            voice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    voice.stop();
                    voice.release();
                }
            });
        }else{}
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
