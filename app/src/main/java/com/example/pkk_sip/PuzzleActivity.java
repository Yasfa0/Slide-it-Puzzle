package com.example.pkk_sip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import maes.tech.intentanim.CustomIntent;

public class PuzzleActivity extends AppCompatActivity {


    UjangEffect ujang = new UjangEffect();

    boolean noTime;
    String detik_layout, menit_layout;

    Bundle bundle;
    int waktu;
    ImageView no1, no2, no3, start, blur, classic, timed, custom, pause, timeBox;
    Button resume, toMenu, restart;
    CardView card, cardmain;
    TextView timer, skip;
    MediaPlayer voice;
    SharedPreferences pref;

    ArrayList<Bearblock> listblock;
    ImageView leftbtn;
    ImageView rightbtn;
    ImageView upbtn;
    ImageView downbtn;
    ImageView gambar_hint;
    int[] currentpos = new int[]{3, 3};
    int[] leftpos = new int[]{2, 3};
    int[] rightpos = new int[]{4, 3};
    int[] uppos = new int[]{3, 2};
    int[] downpos = new int[]{3, 4};
    int p = 0;
    int t = 0;
    int layout_p;
    int layout_t;
    boolean status_selesai_randomizer = false;

    ArrayList<Bitmap> bitmapArrayList = new ArrayList<Bitmap>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);


        toMenu = findViewById(R.id.menu);
        restart = findViewById(R.id.restart);
        timeBox = findViewById(R.id.timer_show);
        timer = findViewById(R.id.timer);
        cardmain = findViewById(R.id.card);
        custom = findViewById(R.id.custom);
        no1 = (ImageView) findViewById(R.id.no1);
        no2 = (ImageView) findViewById(R.id.no2);
        no3 = (ImageView) findViewById(R.id.no3);
        start = (ImageView) findViewById(R.id.start);
        blur = (ImageView) findViewById(R.id.blur);
        gambar_hint = findViewById(R.id.hint_image);


        pref = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE);

        resume = findViewById(R.id.resume);

        //Tombol Skip hanya untuk keperluan Testing
        skip = findViewById(R.id.skipStage);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result = new Intent(PuzzleActivity.this, HasilActivity.class);
                startActivity(result);
                PuzzleActivity.super.finish();
                CustomIntent.customType(PuzzleActivity.this, "fadein-to-fadeout");
            }
        });

        //Pause Sementara (Nanti ganti sama Fragment)
        pause = findViewById(R.id.pause);
        card = findViewById(R.id.difficulty);

        blur.setVisibility(View.VISIBLE);
        no1.setVisibility(View.INVISIBLE);
        no2.setVisibility(View.INVISIBLE);
        no3.setVisibility(View.INVISIBLE);
        start.setVisibility(View.INVISIBLE);
        pause.setVisibility(View.INVISIBLE);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            waktu = bundle.getInt("waktu");

            if (waktu > 0) {
                noTime = false;
            } else {
                noTime = true;
                waktu = 3959000;
            }

        } else {
            noTime = true;
            waktu = 3959000;
        }


//Section
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ujang.clickAnim(pause);

                Runnable run = new Runnable() {
                    @Override
                    public void run() {

                        card.setVisibility(View.VISIBLE);
                        blur.setVisibility(View.VISIBLE);
                        cardmain.setVisibility(View.INVISIBLE);
                    }
                };

                Handler timer = new Handler();
                timer.postDelayed(run, 300);

            }
        });

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                card.setVisibility(View.INVISIBLE);
                blur.setVisibility(View.INVISIBLE);
                cardmain.setVisibility(View.VISIBLE);
                playSound();
            }
        });
//
        toMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMenu = new Intent(PuzzleActivity.this, MainActivity.class);
                startActivity(toMenu);
                backSound();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent restart = getIntent();
                finish();
                startActivity(restart);
                playSound();
            }
        });

        new CountDownTimer(5000, 1000) {

            public void onTick(long waktu) {
                no1.setVisibility(View.VISIBLE);
                //chimeSound();


                if (waktu < 4000) {
                    no2.setVisibility(View.VISIBLE);
                    //chimeSound();
                }

                if (waktu < 3000) {
                    no3.setVisibility(View.VISIBLE);
                    //chimeSound();
                }

                if (waktu < 2000) {
                    start.setVisibility(View.VISIBLE);
                    //chimeSound();
                }

            }

            public void onFinish() {
                timeBox.setVisibility(View.VISIBLE);
                timer.setVisibility(View.VISIBLE);
                blur.setVisibility(View.INVISIBLE);
                no1.setVisibility(View.INVISIBLE);
                no2.setVisibility(View.INVISIBLE);
                no3.setVisibility(View.INVISIBLE);
                start.setVisibility(View.INVISIBLE);
                cardmain.setVisibility(View.VISIBLE);
                pause.setVisibility(View.VISIBLE);
                //skip.setVisibility(View.VISIBLE);
            }

        }.start();


        final int detik = waktu % 60000 / 1000;
        final int menit = waktu / 60000 / 1000;
        final int menitup = waktu / 60000;

        if (noTime == false) {

            new CountDownTimer(waktu, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long detik_sisa = millisUntilFinished % 60000 / 1000;
                    long menit_sisa = millisUntilFinished / 60000;
                    long detik_show = detik - (detik - detik_sisa);
                    long menit_show = menit - (menit - menit_sisa);
                    detik_layout = "";
                    menit_layout = "";
                    if (detik_show < 10) {
                        detik_layout = String.valueOf("0" + detik_show);
                    } else {
                        detik_layout = String.valueOf(detik_show);
                    }

                    if (menit_show < 10) {
                        menit_layout = String.valueOf("0" + menit_show);
                    } else {
                        menit_layout = String.valueOf(menit_show);
                    }
                    String string_waktu = menit_layout + ":" + detik_layout;
                    timer.setText(string_waktu);
                }

                @Override
                public void onFinish() {

                }
            }.start();

        } else {

            //timer.setText("No Time");
            new CountDownTimer(waktu, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long detik_sisa = millisUntilFinished % 60000 / 1000;
                    long menit_sisa = millisUntilFinished / 60000;
                    long detik_show = detik - detik_sisa;
                    long menit_show = menitup - menit_sisa;
                    detik_layout = "";
                    menit_layout = "";
                    if (detik_show < 10) {
                        detik_layout = String.valueOf("0" + detik_show);
                    } else {
                        detik_layout = String.valueOf(detik_show);
                    }

                    if (menit_show < 10) {
                        menit_layout = String.valueOf("0" + menit_show);
                    } else {
                        menit_layout = String.valueOf(menit_show);
                    }
                    String string_waktu = menit_layout + ":" + detik_layout;
                    timer.setText(string_waktu);

                    if (millisUntilFinished <= 1000) {
                        checkwin();
                    }
                }

                @Override
                public void onFinish() {

                }
            }.start();

        }

        p = Integer.parseInt(getIntent().getStringExtra("p"));
        t = Integer.parseInt(getIntent().getStringExtra("l"));

        int size_t = 250;
        int size_p = 250;

        if (p > t) {
            layout_p = size_p;
            layout_t = size_p / p * t;
        } else if (p < t) {
//            System.out.println("In");
            layout_t = size_t;
            layout_p = size_t / t * p;
//            System.out.println(layout_p);
//            System.out.println(layout_t);
        } else {
            layout_t = size_t;
            layout_p = size_p;
        }
        initPicOOP();
        initBlockOOP(p, t);
        leftbtn = findViewById(R.id.pad_right);
        rightbtn = findViewById(R.id.pad_left);
        upbtn = findViewById(R.id.pad_down);
        downbtn = findViewById(R.id.pad_up);
        Bearmove(p, t);
        initslide();
        randomizer();


        hint((ImageView) findViewById(R.id.hint_image));
        hint((ImageView) findViewById(R.id.hint));
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

    public void chimeSound() {
        if (pref.getString("soundSetting", null).equalsIgnoreCase("ON")) {
            voice = MediaPlayer.create(this, R.raw.electronic_chime);
            voice.start();
            voice.setLooping(false);
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

    private void checkwin() {
        boolean status = false;
        int score = 0;

        if (noTime) {
            score = p * t * 1000;
        } else {
            score = (p + t) / 2 * 1000 * p * t / 2;
            int point_per_second = (p + t) / 2 * 1000 * p * t / 6000 / 2;
            String waktu = timer.getText().toString();
            int sisa_menit = Integer.parseInt(waktu.substring(0, 1));
            int sisa_detik = Integer.parseInt(waktu.substring(3, 4));
            int sisa_waktu = sisa_detik + sisa_menit * 60;
            score = score - point_per_second * sisa_waktu;
        }
        int point_per_block = (p + t) / 2 * 1000;

        for (int i = 0; i < listblock.size(); i++) {
            if (listblock.get(i).getPosition() == listblock.get(i).getStartposition()) {
                status = true;
                score = score + point_per_block;
            } else {
                status = false;

                break;
            }
        }
        if (!noTime && timer.getText().toString().equals("00:00")) {
            status = true;
        }
        if (status == true && status_selesai_randomizer == true) {
            //Nanti kan intent ke Activity Menang
            //Parameternya Waktu,Score
            //Nama mah Masukin nanti pake TextField aja di activity menangnya
            //jangan di activity ini
            //kalo udah itu masukin aja datanya ke database
            //Kalo Butuh Score Tinggal Di Panggil Aja
            //Scorenya mah tinggal pake score yang diatas... langsung aja di panggil
            //kalo waktu kan situ yang ngerjain jadi gk tau
            //System.out.println("Kamu Menang");
            Intent result = new Intent(PuzzleActivity.this, HasilActivity.class);
            String ukuran = p + " X " + t;
            String stringScore = score + " ";
            result.putExtra("ukuran", ukuran);
            result.putExtra("score", stringScore);
            result.putExtra("waktu", menit_layout + ":" + detik_layout);
            startActivity(result);
            PuzzleActivity.super.finish();
            CustomIntent.customType(PuzzleActivity.this, "fadein-to-fadeout");
        }
    }

    private void Bearmove(int p, int t) {
        int block_length = dpToPx(layout_p / p);
        int block_height = dpToPx(layout_t / t);
        if (currentpos[0] == p - 1 && currentpos[1] == t - 1) {
            checkwin();
        }
        if (leftpos[0] >= 0) {
            for (int i = 0; i < p * t - 1; i++) {
                if (leftpos[0] == listblock.get(i).getPosition()[0] && leftpos[1] == listblock.get(i).getPosition()[1]) {
                    Run(listblock.get(i), leftbtn, block_length, 0, "left");
//                    System.out.print("Its Left /n");
                }
            }
        } else {
            EmptyButtonOnClick(leftbtn);
        }
        if (rightpos[0] <= p - 1) {
            for (int i = 0; i < p * t - 1; i++) {
                if (rightpos[0] == listblock.get(i).getPosition()[0] && rightpos[1] == listblock.get(i).getPosition()[1]) {
                    Run(listblock.get(i), rightbtn, -block_length, 0, "right");
//                    System.out.print("Its Right/n");
                }
            }
        } else {
            EmptyButtonOnClick(rightbtn);
        }

        if (uppos[1] >= 0) {
            for (int i = 0; i < p * t - 1; i++) {
                if (uppos[0] == listblock.get(i).getPosition()[0] && uppos[1] == listblock.get(i).getPosition()[1]) {
                    Run(listblock.get(i), upbtn, 0, block_height, "up");
//                    System.out.print("Its Up/n");
                }
            }
        } else {
            EmptyButtonOnClick(upbtn);
        }

        if (downpos[1] <= t - 1) {
            for (int i = 0; i < p * t - 1; i++) {
                if (downpos[0] == listblock.get(i).getPosition()[0] && downpos[1] == listblock.get(i).getPosition()[1]) {
                    Run(listblock.get(i), downbtn, 0, -block_height, "down");
                }
            }
        } else {
            EmptyButtonOnClick(downbtn);
        }

    }

    private void EmptyButtonOnClick(ImageView btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void hint(ImageView img) {
        final ImageView hint = findViewById(R.id.hint_image);
        final ImageView hint_btn = findViewById(R.id.hint);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hint.getVisibility() == View.VISIBLE) {
                    hint.setVisibility(View.INVISIBLE);
                } else if (hint.getVisibility() == View.INVISIBLE) {
                    hint.setVisibility(View.VISIBLE);
                }
                ujang.clickAnim(hint_btn);


            }
        });
    }

    private void randomizer() {
        final int block_length = dpToPx(layout_p / p);
        final int block_height = dpToPx(layout_t / t);
        int temp = 0;
        for (int i = 0; i < (p * t * 18); i++) {

            int random = new Random().nextInt(5);
            for (int a = 5; a < 5; a++) {
                if (temp == random) {
                    random = new Random().nextInt(5);
                } else {
                    temp = random;
                    break;
                }
            }


            if (random == 1) {
                slide(downpos, "down", 0, (-block_height), 1000);
            } else if (random == 2) {
                slide(leftpos, "left", block_length, 0, 1000);
            } else if (random == 3) {
                slide(rightpos, "right", (-block_length), 0, 1000);
            } else if (random == 4) {
                slide(uppos, "up", 0, block_height, 1000);
            }
        }
        status_selesai_randomizer = true;

    }

    private void Run(Bearblock block, ImageView btn, int x, int y, String change) {
        int block_length = dpToPx(layout_p / p);
        int block_height = dpToPx(layout_t / t);
        ImageView img = block.getBlock();
        int[] cur = new int[2];
        cur[0] = (block.getPosition()[0] - block.getStartposition()[0]) * block_length + x;
        cur[1] = (block.getPosition()[1] - block.getStartposition()[1]) * block_height + y;
        int[] next = new int[2];
        next[0] = (block.getPosition()[0] - block.getStartposition()[0]) * block_length;
        next[1] = (block.getPosition()[1] - block.getStartposition()[1]) * block_height;
        initbtn(btn, img, cur, next, block, change);
    }

    private void slide(int[] nextpos, String change, int x, int y, int speed) {
        int block_length = dpToPx(layout_p / p);
        int block_height = dpToPx(layout_t / t);
        if (nextpos[1] < t && nextpos[1] >= 0 && nextpos[0] < p && nextpos[0] >= 0) {
            for (int i = 0; i < p * t - 1; i++) {
                if (nextpos[0] == listblock.get(i).getPosition()[0] && nextpos[1] == listblock.get(i).getPosition()[1]) {
//                    Run(listblock.get(i),downbtn,0,-block_height,"down");
                    final Bearblock block = listblock.get(i);
                    ImageView img = block.getBlock();
                    int[] cur = new int[2];
                    cur[0] = (block.getPosition()[0] - block.getStartposition()[0]) * block_length + x;
                    cur[1] = (block.getPosition()[1] - block.getStartposition()[1]) * block_height + y;
                    int[] next = new int[2];
                    next[0] = (block.getPosition()[0] - block.getStartposition()[0]) * block_length;
                    next[1] = (block.getPosition()[1] - block.getStartposition()[1]) * block_height;
                    Animation animation = new TranslateAnimation(next[0], cur[0], next[1], cur[1]);
                    System.out.println(change);
                    animation.setDuration(speed);
                    animation.setFillAfter(true);
                    img.startAnimation(animation);
                    Change(change, block);
                    if (block.getPosition() == block.getStartposition()) {
                        Runnable owo = new Runnable() {
                            @Override
                            public void run() {
                                ujang.clickAnim(block.getBlock());
                            }
                        };

                        Handler hand = new Handler();
                        hand.postDelayed(owo, 500);
                    } else {

                    }
                    Bearmove(p, t);
                    initslide();
//                    playSound();
                    break;
                }
            }
        } else {

        }
    }

    private void initslide() {
        final int block_length = dpToPx(layout_p / p);
        final int block_height = dpToPx(layout_t / t);
        CardView playarea = findViewById(R.id.card);
        playarea.setOnTouchListener(new OnSwipeTouchListener(this) {

            public void onSwipeTop() {
                slide(downpos, "down", 0, (-block_height), 500);
                if (!noTime && timer.getText().toString().equals("00:00")) {
                    checkwin();
                }
            }

            public void onSwipeRight() {
                slide(leftpos, "left", block_length, 0, 500);
                if (!noTime && timer.getText().toString().equals("00:00")) {
                    checkwin();
                }
            }

            public void onSwipeLeft() {
                slide(rightpos, "right", (-block_length), 0, 500);
                if (!noTime && timer.getText().toString().equals("00:00")) {
                    checkwin();
                }
            }

            public void onSwipeBottom() {
                slide(uppos, "up", 0, block_height, 500);
                if (!noTime && timer.getText().toString().equals("00:00")) {
                    checkwin();
                }
            }

            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private void initBlockOOP(int p, int t) {
        p = p - 1;
        t = t - 1;
        currentpos = new int[]{p, t};
        leftpos = new int[]{p - 1, t};
        rightpos = new int[]{p + 1, t};
        uppos = new int[]{p, t - 1};
        downpos = new int[]{p, t + 1};
        ArrayList<LinearLayout> arrayll = new ArrayList<LinearLayout>();
        int block_length = dpToPx(layout_p / (p + 1));
        int block_height = dpToPx(layout_t / (t + 1));
        FrameLayout fl = findViewById(R.id.frame);
//        FrameLayout fl = null;
        listblock = new ArrayList<Bearblock>();
        for (int i = 0; i <= t; i++) {
            LinearLayout ll = new LinearLayout(this);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                    dpToPx(layout_p), dpToPx(layout_t)
            );

            lp.setLayoutDirection(LinearLayout.HORIZONTAL);
            arrayll.add(ll);
            for (int ii = 0; ii <= p; ii++) {
                if (listblock.size() != ((p + 1) * (t + 1)) - 1) {
                    ImageView imv = new ImageView(this);
                    imv.setImageBitmap(bitmapArrayList.get((i * 1) + (i * p) + ii));
                    Bearblock block = new Bearblock();
                    block.setPosition(new int[]{ii % (p + 1), i % (t + 1)});
                    block.initDeltaposition();
                    block.setBlock(imv);
                    block.setStartposition();
                    LinearLayout.LayoutParams ip = new LinearLayout.LayoutParams(block_length, block_height);
                    ip.topMargin = i * block_height;
                    arrayll.get(i).addView(imv, ip);
                    listblock.add(block);

                }
            }

            fl.addView(arrayll.get(i), lp);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    dpToPx(layout_p), dpToPx(layout_t));
            params.gravity = Gravity.CENTER;
            fl.setLayoutParams(params);
        }
    }

    private void initbtn(ImageView btn, final ImageView img, final int[] pos, final int[] next, final Bearblock block, final String change) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = new TranslateAnimation(next[0], pos[0], next[1], pos[1]);
//                System.out.println((pos[0])+","+(pos[1]));
                System.out.println(change);
                animation.setDuration(500);
                animation.setFillAfter(true);
                img.startAnimation(animation);
                Change(change, block);
                Bearmove(p, t);
                playSound();
            }
        });
    }

    private void Change(String change, Bearblock block) {
        if (change.equals("left")) {
            int[] blockpos = block.getPosition();
            block.setPosition(currentpos);
            currentpos = blockpos;
            rightpos[0] = rightpos[0] - 1;
            leftpos[0] = leftpos[0] - 1;
            uppos[0] = uppos[0] - 1;
            downpos[0] = downpos[0] - 1;
        } else if (change.equals("right")) {
            int[] blockpos = block.getPosition();
            block.setPosition(currentpos);
            currentpos = blockpos;
            rightpos[0] = rightpos[0] + 1;
            leftpos[0] = leftpos[0] + 1;
            uppos[0] = uppos[0] + 1;
            downpos[0] = downpos[0] + 1;
        } else if (change.equals("up")) {
            int[] blockpos = block.getPosition();
            block.setPosition(currentpos);
            currentpos = blockpos;
            rightpos[1] = rightpos[1] - 1;
            leftpos[1] = leftpos[1] - 1;
            uppos[1] = uppos[1] - 1;
            downpos[1] = downpos[1] - 1;
        } else if (change.equals("down")) {
            int[] blockpos = block.getPosition();
            block.setPosition(currentpos);
            currentpos = blockpos;
            rightpos[1] = rightpos[1] + 1;
            leftpos[1] = leftpos[1] + 1;
            uppos[1] = uppos[1] + 1;
            downpos[1] = downpos[1] + 1;
        }
    }

    private void initPicOOP() {
        Bitmap temp = getIntent().getParcelableExtra("Gambar");
        gambar_hint.setImageBitmap(temp);
        for (int i = 0; i < t; i++) {
            for (int a = 0; a < p; a++) {
                bitmapArrayList.add(crop(temp, a % p, i % t));
            }
        }
    }

    private Bitmap crop(Bitmap original, int pos_p, int pos_t) {
        int height = layout_t / t;
        int width = layout_p / p;
//        System.out.println("Lebar : "+width);
//        System.out.println("Pos P : "+pos_p);
//        System.out.println("Tinggi : "+height);
//        System.out.println("Pos T : "+pos_t);
        Bitmap bMap = Bitmap.createBitmap(original, width * pos_p, height * pos_t,
                width, height, new Matrix(), true);

        return bMap;
    }

}
