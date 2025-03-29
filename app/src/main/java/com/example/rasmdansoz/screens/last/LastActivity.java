package com.example.rasmdansoz.screens.last;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.example.rasmdansoz.R;
import com.example.rasmdansoz.screens.game.GameActivity;
import com.example.rasmdansoz.screens.start.StartActivity;

public class LastActivity extends AppCompatActivity {
    private AppCompatButton restart;
    private AppCompatButton menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        setContentView(R.layout.activity_last);
        loadViews();
        restart.setOnClickListener(view -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
            finish();
        });
        menu.setOnClickListener(view -> {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }

    private void loadViews() {
        restart = findViewById(R.id.restart_button);
        menu = findViewById(R.id.menu_button);
    }
}