package com.example.pkk_sip;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

public class BGMClass {
    static MediaPlayer bgmPlayer;

    public static void startBGM(Context AppContext){
        bgmPlayer = MediaPlayer.create(AppContext, R.raw.bgm_menu);
        bgmPlayer.setLooping(true);
        bgmPlayer.start();
    }

    public static void stopBGM(){
        bgmPlayer.stop();
        bgmPlayer.release();
    }

}
