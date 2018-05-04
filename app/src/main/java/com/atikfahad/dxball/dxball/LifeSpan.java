package com.atikfahad.dxball.dxball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class LifeSpan {
    private int lifeCount = 0;
    private int pointCount = 0;
    private Paint paint = new Paint();
    android.graphics.drawable.Drawable iconLife;
    public LifeSpan(Context context){
        iconLife = context.getResources().getDrawable(R.drawable.ic_heart_test);
        lifeCount = 3;
        pointCount = 0;
    }
    public void increasePoint(int increase){
        this.pointCount = this.pointCount + increase;
    }
    public void decreasePoint(int decrease){
        this.pointCount = this.pointCount - decrease;
    }
    public int getPoint(){
        return this.pointCount;
    }
    public void scoreBoard(Canvas canvas){
        paint.setColor(Color.parseColor("#F44336"));
        paint.setTextSize(22);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("SCORE : " + this.pointCount, 10, 25, paint);
    }
    public boolean removeLife(Canvas canvas){
        --lifeCount;
        this.drawLife(canvas);
//        int draw = 60;
//        int increment = 0;
//        for(int i = 1; i <= lifeCount; i++){
//            iconLife.setBounds(canvas.getWidth() - (draw * i),0,canvas.getWidth() - (draw * increment), 35);
//            iconLife.draw(canvas);
////            iconLife.setBounds(cellWidth - 120,0,cellWidth - 60, 35);
////            iconLife.draw(canvas);
////            iconLife.setBounds(cellWidth - 180,0,cellWidth - 120, 35);
////            iconLife.draw(canvas);
//        }
        if(lifeCount == 0)
            return false;
        else
            return true;
    }
    public void setLifeCount(Canvas canvas){
        ++lifeCount;
        this.drawLife(canvas);
    }
    public void drawLife(Canvas canvas){
        int draw = 60;
        int increment = 0;
        for(int i = 1; i <= lifeCount; i++){
            iconLife.setBounds(canvas.getWidth() - (draw * i),0,canvas.getWidth() - (draw * increment), 35);
            iconLife.draw(canvas);
            increment++;
        }
    }
}
