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
    private int cellWidth;
    private boolean isFirst = true;
    Ball ball;
    Bar bar;
    List<Brick> bricks;
    Context context;
    private int howMany = 0;
    private float extraSpace;
    private int runFor;
    Paint boundary;
    @Override
    protected void onDraw(Canvas canvas) {
        if(isFirst){
            int calculate = canvas.getWidth();
            howMany = calculate/40;
            extraSpace = calculate % 40;
            //extraSpace = 50;
            Log.d("howMany", Float.toString(howMany));
            Log.d("ExtraSpace", Float.toString(extraSpace));
            cellHeight = canvas.getHeight();
            cellWidth = canvas.getWidth();
            isFirst = false;
            runFor = 10;
            bricks = new ArrayList<Brick>();
            for(int i = howMany * 10; i > 0; i--){
                bricks.add(new Brick());
                Log.d("Bricks Creation", Integer.toString(i));
            }
        }
        canvas.drawRGB(255,255,255);
        canvas.drawRect(0,0, extraSpace/2, cellHeight, boundary);
        canvas.drawRect(cellWidth - extraSpace / 2,0, cellWidth, cellHeight, boundary);

        ball.drawBall(cellWidth/2,cellHeight/2, canvas);
        bar.drawBar(canvas, 300, 395);
        int initialX = 0, initialY = 0;
        for (Brick brick:
             bricks) {
            brick.drawBrick(canvas,initialX, initialY, initialX + 40, initialY + 40 );
            initialX = initialX + 40;
            if (initialX == cellWidth){
                initialX = 0;
                //if(runFor > 0)
                    initialY = initialY + 40;
                //runFor--;
            }
        }
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
