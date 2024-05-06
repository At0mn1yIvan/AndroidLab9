package com.example.lab9;

public class Particle {
    float x, y; // координаты частицы
    float vx, vy; // скорость частицы
    float ax, ay; // ускорение частицы (для гравитации)

    public Particle(float x, float y) {
        this.x = x;
        this.y = y;
        //this.vx = (float) (Math.random() * 2 - 1) * 5; // случайная начальная скорость по x
        this.vx = (float) (Math.random() * 2 - 1) * 1f;
        this.vy = (float) (Math.random() * -10 - 5); // случайная начальная скорость вверх по y
        this.ax = 0;
        this.ay = 0.1f; // гравитация вниз
    }

    public void update() {
        // Обновление скорости с учетом ускорения
        vx += ax;
        vy += ay;
        // Обновление позиции с учетом скорости
        x += vx;
        y += vy;
    }
}
