package com.project.codetuts.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.project.codetuts.Auth.SignIn;
import com.project.codetuts.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    public void start(View view) {
        startActivity(new Intent(SplashScreen.this, SignIn.class));
        finish();
    }
}