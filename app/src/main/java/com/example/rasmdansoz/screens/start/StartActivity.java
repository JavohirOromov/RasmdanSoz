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

public class StartActivity extends AppCompatActivity {

    private QuitDialog quitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        quitDialog = new QuitDialog(this);
        findViewById(R.id.start).setOnClickListener(view -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.quit).setOnClickListener(view -> {
            quitDialog.show();
        });
        findViewById(R.id.resume).setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("id",1);
            startActivity(intent);
        });
        quitDialog.setYesBtnClickListener(() -> {
            finish();
            quitDialog.dismiss();
        });
    }
}