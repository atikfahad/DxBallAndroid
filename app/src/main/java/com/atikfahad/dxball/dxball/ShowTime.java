package com.atikfahad.dxball.dxball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.*;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class ShowTime extends View{
 //   Bitmap lifeIcon;
//    Paint lifeIconPaint;
    //private Drawable iconLife;
    android.graphics.drawable.Drawable iconLife = getResources().getDrawable(R.drawable.ic_heart_test);
    private int cellHeight;
    private int cellWidth;
    private boolean isFirst = true, isFirstPosition = true;
    private String TAG = "DRAG EVENT";
    private int dx = 7,dy = 7;
    Ball ball;
    Bar bar;
    private int primaryPostion;
    List<Brick> bricks;
    private int howMany = 0;
    private float extraSpace;
    private float previousPosition, presentPosition;
    private Rect barPlace;
    private float flow = 3.5f;
    Paint boundary;
    private int ballPositionX, ballPositionY, ballTotalPositionY;
    private int boundaryLeft, boundaryRight, boundaryTop, boundaryBottom;
    private int totalBricks;
    @Override
    protected void onDraw(Canvas canvas) {
        if(isFirst){
            //canvas.drawBitmap(lifeIcon,0,0, lifeIconPaint );
            //iconLife = getResources().getDrawable(R.mipmap)
            //canvas.setBitmap(lifeIcon);

            int calculate = canvas.getWidth();
            howMany = calculate/40;
            extraSpace = calculate % 40;
            Log.d("howMany", Float.toString(howMany));
            Log.d("ExtraSpace", Float.toString(extraSpace));
            cellHeight = canvas.getHeight();
            cellWidth = canvas.getWidth();
            isFirst = false;
            primaryPostion = cellWidth/2 - 50;
            bar.drawBar(canvas, primaryPostion, primaryPostion + 100);
            bricks = new ArrayList<Brick>();
            totalBricks = howMany * 2;
            for(int i = totalBricks; i > 0; i--){
                bricks.add(new Brick());
                Log.d("Bricks Creation", Integer.toString(i));
            }
            ballPositionX = cellWidth / 2;
            ballPositionY = cellHeight - 100;
            //boundaryLeft =

            int initialX = 0, initialY = 35;
            for (Brick brick:
                    bricks) {
                brick.drawBrick(canvas,initialX, initialY, initialX + 120, initialY + 90 );
                initialX = initialX + 120;
                if (initialX == cellWidth){
                    initialX = 0;
                    initialY = initialY + 90;
                }
            }

        }
        //canvas.drawRGB(255,255,255); // Initial Color for the screen..
        iconLife.setBounds(0,0,canvas.getWidth(), canvas.getHeight());
        iconLife.draw(canvas);
        // Boundary Section
        canvas.drawRect(0,0, extraSpace / 2, cellHeight, boundary);
        canvas.drawRect(cellWidth - extraSpace / 2,0, cellWidth, cellHeight, boundary);

        // Ball Section

        //ballPositionX = ballPositionX + 3;
        ballPositionY = ballPositionY + dy;
        ballPositionX = ballPositionX + dx;
        ballTotalPositionY = ballPositionY + ball.getRadius();
        ball.drawBall(ballPositionX, ballPositionY, canvas);
        if(ballTotalPositionY >= cellHeight - 200){
//            if(ballPositionY - ball.getRadius() >= barPlace.top &&
//                    ballPositionX - ball.getRadius() >=barPlace.left)
//                flow = -flow;
            if(ball.collisionWithBar(barPlace)){
                dy = - dy;
                //dx = - dx;
            }

        }


        if(ballPositionX <= 0){
            dx = - dx;
            //dy = - dy;
        }
        if(ballPositionX >= cellWidth){
            dx = - dx;
            //dy = - dy;
        }
        if(ballPositionY <= 0)
            dy = - dy;
        // Brick Section
        for (Brick brick:
             bricks) {
            canvas.drawRect(brick.getBrick(), brick.brickPaint);
        }

        if(ballPositionY <= cellHeight/2){
            int needToRemove = 0;
            for (int i = totalBricks - 1; i > 0; i--) {
                if(ball.collisionWithBrick(bricks.get(i).getBrick())){
                    needToRemove = i;
                    dy = -dy;
                }
            }
            if(needToRemove != 0){
                bricks.remove(needToRemove);
                --totalBricks;
            }
        }

        // Bar Section
        bar.drawBar(canvas, primaryPostion, primaryPostion + 200);
        barPlace = bar.getBarPlace();
        invalidate();
    }
    public ShowTime(Context context) {
        super(context);
        ball = new Ball();
        bar = new Bar();
        boundary = new Paint();
        boundary.setColor(Color.GREEN);
        boundary.setStyle(Paint.Style.FILL);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // Bar START

                if(isFirstPosition){
                    previousPosition = motionEvent.getX();
                    Log.d(TAG, "prevPos: "+ presentPosition);
                    isFirstPosition = false;
                    return true;
                }
                presentPosition = motionEvent.getX();
                Log.d(TAG, "presentPos: "+ presentPosition);
                if(presentPosition > previousPosition)
                    primaryPostion = primaryPostion + 10;
                else
                    primaryPostion = primaryPostion - 10;
                previousPosition = presentPosition;
                if(primaryPostion + 200 >= cellWidth)
                    primaryPostion = cellWidth - 200;
                else if (primaryPostion <= 0)
                    primaryPostion = 0;
                return true;
            }

            // Bar END
        });
    }


}

