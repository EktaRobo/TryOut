package com.ekpersonalapp.tryout.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;

import com.ekpersonalapp.tryout.R;
import com.ekpersonalapp.tryout.signin.SignInActivity;

public class SplashActivity extends AppCompatActivity {

    public static final int DELAY_MILLIS = 2000;
    private static final long SPLASH_TIMER = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setTimer();
    }

    private void setTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                moveToNextScreen();
            }
        }, SPLASH_TIMER);
    }

    private void moveToNextScreen() {
        Intent signInIntent = new Intent(SplashActivity.this, SignInActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, findViewById(R.id.logo_image),
                        getString(R.string.logo));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            startActivity(signInIntent, options.toBundle());
        } else {
            startActivity(signInIntent);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, DELAY_MILLIS);
    }
}
