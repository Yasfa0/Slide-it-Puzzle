package com.example.pkk_sip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;


public class FinalResultActivity extends AppCompatActivity {

    Bundle hasil;
    DatabaseReference db;
    TextView nama, layout_time, layout_size, layout_score, text_rank,text_load;
    ImageButton restart, menu;
    ImageView medal;
    MediaPlayer voice;
    ConstraintLayout constraintResult;
    SharedPreferences pref;
    ArrayList<Player> blist = new ArrayList<>();
    ArrayList<Player> flippedblist = new ArrayList<>();
    boolean status_db;

    String nameValue, timeValue, sizeValue, scoreValue;

    UjangEffect ujang = new UjangEffect();

    public void initdb() {
        db.orderByChild("skor").limitToLast(50).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                blist.clear();
                for (DataSnapshot bearss : dataSnapshot.getChildren()) {
                    Player bear = bearss.getValue(Player.class);
                    blist.add(bear);
                }

                for (int i = blist.size() - 1; i >= 0; i--) {
                    flippedblist.add(blist.get(i));
                }

                status_db = true;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        Runnable start = new Runnable() {
            @Override
            public void run() {
                //Hilangkan Disini
                constraintResult = findViewById(R.id.constraintResult);
                text_load = findViewById(R.id.text_load);
                text_load.setVisibility(View.INVISIBLE);
                constraintResult.setVisibility(View.VISIBLE);
                AdView adView = findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);

                medal = findViewById(R.id.medal);
                text_rank = findViewById(R.id.yourScore);
                restart = findViewById(R.id.restart);
                menu = findViewById(R.id.menu);
                nama = findViewById(R.id.name);
                layout_time = findViewById(R.id.time);
                layout_size = findViewById(R.id.size);
                layout_score = findViewById(R.id.scoreValue);
                hasil = getIntent().getExtras();
                nameValue = hasil.getString("nama");
                timeValue = hasil.getString("waktu");
                sizeValue = hasil.getString("ukuran");
                scoreValue = hasil.getString("score");
                if (hasil != null && isNetworkConnected()) {
                    for (int i = 0; i < 10; i++) {
                        if (Integer.parseInt(scoreValue) >= flippedblist.get(i).getSkor()) {
                            System.out.println("Selamat");
                            //Kalo Skornya Masuk
                            // i+1 adalah posisinya

                            text_rank.setText("Hore!\n Kamu Peringkat " + (i+1)  );

                            if (i+1 == 3){
                                medal.setVisibility(View.VISIBLE);
                                medal.setBackgroundResource(R.drawable.medal_bronze);
                            }else if (i+1 == 2){
                                medal.setVisibility(View.VISIBLE);
                                medal.setBackgroundResource(R.drawable.medal_silver);
                            }else if (i+1 == 1){
                                medal.setVisibility(View.VISIBLE);
                                medal.setBackgroundResource(R.drawable.medal_gold);
                            }

                            break;
                        } else {
                            System.out.println("Skor Anda Kurang");
                        }
                    }

                    db.child("unique");
                    DatabaseReference mFiller = db.child("Unique");
                    String id = db.push().getKey();
                    Player baru = new Player(nameValue, Long.parseLong(scoreValue), timeValue, sizeValue, id);
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
                        ujang.clickAnim(menu);
                        Runnable run = new Runnable() {
                            @Override
                            public void run() {
                                Intent keMenu = new Intent(FinalResultActivity.this, MainActivity.class);
                                startActivity(keMenu);
                                CustomIntent.customType(FinalResultActivity.this, "fadein-to-fadeout");
                                backSound();
                            }
                        };
                        Handler timer = new Handler();
                        timer.postDelayed(run, 300);
                    }
                });

                restart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ujang.clickAnim(restart);
                        Runnable run = new Runnable() {
                            @Override
                            public void run() {
                                onBackPressed();
                            }
                        };

                        Handler timer = new Handler();
                        timer.postDelayed(run, 300);
                    }
                });
            }
        };
        Handler hand = new Handler();
        if(isNetworkConnected()){
            db = FirebaseDatabase.getInstance().getReference("childScore");
            blist = new ArrayList<Player>();
            flippedblist = new ArrayList<Player>();
            initdb();
            hand.postDelayed(start, 10000);
        }else{
            hand.postDelayed(start, 1000);
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
