package com.example.lab9;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity {

    private ArrayList<String> musicList; // список музыкальных файлов
    private MediaPlayer mediaPlayer;
    private int currentTrackIndex = 0;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        // Инициализация списка треков
        musicList = new ArrayList<>();
        musicList.add("cant_stop");
        musicList.add("touch_you_right_now");
        // Добавьте остальные треки по аналогии

        // Инициализация MediaPlayer и воспроизведение первого трека
        mediaPlayer = new MediaPlayer();


        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    pausePlayback();
                } else {
                    startPlayback();
                }
            }
        });

        // Кнопка для переключения на следующий трек
        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTrackIndex < musicList.size() - 1) {
                    currentTrackIndex++;
                } else {
                    currentTrackIndex = 0; // Вернуться к началу списка, если достигнут конец
                }
                playTrack(currentTrackIndex);
                if (isPlaying) {
                    startPlayback();
                }
                updateTrackName();
            }
        });

        playTrack(currentTrackIndex);
        startPlayback();
        updateTrackName();
    }

    private void playTrack(int trackIndex) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        try {
            mediaPlayer.reset();
            int resourceId = getResources().getIdentifier(musicList.get(trackIndex), "raw", getPackageName());
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + resourceId));
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void startPlayback() {
        mediaPlayer.start();
        isPlaying = true;
        updatePlayButton();
    }

    private void pausePlayback() {
        mediaPlayer.pause();
        isPlaying = false;
        updatePlayButton();
    }

    private void updatePlayButton() {
        Button playButton = findViewById(R.id.play_button);
        playButton.setText(isPlaying ? "Pause" : "Play");
    }

    private void updateTrackName() {
        TextView trackNameTextView = findViewById(R.id.track_name);
        trackNameTextView.setText(musicList.get(currentTrackIndex).replace("_", " "));
    }
}