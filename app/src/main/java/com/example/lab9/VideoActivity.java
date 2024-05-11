package com.example.lab9;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {
    private VideoView videoView;
    private MediaController mediaController;
    private Button playButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.video_view);
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);

        // Устанавливаем медиаконтроллер
        videoView.setMediaController(mediaController);

        playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(v -> prepareAndStartVideo());
    }


    private void prepareAndStartVideo() {
        videoView.setOnPreparedListener(mp -> {
            mp.start();
            playButton.setVisibility(View.GONE); // Скрываем кнопку после запуска видео
        });
        videoView.requestFocus();
        videoView.start();
    }
}