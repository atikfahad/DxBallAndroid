package com.atikfahad.dxball.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Fahad on 3/5/18.
 */

public class Bar extends Drawable {
    Paint barPaint;
    public Bar(){
        barPaint = new Paint();
        barPaint.setColor(Color.RED);
        barPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {

    }
    public void drawBar(Canvas canvas, int left, int right){

        //canvas.drawRect(length, height, length, height, barPaint);
        canvas.drawRect(left,canvas.getHeight() - 15, right, canvas.getHeight(), barPaint);
        //canvas.drawRGB(255,255,255);
    }
    public void changePositionBar(int x, int y){

    }
}
