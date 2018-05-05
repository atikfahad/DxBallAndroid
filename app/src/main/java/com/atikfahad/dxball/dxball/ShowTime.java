package com.atikfahad.dxball.dxball;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class ShowTime extends View{
    private boolean gameOver = false, levelUp = false;
    private boolean levelSecondFirst = false;
    LifeSpan lifeSpan;
    Paint paint = new Paint();
    private int currentLevel = 1;
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
            isFirst = false;

            // TOTAL BRICKS BASED ON WIDTH
            int calculate = canvas.getWidth();
            howMany = calculate / 40;
            extraSpace = calculate % 40;
            cellHeight = canvas.getHeight();
            cellWidth = canvas.getWidth();
            bricks = new ArrayList<Brick>();
            totalBricks = howMany;
            switch (currentLevel){
                case 1:
                    levelFirst(canvas);
                    break;
                case 2:
                    levelSecond(canvas);
                    break;
                default:
                    gameFinished(canvas);
                    break;
            }
            // LEVEL INITIALIZE

            // BAR AREA
            primaryPostion = cellWidth/2 - 50;
            bar.drawBar(canvas, primaryPostion, primaryPostion + 100);

            // BALL AREA
            ballPositionX = cellWidth / 2;
            ballPositionY = cellHeight - 100;
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
        canvas.drawText("LEVEL : " + this.currentLevel, cellWidth / 2 - 30,25 , paint);


        // LEVEL BOARD END

        // Boundary Section
//        canvas.drawRect(0,0, extraSpace / 2, cellHeight, boundary);
//        canvas.drawRect(cellWidth - extraSpace / 2,0, cellWidth, cellHeight, boundary);


        // Bar Section

        bar.drawBar(canvas, primaryPostion, primaryPostion + 200);
        barPlace = bar.getBarPlace();

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
                    lostLife(canvas);
                }
                else{
                    //isFirst = true;
                    gameOver = true;
                    bricks.clear();
                    //invalidate();
                    gameOver(canvas);
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
        if(!gameOver || !levelUp){
            for (Brick brick:
                    bricks) {
                canvas.drawRect(brick.getBrick(), brick.brickPaint);
            }
        }

        if(bricks.isEmpty()){
            currentLevel = 2;
            isFirst = true;
            //invalidate();
            levelUp = true;
            levelSecondFirst = true;
            levelSecond(canvas);
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
        if (gameOver){
            canvas.drawColor(Color.DKGRAY);
        }
        if(levelSecondFirst){
            canvas.drawColor(Color.GREEN);
            paint.setColor(Color.BLUE);
            paint.setTextSize(50);
            paint.setFakeBoldText(true);
            canvas.drawText("LEVEL 1 DONE!",canvas.getWidth() / 2 - 200,canvas.getHeight() / 2, paint);
            invalidate();
            Vibrator v = (Vibrator)getContext().getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(400);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            levelSecondFirst = false;

        }
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


    public void levelFirst(Canvas canvas){
        currentLevel = 1;
        gameOver = false;
        levelUp = false;

        for(int i = totalBricks; i > 0; i--){
            if(i % 2 == 0)
                if(i < 5)
                    bricks.add(new BrickSecond());
                else
                    bricks.add(new Brick());
            else
                if(i > 10)
                    bricks.add(new Brick());
                else
                    bricks.add(new BrickSecond());
            Log.d("Bricks Creation", Integer.toString(i));
        }
        //bricks.add(new Brick());



        //bricks.add(new BrickSecond());
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
    public void levelSecond(Canvas canvas){
//        canvas.drawColor(Color.GREEN);
//        paint.setColor(Color.BLUE);
//        paint.setTextSize(50);
//        paint.setFakeBoldText(true);
        //canvas.drawText("LEVEL 1 DONE!",canvas.getWidth() / 2 - 200,canvas.getHeight() / 2, paint);
//        Vibrator v = (Vibrator)getContext().getSystemService(Context.VIBRATOR_SERVICE);
//        v.vibrate(400);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        for(int i = totalBricks; i > 0; i--){
            if(i % 2 == 0)
                if(i < 5)
                    bricks.add(new BrickSecond());
                else
                    bricks.add(new Brick());
            else
            if(i > 10)
                bricks.add(new Brick());
            else
                bricks.add(new BrickSecond());
            Log.d("Bricks Creation", Integer.toString(i));
        }


        bricks.add(new BrickSecond());
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

    public void gameFinished(Canvas canvas){

    }
    public void lostLife(Canvas canvas){
        //invalidate();
        primaryPostion = cellWidth/2 - 50;
        ballPositionX = cellWidth / 2;
        ballPositionY = cellHeight - 100;
        canvas.drawColor(Color.RED);
        Vibrator v = (Vibrator)getContext().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        invalidate();

    }
    public void gameOver(Canvas canvas){
        isFirst = false;
        canvas.drawColor(Color.RED);
//        Paint over = new Paint();
//        over.setColor(Color.RED);
//        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), over);
//        over.setStyle(Paint.Style.FILL);
//        over.setTextSize(50);
//        over.setFakeBoldText(true);
//        canvas.drawText("FINAL SCORE : "+ lifeSpan.getPoint(),canvas.getWidth() / 2 - 200,canvas.getHeight() / 2, over);
        Vibrator v = (Vibrator)getContext().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
//        try {
//            Thread.sleep(200);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Intent i = new Intent(getContext(), GameOverActivity.class);
        i.putExtra("SCORE", Integer.toString(lifeSpan.getPoint()));
        getContext().startActivity(i);
        ((StartActivity )getContext()).finish();
//        invalidate();
//        bricks.clear();
//        paint.setColor(Color.MAGENTA);
//        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
//        canvas.drawColor(Color.parseColor("#C62828"));
//        paint.setColor(Color.WHITE);
//        paint.setTextSize(50);
//        paint.setFakeBoldText(true);
//        canvas.drawText("GAME OVER",canvas.getWidth() / 2 - 110,canvas.getHeight() / 2,paint);
//        canvas.drawText("FINAL SCORE: "+ lifeSpan.getPoint(),canvas.getWidth() / 2 - 200,canvas.getHeight() / 2 + 60,paint);
//        //Intent i = new Intent((StartActivity)getContext(), GameOverActivity.class);
//        Vibrator v = (Vibrator)getContext().getSystemService(Context.VIBRATOR_SERVICE);
//        v.vibrate(200);
//        try {
//            Thread.sleep(200);
//            ((StartActivity )getContext()).finish();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


}

