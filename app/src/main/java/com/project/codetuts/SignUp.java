package com.project.codetuts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void sign_In(View view) {
        startActivity(new Intent(SignUp.this, SignIn.class));
        finish();
    }

    public void sign_Up(View view) {
        startActivity(new Intent(SignUp.this, MainActivity.class));
        finish();
    }
}