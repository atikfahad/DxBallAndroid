package com.atikfahad.dxball.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;

public class Brick extends Drawable {
    Paint brickPaint = new Paint();
    private int brickPoint = 0;
    private int CATEGORY = 1;
    Rect brick = new Rect();
    public Brick(){
        brickPoint = 10;
        brick = new Rect();
        brickPaint.setShader(new LinearGradient(0, 0, 0, 500, Color.parseColor("#01579B"), Color.parseColor("#4FC3F7"), Shader.TileMode.MIRROR));
        brickPaint.setStyle(Paint.Style.FILL);
    }
    @Override
    public void draw(Canvas canvas) {

    }
    public void setBrickPaint(String colorFirst, String colorSecond){
        brickPaint.setShader(new LinearGradient(0, 0, 0, 500, Color.parseColor(colorFirst), Color.parseColor(colorSecond), Shader.TileMode.MIRROR));
        brickPaint.setStyle(Paint.Style.FILL);
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

    public int getBrickPoint(){
        return this.brickPoint;
    }

    public void setBrickPoint(int brickPoint){
        this.brickPoint = brickPoint;
    }
    public void setCategory(int category){
        this.CATEGORY = category;
    }
    public int getCATEGORY(){
        return this.CATEGORY;
    }
}
