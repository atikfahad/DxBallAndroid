package com.atikfahad.dxball.dxball;

import android.graphics.Canvas;

public abstract class Drawable {
    private float x = 0;
    private float y = 0;

    public void setx(float x){
        this.x = x;
    }
    public float getx(){
        return this.x;
    }
    public void sety(float y){
        this.y = y;
    }
    public float gety(float y){
        return this.y;
    }
    public abstract void draw(Canvas canvas);
}
