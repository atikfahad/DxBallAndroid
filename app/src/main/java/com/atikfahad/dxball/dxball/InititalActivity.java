package com.atikfahad.dxball.dxball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class InititalActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initital);
        ImageView logo = (ImageView)findViewById(R.id.logoImage);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.logo);
        logo.startAnimation(animation);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InititalActivity.this, StartActivity.class);
                startActivity(i);
                //finish();
            }
        });
    }
}
