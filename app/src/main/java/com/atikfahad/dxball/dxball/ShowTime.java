package com.atikfahad.dxball.dxball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class ShowTime extends View {
    private int cellHeight;
    private int cellWeight;
    private boolean isFirst = true;
    Ball ball;
    Bar bar;
    List<Brick> bricks;
    Context context;
    private int howMany = 0;
    private float extraSpace;
    Paint boundary;
    @Override
    protected void onDraw(Canvas canvas) {
        if(isFirst){
            int calculate = canvas.getWidth();
            howMany = calculate/40;
            extraSpace = calculate % 40;
            //extraSpace = 50;
            Log.d("ExtraSpace", Float.toString(howMany));
            Log.d("ExtraSpace", Float.toString(extraSpace));
            cellHeight = canvas.getHeight();
            cellWeight = canvas.getWidth();
            isFirst = false;
        }
        canvas.drawRGB(255,255,255);
        canvas.drawRect(0,0, extraSpace/2, cellHeight, boundary);
        canvas.drawRect(cellWeight - extraSpace / 2,0, cellWeight, cellHeight, boundary);

        ball.drawBall(cellWeight/2,cellHeight/2, canvas);
        bar.drawBar(canvas, 300, 395);
        //brick.drawBrick(canvas);
        invalidate();
    }

    public ShowTime(Context context) {
        super(context);
        ball = new Ball();
        bar = new Bar();
        boundary = new Paint();
        boundary.setColor(Color.GREEN);
        boundary.setStyle(Paint.Style.FILL);
        //brick = new Brick();
    }
}
