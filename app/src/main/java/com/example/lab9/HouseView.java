package com.example.lab9;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;


public class HouseView extends View {

    private Bitmap[] characterFrames;
    private int currentFrame = 0;
    private int frameWidth, frameHeight;
    private int characterX, characterY;
    private int moveSpeed = 30;

    public HouseView(Context context) {
        super(context);
        init();
    }

    public HouseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HouseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Загрузка изображений для анимации персонажа
        characterFrames = new Bitmap[10];
        Resources resources = getResources();
        for (int i = 1; i <= 10; i++) {
            String resourceName = "walk_" + i;
            int resourceId = resources.getIdentifier(resourceName, "drawable", getContext().getPackageName());
            characterFrames[i - 1] = BitmapFactory.decodeResource(resources, resourceId);
        }

        // Определение размеров кадра анимации
        frameWidth = characterFrames[0].getWidth() / 3;
        frameHeight = characterFrames[0].getHeight() / 3;

        for (int i = 0; i < characterFrames.length; i++) {
            characterFrames[i] = Bitmap.createScaledBitmap(characterFrames[i], frameWidth, frameHeight, true);
        }

    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        drawEnvironment(canvas);
        drawCharacter(canvas);

        postInvalidateDelayed(100);
    }

    private void drawCharacter(Canvas canvas) {
        canvas.drawBitmap(characterFrames[currentFrame], characterX, characterY, null);
        currentFrame++;
        if (currentFrame >= characterFrames.length) {
            currentFrame = 0;
        }
        characterX += moveSpeed;

        if (characterX >= getWidth())
            characterX = 0;
    }

    private void drawEnvironment(Canvas canvas) {
        Paint housePaint = new Paint();
        housePaint.setColor(Color.parseColor("#FFA500"));

        Paint roofPaint = new Paint();
        roofPaint.setColor(Color.parseColor("#7F4222"));

        Paint fluePaint = new Paint();
        fluePaint.setColor(Color.parseColor("#FD5E53"));

        Paint skyPaint = new Paint();
        skyPaint.setColor(Color.parseColor("#87CEEB"));

        Paint grassPaint = new Paint();
        grassPaint.setColor(Color.parseColor("#00FF00"));

        int width = getWidth();
        int height = getHeight();

        // Небо
        canvas.drawRect(0, 0, width, height / 2f, skyPaint);

        // Трава
        canvas.drawRect(0, height / 2f, width, height, grassPaint);

        // Основа дома
        canvas.drawRect(350, height / 2f - 200, width - 350, height / 2f + 300, housePaint);

        // Труба
        canvas.drawRect(width - 500, height / 2f - 550, width - 430, height / 2f - 330, fluePaint);

        // Крыша
        Path roofPath = new Path();
        roofPath.moveTo(400, height / 2f - 200);
        roofPath.lineTo(200, height / 2f - 200);
        roofPath.lineTo(width / 2f, height / 2f - 500);
        roofPath.lineTo(width - 200, height / 2f - 200);
        roofPath.lineTo(width - 400, height / 2f - 200);
        canvas.drawPath(roofPath, roofPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Начальное положение персонажа после измерения размеров View
        characterX = 20; // Начальное положение персонажа по X
        characterY = h / 2 + 100; // Положение персонажа по Y (под домом)
    }
}

