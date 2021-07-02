package Bingo.Representation.Model;

import processing.core.PGraphics;

public class Text {
    private final String text;
    private final float x, y, scale, size;
    private final int color;

    public Text(String text, float x, float y, float size, int color, float scale) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.color = color;
        this.size = size;
    }

    public void draw(PGraphics g) {
        g.fill(color);
        g.textSize(size * scale);
        g.text(text, x * scale, y * scale + (float) (g.textAscent() * 0.8 / 2));
    }
}
