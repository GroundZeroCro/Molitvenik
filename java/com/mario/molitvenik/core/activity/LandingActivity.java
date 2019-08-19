package com.mario.molitvenik.core.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.mario.molitvenik.R;

public class LandingActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_landing);

    new Thread(() -> {
      try {
        Thread.sleep(600);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Intent intent = new Intent(LandingActivity.this, MainActivity.class);
      startActivity(intent);
      finish();
    }).start();
  }
}
