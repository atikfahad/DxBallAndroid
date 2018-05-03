package com.atikfahad.dxball.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

/**
 * Created by Fahad on 3/5/18.
 */

public class Brick extends Drawable {
    Paint brickPaint;
    static int colorChange = 0;
    public Brick(){
        brickPaint = new Paint();
        brickPaint.setColor(setColorChange(colorChange++));
        brickPaint.setStyle(Paint.Style.FILL);
    }
    public int setColorChange(int colorChange){
        if(colorChange % 2 == 0)
            return Color.MAGENTA;
        else if(colorChange % 2 == 1)
            return Color.BLACK;
        else if(colorChange % 2 == 2)
            return Color.GRAY;
        else
            return Color.BLUE;
    }
    @Override
    public void draw(Canvas canvas) {

    }
    public void drawBrick(Canvas canvas, float l, float t, float r, float b){
        canvas.drawRect(l,t, r, b, brickPaint);
    }
//    public static List collectionOfBricks(Canvas canvas){
//            int calculate = canvas.getWidth();
//
//        return
//    }
}
