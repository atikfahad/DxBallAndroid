package com.atikfahad.dxball.dxball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Fahad on 3/5/18.
 */

public class Ball extends Drawable {
    private float radius = 20;
    private float dx, dy;
    private Paint ballPaint;
    Drawable drawable;
    public void colidesWith(Drawable drawable){
        this.drawable = drawable;
        this.setx(5);
        this.sety(5);
    }
    public Ball() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRGB(255,255,255);
    }
    public void drawBall(float x, float y, Canvas canvas){
        ballPaint = new Paint();
        ballPaint.setColor(Color.RED);
        ballPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y,radius, ballPaint);
        //canvas.drawRGB(255,255,255);
    }
}
