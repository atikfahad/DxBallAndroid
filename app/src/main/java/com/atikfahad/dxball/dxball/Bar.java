package com.atikfahad.dxball.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class Bar extends Drawable {
    Paint barPaint;
    private Rect barPlace;
    public Bar(){
        barPaint = new Paint();
        barPaint.setColor(Color.RED);
        barPaint.setStyle(Paint.Style.FILL);
        barPlace = new Rect();
    }
    public Rect getBarPlace(){
        return this.barPlace;
    }
    @Override
    public void draw(Canvas canvas) {

    }
    public void drawBar(Canvas canvas, int left, int right){
        barPlace.left = left;
        barPlace.right = right;
        barPlace.top = canvas.getHeight() - 30;
        barPlace.bottom = canvas.getHeight();
        canvas.drawRect(barPlace, barPaint);
        //canvas.drawRect(left,canvas.getHeight() - 15, right, canvas.getHeight(), barPaint);
        //canvas.drawRGB(255,255,255);
    }
    public void changePositionBar(int x, int y){

    }
}
