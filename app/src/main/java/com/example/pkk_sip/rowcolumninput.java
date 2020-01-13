package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;

public class rowcolumninput extends AppCompatActivity {

    TextView input1,input2;
    ImageView del,num2,num3,num4,num5,num6,num7,num8,num9;
    int posisi = 1;
    ImageView next,back;
    MediaPlayer voice;
    SharedPreferences pref;

    Bundle bundleSkipSound;
    int waktu;

    UjangEffect ujang = new UjangEffect();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rowcolumninput);


        input1 = (TextView) findViewById(R.id.input1);
        input2 = (TextView) findViewById(R.id.input2);
        back = (ImageView) findViewById(R.id.left);


        bundleSkipSound = getIntent().getExtras();

        if (bundleSkipSound != null){
            waktu = bundleSkipSound.getInt("waktu");
        }else{
            waktu = 0;
        }

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ujang.clickAnim(back);
                Runnable run = new Runnable() {
                    @Override
                    public void run() {

                            Intent back = new Intent(rowcolumninput.this, CustomTimeActivity.class);
                            startActivity(back);


                        CustomIntent.customType(rowcolumninput.this,"fadein-to-fadeout");
                        backSound();
                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run,300);

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
                ujang.clickAnim(del);
                input1.setText("2");
                input2.setText("2");
                input1.setTextSize(50);
                input2.setTextSize(50);
                backSound();
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ujang.clickAnim(next);
                Runnable run = new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(rowcolumninput.this,ChooseImageActivity.class);
                        intent.putExtra("p",input1.getText().toString());
                        intent.putExtra("l",input2.getText().toString());
                        intent.putExtra("waktu",waktu);
                        startActivity(intent);
                        playSound();
                        CustomIntent.customType(rowcolumninput.this,"fadein-to-fadeout");
                    }
                };
                Handler timer = new Handler();
                timer.postDelayed(run,300);

            }
        });

        initnumber(num2,"2");
        initnumber(num3,"3");
        initnumber(num4,"4");
        initnumber(num5,"5");
        initnumber(num6,"6");
        initnumber(num7,"7");
        initnumber(num8,"8");
        initnumber(num9,"9");

    }

    ArrayList<TextView> arrayinput = new ArrayList<TextView>();
    public void initnumber(final ImageView nomor, final String text){
        arrayinput.add(input1);
        arrayinput.add(input2);

        nomor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posisi==0){
                    posisi =1;
                }
                for(int i = 1;i<5;i++){
                    if(posisi == i){
                        arrayinput.get(i-1).setText(text);

                        for(int b = 0;b<2;b++){
                            if(b==i){
                                arrayinput.get(b).setTextSize(70);
                            }else{
                                arrayinput.get(b).setTextSize(50);
                            }

                        }
                    }
                }
                ujang.clickAnim(nomor);
                playSound();

                posisi++;
                if(posisi>2){
                    posisi = 0;
                } 
            }
        });
    }

    public void playSound(){

        if (pref.getString("soundSetting",null).equalsIgnoreCase("ON")){
            voice = MediaPlayer.create(this,R.raw.adriantnt_bubble_clap);
            voice.start();
            voice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
//                    voice.stop();
                    voice.release();
                }
            });
        }else{

        }
    }

    public void backSound(){

        if (pref.getString("soundSetting",null).equalsIgnoreCase("ON")){
            voice = MediaPlayer.create(this,R.raw.bubble_cancel);
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
