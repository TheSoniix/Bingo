package Bingo.Representation.Model;

import processing.core.PGraphics;

public class Ball {
    private final float x, y, scale, size;
    private final int color;

    public Ball(float x, float y, int size, int color, float scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.size = size;
        this.color = color;
    }

    public void draw(PGraphics g) {
        g.fill(color);
        g.circle(x * scale, y * scale, size * scale);
    }

}
