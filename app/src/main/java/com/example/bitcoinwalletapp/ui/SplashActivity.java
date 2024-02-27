/**
 * @author Matlhogonolo Manye
 * @date 27/02/24
 *
 */
package com.example.bitcoinwalletapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.bitcoinwalletapp.MainActivity;
import com.example.bitcoinwalletapp.R;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DISPLAY_DURATION = 4000; // 4 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);

                // Close the splash activity
                finish();
            }
        }, SPLASH_DISPLAY_DURATION);
    }
}