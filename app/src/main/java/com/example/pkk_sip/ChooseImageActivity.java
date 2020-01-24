package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import maes.tech.intentanim.CustomIntent;

public class ChooseImageActivity extends AppCompatActivity {

    ImageView back, next;
    MediaPlayer voice;
    SharedPreferences pref;
    ArrayList<Bitmap> data_Gambar = new ArrayList<Bitmap>();

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
    boolean bun = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBundle = getIntent().getExtras();
        setContentView(R.layout.activity_choose_image);
        bun = dataBundle.getBoolean("status_gambar");
        ImageView imgpilih = findViewById(R.id.image);


        back = (ImageView) findViewById(R.id.back);
        next = (ImageView) findViewById(R.id.next_img);


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

        if (bun) {
            initgambar();
            EmptyButtonOnClick(imgpilih);
        } else {
            imgpilih.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pilih(v);
                }
            });
        }

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
        Bitmap data1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.def_img1);
        Bitmap data2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.def_img2);
        Bitmap data3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.def_img3);
        Bitmap data4 = BitmapFactory.decodeResource(this.getResources(), R.drawable.def_img4);
        Bitmap data5 = BitmapFactory.decodeResource(this.getResources(), R.drawable.def_img5);
        Bitmap data6 = BitmapFactory.decodeResource(this.getResources(), R.drawable.def_img6);
        Bitmap data7 = BitmapFactory.decodeResource(this.getResources(), R.drawable.def_img7);
        Bitmap data8 = BitmapFactory.decodeResource(this.getResources(), R.drawable.def_img8);
        Bitmap data9 = BitmapFactory.decodeResource(this.getResources(), R.drawable.def_img9);
        Bitmap data10 = BitmapFactory.decodeResource(this.getResources(), R.drawable.def_img10);
        Bitmap data11 = BitmapFactory.decodeResource(this.getResources(), R.drawable.def_img11);
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

        int random = new Random().nextInt(11);
        Bitmap src = data_Gambar.get(random);

        if (p > t) {
            int height = 250 / p * t;
            src = scaleCenterCrop(src, height, 250);
        } else if (p < t) {
            int width = 250 / t * p;
            src = scaleCenterCrop(src, 250, width);
        } else {
            src = scaleCenterCrop(src, 250, 250);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        src.compress(Bitmap.CompressFormat.PNG, 100, out);
        src = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
        sentBitmap = src;

        ((ImageView) findViewById(R.id.image)).setImageBitmap(src);


    }


    public Bitmap scaleCenterCrop(Bitmap source, int newHeight, int newWidth) {
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();

        float xScale = (float) newWidth / sourceWidth;
        float yScale = (float) newHeight / sourceHeight;
        float scale = Math.max(xScale, yScale);

        // Now get the size of the source bitmap when scaled
        float scaledWidth = scale * sourceWidth;
        float scaledHeight = scale * sourceHeight;

        float left = (newWidth - scaledWidth) / 2;
        float top = (newHeight - scaledHeight) / 2;

        RectF targetRect = new RectF(left, top, left + scaledWidth, top
                + scaledHeight);//from ww w  .j a va 2s. co m

        Bitmap dest = Bitmap.createBitmap(newWidth, newHeight,
                source.getConfig());
        Canvas canvas = new Canvas(dest);
        canvas.drawBitmap(source, null, targetRect, null);

        return dest;
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        ImageView image = findViewById(R.id.image);

        if (resultCode == RESULT_OK && i == 0) {
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
            } else {
                i = 0;
                final Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMAGE);
            }
        }

    }

    private void EmptyButtonOnClick(ImageView btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
