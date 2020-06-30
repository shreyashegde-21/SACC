package com.example.pglocator;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private static int Splash_Time_Out = 3000;
    private Animation top,bottom;
    private ImageView wplogo;
    private TextView wpname,wpdescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        top = AnimationUtils.loadAnimation(this, R.anim.welcome_top_animation);
        bottom = AnimationUtils.loadAnimation(this, R.anim.welcome_bottom_animation);
        wplogo = (ImageView) findViewById(R.id.wplogo);
        wpname = (TextView) findViewById(R.id.wpname);
        wpdescription = (TextView) findViewById(R.id.wpdescription);

        wplogo.setAnimation(top);
        wpname.setAnimation(bottom);
        wpdescription.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent into = new Intent(WelcomeActivity.this, Login_Activity.class);

               /* Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(wplogo, "imagetransition");
                pairs[1] = new Pair<View, String>(wpname, "texttransition");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(WelcomeActivity.this, pairs);

                    startActivity(into, options.toBundle());
                }*/

                startActivity(into);
                finish();
            }
        },Splash_Time_Out);
    }
}

