package com.atikfahad.dxball.dxball;


import android.graphics.Canvas;
import android.graphics.Rect;

public class BrickSecond extends Brick {
    private boolean isFirst = true;
    public BrickSecond() {
        super();
        this.setBrickPoint(15);
        this.setBrickPaint("#424242", "#616161");
        this.setCategory(2);
    }
    public boolean executeIt(){
        if(isFirst){
            this.setBrickPaint("#E0E0E0", "#BDBDBD");
            isFirst = false;
            return false;
        }
        else
            return true;
    }
}
