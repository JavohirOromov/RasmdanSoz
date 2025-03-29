package com.example.rasmdansoz.screens.start;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rasmdansoz.R;
import com.example.rasmdansoz.dialogs.QuitDialog;
import com.example.rasmdansoz.screens.game.GameActivity;
import com.example.rasmdansoz.screens.info.InfoActivity;
import com.example.rasmdansoz.storage.LocalStorage;

public class StartActivity extends AppCompatActivity {
    private QuitDialog quitDialog;
    private LocalStorage storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        quitDialog = new QuitDialog(this);
        storage = LocalStorage.Companion.getInstance();

        AppCompatButton resume = findViewById(R.id.resume);

        if (storage.getIndex() == 0){
            resume.setVisibility(View.GONE);

        }else {
            resume.setVisibility(View.VISIBLE);
        }
        findViewById(R.id.start).setOnClickListener(view -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.quit).setOnClickListener(view -> {
            quitDialog.show();
        });
       resume.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("id",1);
            startActivity(intent);
        });
        findViewById(R.id.info).setOnClickListener(v ->{
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        });
        quitDialog.setYesBtnClickListener(() -> {
            finish();
            quitDialog.dismiss();
        });
    }
}