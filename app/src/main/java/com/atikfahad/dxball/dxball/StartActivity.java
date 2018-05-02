package com.atikfahad.dxball.dxball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class StartActivity extends AppCompatActivity {
    ShowTime showTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showTime = new ShowTime(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        showTime.setFocusableInTouchMode(true);
        showTime.requestFocus();
        setContentView(showTime);
        //setContentView(R.layout.activity_start);
    }
}
