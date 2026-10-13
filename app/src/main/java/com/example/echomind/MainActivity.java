package com.example.echomind;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProgressBar bar = findViewById(R.id.splashProgress);

        final int total = 100;
        final long stepTime = 15; // 1.5 ثانية إجمالاً
        final Handler handler = new Handler();

        new Thread(() -> {
            for (int i = 1; i <= total; i++) {
                final int p = i;
                handler.post(() -> bar.setProgress(p));
                try { Thread.sleep(stepTime); } catch (InterruptedException e) { }
            }
            handler.post(() -> {
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                finish();
            });
        }).start();
    }
}
