package Bingo.Representation.Model;

import processing.core.PGraphics;

public class Ball {

    public static void draw(PGraphics g, float x, float y, int size, int color, float scale) {
        g.fill(color);
        g.circle(x * scale, y * scale, size * scale);

    }

}
