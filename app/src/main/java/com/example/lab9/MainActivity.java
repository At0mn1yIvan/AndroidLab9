package com.example.lab9;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button taskBtn3 = findViewById(R.id.buttonTask3);
        taskBtn3.setOnClickListener(v -> {
            startActivity(new Intent(this, ParticlesActivity.class));
        });

        Button taskBtn4 = findViewById(R.id.buttonTask4);
        taskBtn4.setOnClickListener(v -> {
            startActivity(new Intent(this, FountainActivity.class));
        });

        Button taskBtn5 = findViewById(R.id.buttonTask5);
        taskBtn5.setOnClickListener(v -> {
            startActivity(new Intent(this, MusicActivity.class));
        });

        Button taskBtn6 = findViewById(R.id.buttonTask6);
        taskBtn6.setOnClickListener(v -> {
            startActivity(new Intent(this, VideoActivity.class));
        });
    }
}