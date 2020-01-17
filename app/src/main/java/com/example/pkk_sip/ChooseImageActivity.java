package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import maes.tech.intentanim.CustomIntent;

public class ChooseImageActivity extends AppCompatActivity {

    ImageView back, next;
    MediaPlayer voice;
    SharedPreferences pref;
    ArrayList<Uri> data_Gambar = new ArrayList<Uri>();

    Bundle dataBundle;
    int waktu;
    boolean noImage;

    UjangEffect ujang = new UjangEffect();

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

        initgambar();

        back = (ImageView) findViewById(R.id.back);
        next = (ImageView) findViewById(R.id.next_img);

        dataBundle = getIntent().getExtras();

        if (dataBundle != null) {
            waktu = dataBundle.getInt("waktu");
            noImage = dataBundle.getBoolean("noImage");
        } else {
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

                        Intent back = new Intent(ChooseImageActivity.this, rowcolumninput.class);
                        back.putExtra("waktu", waktu);
                        startActivity(back);
                        backSound();
                        CustomIntent.customType(ChooseImageActivity.this, "fadein-to-fadeout");
                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run, 300);

            }
        });

        p = Integer.parseInt(getIntent().getStringExtra("p"));
        t = Integer.parseInt(getIntent().getStringExtra("l"));
        if (p > t) {
            layout_p = 250;
            layout_t = 250 / p * t;
        } else if (p < t) {
            layout_t = 250;
            layout_p = 250 / t * p;
        } else {
            layout_t = 250;
            layout_p = 250;
        }
        block_p = layout_p / p;
        block_t = layout_t / t;

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ujang.clickAnim(next);
                Runnable run = new Runnable() {
                    @Override
                    public void run() {

                        Intent next = new Intent(ChooseImageActivity.this, Preview.class);
                        next.putExtra("Gambar", sentBitmap);
                        p = Integer.parseInt(getIntent().getStringExtra("p"));
                        t = Integer.parseInt(getIntent().getStringExtra("l"));
                        next.putExtra("p", String.valueOf(p));
                        next.putExtra("l", String.valueOf(t));
                        next.putExtra("waktu", waktu);
                        startActivity(next);
                        playSound();
                        CustomIntent.customType(ChooseImageActivity.this, "fadein-to-fadeout");

                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run, 300);

            }
        });


    }

    public void Pilih(View v) {
        final Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMAGE);
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

    public void initgambar() {
        Uri data1 = Uri.parse("android.resource://com.example.pkk_sip/drawable/def_img1");
        Uri data2 = Uri.parse("android.resource://com.example.pkk_sip/drawable/def_img2");
        Uri data3 = Uri.parse("android.resource://com.example.pkk_sip/drawable/def_img3");
        Uri data4 = Uri.parse("android.resource://com.example.pkk_sip/drawable/def_img4");
        Uri data5 = Uri.parse("android.resource://com.example.pkk_sip/drawable-v24/def_img5.png");
        Uri data6 = Uri.parse("android.resource://com.example.pkk_sip/drawable-v24/def_img6.png");
        Uri data7 = Uri.parse("android.resource://com.example.pkk_sip/drawable-v24/def_img7.png");
        Uri data8 = Uri.parse("android.resource://com.example.pkk_sip/drawable-v24/def_img8.png");
        Uri data9 = Uri.parse("android.resource://com.example.pkk_sip/drawable-v24/def_img9.png");
        Uri data10 = Uri.parse("android.resource://com.example.pkk_sip/drawable-v24/def_img10.png");
        Uri data11 = Uri.parse("android.resource://com.example.pkk_sip/drawable-v24/def_img11.png");
        data_Gambar.add(data1);
        data_Gambar.add(data2);
        data_Gambar.add(data3);
        data_Gambar.add(data4);
        data_Gambar.add(data5);
        data_Gambar.add(data6);
        data_Gambar.add(data7);
        data_Gambar.add(data8);
        data_Gambar.add(data9);
        data_Gambar.add(data10);
        data_Gambar.add(data11);

    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        ImageView image = findViewById(R.id.image);

        if (resultCode == RESULT_OK && i == 0) {
            boolean status_gambar = false;
            boolean bun = dataBundle.getBoolean("status_gambar");
            if(bun){
                status_gambar = true;
            }

            if (status_gambar) {
                int random = new Random().nextInt(4);
                Uri gambar = data_Gambar.get(random);
                performCrop(gambar);

            } else {


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
            }
        } else {
            if (data != null && !data.equals("")) {
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
