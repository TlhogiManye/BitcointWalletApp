package com.example.bitcoinwalletapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

/**
 * @author tlhogi-manye
 * @date 28/02/2024
 *
 * This is the entrance activity and hosts the fragments for the different features that
 *
 * are injected into the main screen
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity: ", "MainActivity entrance");

    }
}