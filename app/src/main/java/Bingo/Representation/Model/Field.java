package Bingo.Representation.Model;

import processing.core.PGraphics;

public class Field {

    public static void draw(PGraphics g, float x, float y, float size, int color, float scale) {
        g.fill(color);
        g.rect(x * scale, y * scale, size * scale, size * scale);
    }
}
