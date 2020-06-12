package com.example.pglocator;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    private static int Splash_Time_Out = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent into = new Intent(WelcomeActivity.this, Login_Activity.class);
                startActivity(into);
                finish();
            }
        },Splash_Time_Out);
    }
}
