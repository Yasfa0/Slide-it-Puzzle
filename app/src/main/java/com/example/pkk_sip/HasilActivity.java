package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import maes.tech.intentanim.CustomIntent;

public class HasilActivity extends AppCompatActivity {

    EditText input;
    Button ok;
    MediaPlayer voice;
    SharedPreferences pref;

    String waktu, score, ukuran;

    Bundle hasilAkhir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        hasilAkhir = getIntent().getExtras();

        if (hasilAkhir != null) {
            waktu = hasilAkhir.getString("waktu");
            ukuran = hasilAkhir.getString("ukuran");
            score = hasilAkhir.getString("score");
        } else {
            waktu = "??:??";
            ukuran = "???";
            score = "???";
        }

        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        input = findViewById(R.id.input);
        ok = (Button) findViewById(R.id.button);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hasil = new Intent(HasilActivity.this, FinalResultActivity.class);
                String nama = input.getText().toString();
                hasil.putExtra("nama", nama);
                hasil.putExtra("waktu", waktu);
                hasil.putExtra("ukuran", ukuran);
                hasil.putExtra("score", score);
                startActivity(hasil);
                CustomIntent.customType(HasilActivity.this, "fadein-to-fadeout");
                playSound();
            }
        });

    }

    public void playSound() {

        if (pref.getString("soundSetting", null).equalsIgnoreCase("ON")) {
            voice = MediaPlayer.create(this, R.raw.tone);
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
            voice = MediaPlayer.create(this, R.raw.computer_error);
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
