package com.example.ruby.materiallab.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ruby.materiallab.R;

/**
 * Splash View
 * Includes:
 * Animations from different views executed at the same time.
 */
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2000;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        startAnimations();
    }

    private void startAnimations() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        animation.reset();
        View container = findViewById(R.id.lin_lay);
        container.clearAnimation();
        container.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        animation.reset();
        View iv_logo = findViewById(R.id.iv_logo);
        iv_logo.clearAnimation();
        iv_logo.startAnimation(animation);
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                btnGoNext();
            }
        }, SPLASH_TIME_OUT);
    }

    public void btnGoNext() {
        startActivity(new Intent(this, MainActivity.class));//LoginActivity //MainActivity //TabsHeaderActivity
        finish();
    }
}
