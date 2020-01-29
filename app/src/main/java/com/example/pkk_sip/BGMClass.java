package com.example.pkk_sip;

import android.content.Context;
import android.media.MediaPlayer;

public class BGMClass {

    MediaPlayer bgmPlayer;

    public void startBGM(Context AppContext){
        bgmPlayer = MediaPlayer.create(AppContext, R.raw.bgm_menu);
        bgmPlayer.setLooping(true);
        bgmPlayer.start();
    }

    public void stopBGM(){
        bgmPlayer.stop();
        bgmPlayer.release();
    }

}
