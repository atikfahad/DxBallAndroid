package com.atikfahad.dxball.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Brick extends Drawable {
    Paint brickPaint;
    static int colorChange = 0;
    Rect brick = new Rect();
    public Brick(){
        brickPaint = new Paint();
        brickPaint.setColor(setColorChange(colorChange++));
        brickPaint.setStyle(Paint.Style.FILL);
        brick = new Rect();
    }
    public int setColorChange(int colorChange){
        if(colorChange % 2 == 1)
            return Color.MAGENTA;
        else
            return Color.BLUE;
    }
    @Override
    public void draw(Canvas canvas) {

    }
    public void drawBrick(Canvas canvas, int left, int top, int right, int bottom){
        brick.left = left;
        brick.top = top;
        brick.right = right;
        brick.bottom = bottom;
        canvas.drawRect(brick, brickPaint);
    }
    public Rect getBrick(){
        return this.brick;
    }
    public Paint getBrickPaint(){
        return this.brickPaint;
    }
//    public static List collectionOfBricks(Canvas canvas){
//            int calculate = canvas.getWidth();
//
//        return
//    }
}
