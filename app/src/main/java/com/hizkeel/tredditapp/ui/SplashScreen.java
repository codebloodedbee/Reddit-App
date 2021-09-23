package com.hizkeel.tredditapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.hizkeel.tredditapp.R;

public class SplashScreen extends AppCompatActivity {

    Handler handler = new Handler();
    Runnable runnable;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        timer(3000);
    }

    private void timer(long timeInMilli) {
        runnable = new Runnable() {
            @Override
            public void run() {
                go();

            }

        };

        handler.postDelayed(runnable, timeInMilli);
    }

    public void go(){
        Intent intent = new Intent(SplashScreen.this, Home.class);
        startActivity(intent);
    }


}