package com.example.pkk_sip;

import android.media.Image;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;

public class UjangEffect {

    //Ieu keur ImageView
    public void clickAnim(final ImageView ujangImage) {


        new CountDownTimer(300, 10) {
            @Override
            public void onTick(long millisUntilFinished) {

                if (millisUntilFinished > 200) {

                    float i = millisUntilFinished % 100;

                    if (i == 0) {

                    } else {
                        ujangImage.setScaleX((float) 1.1 - i / 1000);
                        ujangImage.setScaleY((float) 1.1 - i / 1000);
                    }
                } else if (millisUntilFinished > 100) {
                    float i = millisUntilFinished % 100;

                    if (i == 0) {

                    } else {
                        ujangImage.setScaleX((float) 0.9 + i / 500);
                        ujangImage.setScaleY((float) 0.9 + i / 500);
                    }
                } else if (millisUntilFinished > 20) {
                    float i = millisUntilFinished % 100;

                    if (i == 0) {

                    } else {
                        ujangImage.setScaleX((float) 1 - i / 1000);
                        ujangImage.setScaleY((float) 1 - i / 1000);
                    }

                } else {
                    ujangImage.setScaleX(1);
                    ujangImage.setScaleY(1);
                }


            }

            @Override
            public void onFinish() {

            }
        }.start();


    }

    //Ieu keur ImageButton
    //Jero na mah keneh kehed
    //Beda na ngan Tipe na hungkul
    public void clickButtonAnim(ImageButton ujangButton) {
        ujangButton.setScaleX((float) 0.85);
        ujangButton.setScaleY((float) 0.85);
    }


}
