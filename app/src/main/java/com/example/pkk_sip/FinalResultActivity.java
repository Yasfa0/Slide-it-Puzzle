package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;

public class FinalResultActivity extends AppCompatActivity {

    Bundle hasil;
    TextView nama;
    ImageButton restart,menu;
    MediaPlayer ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        restart = findViewById(R.id.restart);
        menu = findViewById(R.id.menu);
        hasil = getIntent().getExtras();
        nama = findViewById(R.id.namaUser);
        String name = hasil.getString("nama");

        nama.setText(name);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keMenu = new Intent(FinalResultActivity.this,MainActivity.class);
                startActivity(keMenu);
                CustomIntent.customType(FinalResultActivity.this,"fadein-to-fadeout");
                backSound();
            }
        });


        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent restart = new Intent(FinalResultActivity.this,PuzzleActivity.class);
                startActivity(restart);
                CustomIntent.customType(FinalResultActivity.this,"fadein-to-fadeout");
                playSound();
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
