package com.sujan.twiteerclone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.sujan.twiteerclone.R;

public class SplashActivity extends AppCompatActivity {


    ImageView twLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        twLogo = findViewById(R.id.twLogo);

//        Using a thread and halt screen for 2 seconds.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, TwiteerActivity.class);
                startActivity(intent);
                finish(); //close the activity
            }
        }, 2000);

    }
}


