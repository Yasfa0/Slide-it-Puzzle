package com.example.pkk_sip;

import android.widget.ImageView;

public class Bearblock {
    int[] position ;
    ImageView block;
    int[] deltaposition = new int[2];

    public int[] getStartposition() {
        return startposition;
    }

    public void setStartposition() {
        this.startposition = this.position;
    }

    int[] startposition;

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public ImageView getBlock() {
        return block;
    }

    public void setBlock(ImageView block) {
        this.block = block;
    }

    public int[] getDeltaposition() {
        return deltaposition;
    }

    public void initDeltaposition() {
        deltaposition[0] = position[0]*180;
        deltaposition[1] = position[1]*180;
    }







}
