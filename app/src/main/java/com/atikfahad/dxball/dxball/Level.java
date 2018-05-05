package com.atikfahad.dxball.dxball;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fahad on 5/5/18.
 */

public class Level {
    Canvas canvas;
    private int cellHeight, cellWidth;
    private int howMany;
    List<Brick> bricks;
    private int totalBricks;
    public Level(){
    }

    public List<Brick> firstLevel(Canvas canvas){
        this.canvas = canvas;
        cellHeight = canvas.getHeight();
        cellWidth = canvas.getWidth();

        howMany = cellWidth / 40;
        bricks = new ArrayList<Brick>();
        totalBricks = howMany * 2;
        for(int i = totalBricks - 1; i > 0; i--){
            bricks.add(new Brick());
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
        return bricks;
    }
}
