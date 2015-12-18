package com.example.igp.igpmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

/**
 * Created by vimal on 15/12/15.
 */
public class SplashActivity extends Activity {

    private long timeDelay = 3000;  // 3 seconds delay for splash screen

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.splash_screen);

        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, timeDelay);*/
    }
}
