package com.example.pkk_sip;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {

    Context context;
    UjangEffect ujang = new UjangEffect();
    //    TextView testText;
    ImageView customMenu, ranking, sound, exit;
    MediaPlayer ok, bgm;
    Bundle firstBundle;
    boolean firstTime;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
//    Sound soundController = new Sound();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstBundle = getIntent().getExtras();
        if (firstBundle != null) {
            firstTime = firstBundle.getBoolean("firstTime");
        } else {
            firstTime = false;
        }

        //Sementara
        if (firstTime) {
            bgm = MediaPlayer.create(this, R.raw.bgm_menu);
            bgm.setLooping(true);
            bgm.start();
        }

        sound = findViewById(R.id.sound);
//        testText = findViewById(R.id.soundTest);
        customMenu = (ImageView) findViewById(R.id.custom);
        ranking = (ImageView) findViewById(R.id.ranking);
        exit = (ImageView) findViewById(R.id.exit);

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

//        testText.setText(pref.getString("soundSetting", null));

        if (pref.getString("soundSetting", null).equalsIgnoreCase("ON")) {
            sound.setColorFilter(Color.argb(0, 255, 255, 255));

        }

        if (pref.getString("soundSetting", null).equalsIgnoreCase("OFF")) {
            sound.setColorFilter(Color.argb(100, 20, 20, 20));
        }


        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ujang.clickAnim(sound);
                if (pref.getString("soundSetting", null).equalsIgnoreCase("ON")) {
                    editor = pref.edit();
                    editor.putString("soundSetting", "OFF");
                    editor.apply();
                    sound.setColorFilter(Color.argb(100, 20, 20, 20));
//                    testText.setText(pref.getString("soundSetting", null));
                } else {
                    editor = pref.edit();
                    editor.putString("soundSetting", "ON");
                    editor.apply();
                    sound.setColorFilter(Color.argb(0, 255, 255, 255));
//                    testText.setText(pref.getString("soundSetting", null));
                }
            }
        });

        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ujang.clickAnim(ranking);
                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        Intent toRanking = new Intent(MainActivity.this, RankingActivity.class);
                        playSound();
                        startActivity(toRanking);
                        CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");


                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run, 300);

            }
        });

        customMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ujang.clickAnim(customMenu);
                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        Intent toCustom = new Intent(MainActivity.this, TimeLimitActivity.class);
                        startActivity(toCustom);
                        playSound();
                        CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run, 300);

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ujang.clickAnim(exit);

                backSound();

                Runnable run = new Runnable() {
                    @Override
                    public void run() {

                        Intent toLauncher = new Intent(Intent.ACTION_MAIN);
                        toLauncher.addCategory(Intent.CATEGORY_HOME);
                        toLauncher.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        bgm.stop();
                        startActivity(toLauncher);

//                        if(android.os.Build.VERSION.SDK_INT >= 21)
//                        {
//                            finishAndRemoveTask();
//                        }
//                        else
//                        {
                        finish();
//                        }

                        CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");

                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run, 300);

            }
        });

        //ok = MediaPlayer.create(this,R.raw.tone);
        //ok.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        //  @Override
        //public void onCompletion(MediaPlayer mediaPlayer) {
        //  mediaPlayer.reset();
        //mediaPlayer.release();
        //ok = null;
        //}
        // });

    }

    public void playSound() {

        if (pref.getString("soundSetting", null).equalsIgnoreCase("ON")) {
            ok = MediaPlayer.create(this, R.raw.adriantnt_bubble_clap);
            ok.start();
            ok.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    ok.stop();
                    ok.release();
                }
            });
        } else {

        }
    }

    public void backSound() {

        if (pref.getString("soundSetting", null).equalsIgnoreCase("ON")) {
            ok = MediaPlayer.create(this, R.raw.bubble_cancel);
            ok.start();
            ok.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    ok.stop();
                    ok.release();
                }
            });
        } else {
        }

    }


}
