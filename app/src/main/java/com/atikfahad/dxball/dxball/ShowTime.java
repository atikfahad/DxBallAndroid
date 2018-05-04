package com.atikfahad.dxball.dxball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class ShowTime extends View{
    LifeSpan lifeSpan;
    Paint paint = new Paint();
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
    Paint boundary;
    private int ballPositionX, ballPositionY, ballTotalPositionY;
    private int totalBricks;
    @Override
    protected void onDraw(Canvas canvas) {
        if(isFirst){
            lifeSpan.drawLife(canvas);
            int calculate = canvas.getWidth();
            howMany = calculate / 40;
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
            for(int i = totalBricks - 1; i > 0; i--){
                bricks.add(new Brick());
                Log.d("Bricks Creation", Integer.toString(i));
            }
            bricks.add(new BrickSecond());
            ballPositionX = cellWidth / 2;
            ballPositionY = cellHeight - 100;

            int initialX = 0, initialY = 32;
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
        //canvas.drawColor(Color.parseColor("#424242"));
        //paint.setShader(new LinearGradient(0, 0, 0, getHeight(), Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));
        //canvas.drawPath(arrowPath, paint);
        //canvas.drawColor(paint);


        // LIFE SPAN START
        lifeSpan.drawLife(canvas);
        // Converted into dynamic;
//        iconLife.setBounds(cellWidth - 60,0,cellWidth, 35);
//        iconLife.draw(canvas);
//        iconLife.setBounds(cellWidth - 120,0,cellWidth - 60, 35);
//        iconLife.draw(canvas);
//        iconLife.setBounds(cellWidth - 180,0,cellWidth - 120, 35);
//        iconLife.draw(canvas);



        // LIFE SPAN END

        // SCORE BOARD START
        // Changed to Dynamic
        lifeSpan.scoreBoard(canvas);

        // SCORE BOARD END

        // LEVEL BOARD START

        paint.setColor(Color.parseColor("#F44336"));
        paint.setTextSize(25);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("LEVEL : X", cellWidth / 2 - 30,25 , paint);


        // LEVEL BOARD END

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
            if(ballPositionY >= cellHeight){
                if(lifeSpan.removeLife(canvas)){
                    dy = - dy;
                    try { Thread.sleep(2000); }
                    catch (InterruptedException ex) { android.util.Log.d("DX Ball", ex.toString()); }
                    // HERE NEED TO IMPROVE MORE MAY BE IMPLEMENT THREAD
                }
                else{
                    // GAME WILL BE OVER HERE
                }
            }

        }

        if(ballPositionX <= 0){
            dx = - dx;
        }
        if(ballPositionX >= cellWidth){
            dx = - dx;
        }
        if(ballPositionY <= 0)
            dy = - dy;

        // Brick Section
        for (Brick brick:
             bricks) {
            canvas.drawRect(brick.getBrick(), brick.brickPaint);
        }
        if(bricks.isEmpty()){
            // END OF THE STAGE
        }

        if(ballPositionY <= cellHeight/2){
            int needToRemove = -1;
//            for (int i = totalBricks - 1; i >= 0; i--) {
//                if(ball.collisionWithBrick(bricks.get(i).getBrick())){
//                    needToRemove = i;
//                    dy = -dy;
//                }
            for (int i = 0; i < totalBricks; i++) {
                if(ball.collisionWithBrick(bricks.get(i).getBrick())){
                    lifeSpan.increasePoint(bricks.get(i).getBrickPoint());
                    if(bricks.get(i).getCATEGORY() == 2){
                        if(((BrickSecond) bricks.get(i)).executeIt())
                            needToRemove = i;
                        else
                            needToRemove = -1;
                    }
                    else
                        needToRemove = i;

                    dy = - dy;
                }

            }

            if(needToRemove != -1){
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
        lifeSpan = new LifeSpan(context);
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

