package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import maes.tech.intentanim.CustomIntent;

public class ChooseImageActivity extends AppCompatActivity{

    ImageView back,next;
    MediaPlayer voice;
    SharedPreferences pref;

    private static int RESULT_LOAD_IMAGE = 1;
    final int PIC_CROP = 2;
    int i = 0;
    int layout_p;
    int layout_t;
    int block_p;
    int block_t;
    int p = 0;
    int t = 0;
    Bitmap sentBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);

        back = (ImageView) findViewById(R.id.back);
        next = (ImageView) findViewById(R.id.next_img);


        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(ChooseImageActivity.this,rowcolumninput.class);
                startActivity(back);
                backSound();
                CustomIntent.customType(ChooseImageActivity.this,"fadein-to-fadeout");
            }
        });

        p = Integer.parseInt(getIntent().getStringExtra("p"));
        t = Integer.parseInt(getIntent().getStringExtra("l"));;
        if(p>t){
            layout_p = 250;
            layout_t = 250/p*t;
        }else if(p<t){
            layout_t = 250;
            layout_p = 250/t*p;
        }else{
            layout_t = 250;
            layout_p = 250;
        }
        block_p = layout_p/p;
        block_t = layout_t/t;

        final Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(ChooseImageActivity.this,Preview.class);
                next.putExtra("Gambar",sentBitmap);
                next.putExtra("p",String.valueOf(p));
                next.putExtra("l",String.valueOf(t));
                startActivity(next);
                playSound();
                CustomIntent.customType(ChooseImageActivity.this,"fadein-to-fadeout");
            }
        });

        ImageView btn = findViewById(R.id.image);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivityForResult(photoPickerIntent,RESULT_LOAD_IMAGE);
            }
        });

    }

    public void playSound(){

        if (pref.getString("soundSetting",null).equalsIgnoreCase("ON")){
            voice = MediaPlayer.create(this,R.raw.tone);
            voice.start();
            voice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    voice.stop();
                    voice.release();
                }
            });
        }else{

        }
    }

    public void backSound(){

        if (pref.getString("soundSetting",null).equalsIgnoreCase("ON")){
            voice = MediaPlayer.create(this,R.raw.computer_error);
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

    private void performCrop(Uri picUri) {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            // set crop properties here
            cropIntent.putExtra("crop", true);
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", p);
            cropIntent.putExtra("aspectY", t);
            // indicate output X and Y
            cropIntent.putExtra("outputX", layout_p);
            cropIntent.putExtra("outputY", layout_t);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);

        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        ImageView image = findViewById(R.id.image);

        if (resultCode == RESULT_OK&&i == 0) {
            try {

                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                i = 1;
                performCrop(imageUri);
//                image.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            if (data != null &&!data.equals("")) {
                System.out.println("theres data");
                // get the returned data
                Bundle extras = data.getExtras();
                // get the cropped bitmap
                Bitmap selectedBitmap = extras.getParcelable("data");
                image.setImageBitmap(selectedBitmap);

                sentBitmap = selectedBitmap;

                this.i = 0;
            }
        }
    }


}
