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
    public Brick(){
        brickPaint = new Paint();
        brickPaint.setColor(Color.BLUE);
        brickPaint.setStyle(Paint.Style.FILL);
    }
    @Override
    public void draw(Canvas canvas) {

    }
    public void drawBrick(Canvas canvas){
        canvas.drawRect(50,80, 90, 120, brickPaint);
    }
//    public static List collectionOfBricks(Canvas canvas){
//            int calculate = canvas.getWidth();
//
//        return
//    }
}
