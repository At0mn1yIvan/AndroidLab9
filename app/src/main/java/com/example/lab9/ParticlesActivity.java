package com.example.lab9;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticlesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particles);

        FrameLayout container = findViewById(R.id.container);
        container.addView(new ParticleView(this));
    }

    private static class ParticleView extends View {

        private final List<Particle> particles;
        private final Paint textPaint;

        public ParticleView(Context context) {
            super(context);
            particles = new ArrayList<>();

            textPaint = new Paint();
            textPaint.setColor(Color.WHITE);
            textPaint.setTextSize(40);
            textPaint.setTextAlign(Paint.Align.CENTER);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            updateParticles();
            for (Particle particle : particles) {
                canvas.drawText("★", particle.position.x, particle.position.y, textPaint);
            }
            postInvalidateDelayed(5);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                float x = event.getX();
                float y = event.getY();
                createWaveParticles(x, y);
                invalidate();
                return true;
            }
            return super.onTouchEvent(event);
        }

        private void createWaveParticles(float x, float y) {
            final int NUM_PARTICLES = 100;
            final float INITIAL_RADIUS = 7;

            for (int i = 0; i < NUM_PARTICLES; i++) {
                float angle = (float) (Math.PI * 2 * i / NUM_PARTICLES);
                float offsetX = (float) (INITIAL_RADIUS * Math.cos(angle));
                float offsetY = (float) (INITIAL_RADIUS * Math.sin(angle));
                particles.add(new Particle(new PointF(x, y), new PointF(offsetX, offsetY)));
            }
        }

        private void updateParticles() {
            for (Particle particle : particles) {
                particle.position.x += particle.velocity.x;
                particle.position.y += particle.velocity.y;
            }
        }

    }

    private static class Particle {
        PointF position;
        PointF velocity;
        float radius = 3; // размер частицы

        public Particle(PointF position, PointF velocity) {
            this.position = position;
            this.velocity = velocity;
        }
    }
}