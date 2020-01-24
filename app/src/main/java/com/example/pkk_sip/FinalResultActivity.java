package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import maes.tech.intentanim.CustomIntent;


public class FinalResultActivity extends AppCompatActivity {

    Bundle hasil;
    DatabaseReference db;
    TextView nama,layout_time,layout_size,layout_score;
    ImageButton restart, menu;
    MediaPlayer voice;
    SharedPreferences pref;

    String nameValue,timeValue,sizeValue,scoreValue;

    UjangEffect ujang = new UjangEffect();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        db = FirebaseDatabase.getInstance().getReference("childScore");

        restart = findViewById(R.id.restart);
        menu = findViewById(R.id.menu);
        nama = findViewById(R.id.name);
        layout_time = findViewById(R.id.time);
        layout_size = findViewById(R.id.size);
        layout_score = findViewById(R.id.scoreValue);

        hasil = getIntent().getExtras();

        if (hasil != null){
            nameValue = hasil.getString("nama");
            timeValue = hasil.getString("waktu");
            sizeValue = hasil.getString("ukuran");
            scoreValue = hasil.getString("score");

            db.child("unique");
            DatabaseReference mFiller = db.child("Unique");
            String id = db.push().getKey();
            Player baru = new Player(nameValue,Long.parseLong(scoreValue),timeValue,sizeValue,id);
            db.child(id).setValue(baru);

        }

        nama.setText(nameValue);
        layout_time.setText(timeValue);
        layout_size.setText(sizeValue);
        layout_score.setText(scoreValue);

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        nama.setText(nameValue);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ujang.clickButtonAnim(menu);
                Intent keMenu = new Intent(FinalResultActivity.this, MainActivity.class);
                startActivity(keMenu);
                CustomIntent.customType(FinalResultActivity.this, "fadein-to-fadeout");
                backSound();
            }
        });


        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ujang.clickButtonAnim(restart);
//                Intent restart = new Intent(FinalResultActivity.this, PuzzleActivity.class);
//                startActivity(restart);
//                CustomIntent.customType(FinalResultActivity.this, "fadein-to-fadeout");
//                playSound();
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
