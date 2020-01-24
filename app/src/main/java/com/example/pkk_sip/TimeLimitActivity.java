package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;

public class TimeLimitActivity extends AppCompatActivity {

    ImageView back, yes, no;
    int waktu;
    TextView questionTextView;
    CountDownTimer jeda;
    MediaPlayer voice;
    SharedPreferences pref;
    Bundle bundle;
    String questionTeks;
    boolean noImage;

    String p, l;

    UjangEffect ujang = new UjangEffect();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timed_difficulty);

        questionTextView = findViewById(R.id.labelDifficulty);
        back = (ImageView) findViewById(R.id.back);
        yes = (ImageView) findViewById(R.id.yes);
        no = (ImageView) findViewById(R.id.no);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            waktu = bundle.getInt("waktu");
            questionTeks = "Pilih Gambar \n dari Galeri?";
            questionTextView.setText(questionTeks);
            p = bundle.getString("p");
            l = bundle.getString("l");
        } else {
            waktu = 0;
            questionTeks = " Main dengan \n Batas Waktu?";
            questionTextView.setText(questionTeks);
        }

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ujang.clickAnim(yes);
                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        Intent toCustom = new Intent(TimeLimitActivity.this, CustomTimeActivity.class);


                        if (bundle != null) {
                            toCustom = new Intent(TimeLimitActivity.this, ChooseImageActivity.class);
                            toCustom.putExtra("waktu", waktu);
                            toCustom.putExtra("p", p);
                            toCustom.putExtra("l", l);
                            toCustom.putExtra("status_gambar", false);
                        } else {
                            toCustom.putExtra("waktu", 0);
                        }

                        startActivity(toCustom);
                        playSound();
                        CustomIntent.customType(TimeLimitActivity.this, "fadein-to-fadeout");
                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run, 300);

            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ujang.clickAnim(no);
                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        Intent toCustom = new Intent(TimeLimitActivity.this, rowcolumninput.class);

                        if (bundle != null) {
                            toCustom = new Intent(TimeLimitActivity.this, ChooseImageActivity.class);
                            toCustom.putExtra("waktu", waktu);
                            toCustom.putExtra("status_gambar", true);
                            toCustom.putExtra("p", p);
                            toCustom.putExtra("l", l);
                        } else {
                            toCustom.putExtra("waktu", 0);
                        }

                        startActivity(toCustom);
                        playSound();
                        CustomIntent.customType(TimeLimitActivity.this, "fadein-to-fadeout");
                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run, 300);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ujang.clickAnim(back);
                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        Intent toMenu = new Intent(TimeLimitActivity.this, MainActivity.class);

                        if (bundle != null) {
                            toMenu = new Intent(TimeLimitActivity.this, rowcolumninput.class);
                            toMenu.putExtra("waktu", waktu);
                        }

                        startActivity(toMenu);
                        CustomIntent.customType(TimeLimitActivity.this, "fadein-to-fadeout");
                        backSound();
                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run, 300);

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
