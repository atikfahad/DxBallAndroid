package com.atikfahad.dxball.dxball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    View layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        layout = (View) findViewById(R.id.overLayout);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setContentView(new ShowTime(getApplicationContext()));
                return true;
            }
        });
        TextView gameOver = (TextView)findViewById(R.id.gameOver);
        gameOver.setText("SCORE : " + getIntent().getStringExtra("SCORE"));
    }

}
