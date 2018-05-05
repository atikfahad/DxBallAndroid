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
    private static boolean changeColor = true;
    Rect brick = new Rect();
    public Brick(){
        brickPoint = 10;
        brick = new Rect();
        brickPaint.setStyle(Paint.Style.FILL);
        int shaderColor0 = Color.RED;
        int shaderColor1 = Color.WHITE;
        int shaderColor2 = Color.WHITE;
        int shaderColor3 = Color.DKGRAY;
        brickPaint.setAntiAlias(true);
        Shader linearGradientShader;
        if(changeColor) {
            linearGradientShader = new LinearGradient(
                    3, 3, 5, 5,
                    shaderColor1, shaderColor0, Shader.TileMode.MIRROR);
            changeColor = !changeColor;
        }
        else {
            linearGradientShader = new LinearGradient(
                    3, 3, 7, 7,
                    shaderColor2, shaderColor3, Shader.TileMode.MIRROR);
            changeColor = !changeColor;
        }

        brickPaint.setShader(linearGradientShader);


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
