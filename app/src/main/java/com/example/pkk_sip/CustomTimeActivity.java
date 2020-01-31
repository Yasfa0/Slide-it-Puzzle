package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;

public class CustomTimeActivity extends AppCompatActivity {

    TextView input1, input2, input3, input4;
    ImageView left, right;
    TextView del, num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, clear;
    int posisi = 0;
    MediaPlayer voice;
    SharedPreferences pref;

    String val1, val2, val3, val4;

    UjangEffect ujang = new UjangEffect();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_time);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        input1 = (TextView) findViewById(R.id.input1);
        input2 = (TextView) findViewById(R.id.input2);
        input3 = (TextView) findViewById(R.id.input3);
        input4 = (TextView) findViewById(R.id.input4);

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        right = (ImageView) findViewById(R.id.right);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ujang.clickAnim(right);
                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        Intent preview = new Intent(CustomTimeActivity.this, rowcolumninput.class);
                        val1 = input1.getText().toString();
                        val2 = input2.getText().toString();
                        val3 = input3.getText().toString();
                        val4 = input4.getText().toString();

                        String angkaDetik = val3 + val4;
                        String angkaMenit = val1 + val2;

                        int milisDetik = Integer.parseInt(angkaDetik) * 1000;
                        int milisMenit = Integer.parseInt(angkaMenit) * 60000;

                        int milisTotal = milisMenit + milisDetik;

                        preview.putExtra("waktu", milisTotal);
                        startActivity(preview);
                        playSound();
                        CustomIntent.customType(CustomTimeActivity.this, "fadein-to-fadeout");

                    }
                };


                Handler timer = new Handler();
                timer.postDelayed(run, 300);

            }
        });

        left = (ImageView) findViewById(R.id.left);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ujang.clickAnim(left);

                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        onBackPressed();
                        backSound();
                        CustomIntent.customType(CustomTimeActivity.this, "fadein-to-fadeout");
                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run, 300);

            }
        });

        input1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posisi = 1;
                input1.setTextSize(40);
                input2.setTextSize(35);
                input3.setTextSize(35);
                input4.setTextSize(35);
                playSound();
            }
        });

        input2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posisi = 2;
                input1.setTextSize(35);
                input2.setTextSize(40);
                input3.setTextSize(35);
                input4.setTextSize(35);
                playSound();
            }
        });


        input3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posisi = 3;
                input1.setTextSize(35);
                input2.setTextSize(35);
                input3.setTextSize(40);
                input4.setTextSize(35);
                playSound();
            }
        });

        input4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posisi = 4;
                input1.setTextSize(35);
                input2.setTextSize(35);
                input3.setTextSize(35);
                input4.setTextSize(40);
                playSound();
            }
        });

        num0 = findViewById(R.id.num0);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);
        del = findViewById(R.id.del);
        clear = findViewById(R.id.clear);
        num7 = findViewById(R.id.num7);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ujang.clickAnim(clear);
                Runnable run = new Runnable() {
                    @Override
                    public void run() {

                        input1.setText("0");
                        input2.setText("0");
                        input3.setText("0");
                        input4.setText("0");
                        posisi = 1;
                        input1.setTextSize(40);
                        input2.setTextSize(35);
                        input3.setTextSize(35);
                        input4.setTextSize(35);
                        playSound();


                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run, 300);

            }

        });


        initnumber(num0, "0");
        initnumber(num1, "1");
        initnumber(num2, "2");
        initnumber(num3, "3");
        initnumber(num4, "4");
        initnumber(num5, "5");
        initnumber(num6, "6");
        initnumber(num7, "7");
        initnumber(num8, "8");
        initnumber(num9, "9");
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi == 0) {
                    posisi = 4;
                }
                arrayinput.get(posisi - 1).setText("0");
                for (int i = 0; i < 4; i++) {
                    if ((i + 1) == posisi) {
                        arrayinput.get(i).setTextSize(40);
                    } else {
                        arrayinput.get(posisi).setTextSize(35);
                    }
                }


//                if(posisi != 1){
                posisi--;
//                }
                ujang.clickAnim(del);
                playSound();

            }
        });

//        num2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (posisi == 1){
//                    input1.setText("2");
//
//
//                    input1.setTextSize(70);
//                    input2.setTextSize(50);
//                    input3.setTextSize(50);
//                    input4.setTextSize(50);
//                    playSound();
//                }
//                else if (posisi == 2){
//
//                    input2.setText("2");
//
//
//                    input1.setTextSize(50);
//                    input2.setTextSize(70);
//                    input3.setTextSize(50);
//                    input4.setTextSize(50);
//                    playSound();
//
//                }else if (posisi == 3){
//                    input3.setText("2");
//
//
//                    input1.setTextSize(50);
//                    input2.setTextSize(50);
//                    input3.setTextSize(70);
//                    input4.setTextSize(50);
//                    playSound();
//                }else if (posisi == 4){
//                    input4.setText("2");
//
//
//                    input1.setTextSize(50);
//                    input2.setTextSize(50);
//                    input3.setTextSize(50);
//                    input4.setTextSize(70);
//                    playSound();
//                }
//
//
//
//                if (posisi >= 4){
//                    posisi = 0;
//                }
//
//                posisi++;
//
//            }
//        });

    }


    ArrayList<TextView> arrayinput = new ArrayList<TextView>();

    public void initnumber(final TextView nomor, final String text) {
        arrayinput.add(input1);
        arrayinput.add(input2);
        arrayinput.add(input3);
        arrayinput.add(input4);

        nomor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posisi == 0) {
                    posisi = 1;
                }
                for (int i = 1; i < 5; i++) {
                    if (posisi == i) {
                        arrayinput.get(i - 1).setText(text);

                        for (int b = 0; b < 4; b++) {
                            if (b == i) {
                                arrayinput.get(b).setTextSize(40);
                            } else {
                                arrayinput.get(b).setTextSize(35);
                            }

                        }
                    }
                }
                ujang.clickAnim(nomor);
                playSound();

                posisi++;
                if (posisi > 4) {
                    posisi = 0;
                }
            }
        });
    }

    public void playSound() {

        if (pref.getString("soundSetting", null).equalsIgnoreCase("ON")) {
            voice = MediaPlayer.create(this, R.raw.adriantnt_bubble_clap);
            voice.start();
            voice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    voice.stop();
                    voice.release();
                }
            });
        } else {

        }
    }

    public void backSound() {

        if (pref.getString("soundSetting", null).equalsIgnoreCase("ON")) {
            voice = MediaPlayer.create(this, R.raw.bubble_cancel);
            voice.start();
            voice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    voice.stop();
                    voice.release();
                }
            });
        } else {
        }

    }


}
