package com.example.pkk_sip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import maes.tech.intentanim.CustomIntent;

public class RankingActivity extends AppCompatActivity {

    Bundle data;
    ImageView classic, timed, custom, sip, back;
    MediaPlayer voice;
    SharedPreferences pref;
    Button firebase;

    ListView lv;
    List<Player> blist;
    DatabaseReference db;

    UjangEffect ujang = new UjangEffect();

    @Override
    protected void onStart() {
        super.onStart();

        db.orderByValue().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                blist.clear();
                for(DataSnapshot bearss: dataSnapshot.getChildren()){
                    Player bear = bearss.getValue(Player.class);

                    blist.add(bear);
                }

                ArrayAdapter adapter = new BearList(RankingActivity.this,blist);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        db = FirebaseDatabase.getInstance().getReference("childScore");
        lv = findViewById(R.id.listView);
        blist = new ArrayList<Player>();

        sip = findViewById(R.id.logo);
        back = findViewById(R.id.back);

        data = getIntent().getExtras();

//        firebase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                mChildScore.child("unique");
//
////                DatabaseReference mFiller = mChildScore.child("Unique");
//                String id = db.push().getKey();
//                Player baru = new Player("Ujang","2000","56:23","4x4",id);
//                db.child(id).setValue(baru);
//            }
//        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ujang.clickAnim(back);
                Runnable run = new Runnable() {
                    @Override
                    public void run() {

                        Intent back = new Intent(RankingActivity.this, MainActivity.class);
                        startActivity(back);
                        CustomIntent.customType(RankingActivity.this, "fadein-to-fadeout");
                        backSound();

                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run, 300);

            }
        });
        if (getIntent().getExtras() != null) {

            Bundle backData;
            backData = getIntent().getExtras();
            int backSound = backData.getInt("sound");
            //soundSet = backSound;

        } else {

            //soundSet = 1;

        }


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
