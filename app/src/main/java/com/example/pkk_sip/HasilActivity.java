package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);


        input = findViewById(R.id.input);
        ok = (Button) findViewById(R.id.button);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hasil = new Intent(HasilActivity.this,FinalResultActivity.class);
                String nama = input.getText().toString();
                hasil.putExtra("nama",nama);
                startActivity(hasil);
                CustomIntent.customType(HasilActivity.this,"fadein-to-fadeout");
                playSound();
            }
        });

    }

    public void playSound(){

        voice = MediaPlayer.create(this,R.raw.tone);

        voice.start();

    }

    public void backSound(){

        voice = MediaPlayer.create(this,R.raw.computer_error);
        voice.start();

    }
}
