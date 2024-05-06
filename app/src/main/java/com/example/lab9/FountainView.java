package com.example.lab9;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FountainView extends View {
    private List<Particle> particles;
    private Paint paint;
    private Paint textPaint;

    public FountainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        particles = new ArrayList<>();

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(15);
        textPaint.setTextAlign(Paint.Align.CENTER);


        // Запуск потока обновления
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(30);
                    postInvalidate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (particles.size() < 500) {
            particles.add(new Particle(getWidth() / 2f, getHeight()));
        }
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            canvas.drawText("★", p.x, p.y, textPaint);
            //canvas.drawCircle(p.x, p.y, 5, paint);
            p.update();
            if (p.y > getHeight()) {
                particles.remove(i);
            }
        }
    }
}
