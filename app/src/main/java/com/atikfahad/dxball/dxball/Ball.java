package com.atikfahad.dxball.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class Ball extends Drawable {
    private int radius = 20;
    private float dx, dy;
    private Paint ballPaint;
    Drawable drawable;
    private int ballPositionX, ballPositionY;
    public void colidesWith(Drawable drawable){
        this.drawable = drawable;
        this.setx(5);
        this.sety(5);
    }
    public Ball() {

    }
    public int getRadius(){
        return this.radius;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRGB(255,255,255);
    }
    public void drawBall(int x, int y, Canvas canvas){
        ballPositionX = x;
        ballPositionY = y;
        ballPaint = new Paint();
        ballPaint.setColor(Color.RED);
        ballPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(ballPositionX, ballPositionY,radius, ballPaint);
        //canvas.drawRGB(255,255,255);
    }
    public boolean collisionWithBar(Rect bar){
        if(bar == null)
            return false;
//        if(ballPositionY + this.getRadius() >= bar.top &&
//                ballPositionX + this.getRadius() >=bar.left &&
//                ballPositionY - this.getRadius() <= bar.right &&
//                ballPositionY + this.getRadius() <= bar.bottom)
        if(bar.contains(ballPositionX + this.getRadius(), ballPositionY + this.getRadius()))
//        if(ballPositionY + this.getRadius() >= bar.top && ballPositionY + this.getRadius() <= bar.bottom
//                && ballPositionX >= bar.left && ballPositionX <= bar.right)
            return true;
        else
            return false;
    }
    public boolean collisionWithBrick(Rect brick){
        if(brick.contains((int)ballPositionX + (int)this.getRadius(), (int)ballPositionY + (int)this.getRadius()))
            return true;
        else
            return false;
    }
}
