package Bingo.Representation.Model;

import processing.core.PConstants;
import processing.core.PGraphics;

public class Text {

    public static void draw(PGraphics g, String text, float x, float y, float size, int color, float scale) {
        g.textAlign(PConstants.CENTER);
        g.fill(color);
        g.textSize(size * scale);
        g.text(text, x * scale, y * scale + (float) (g.textAscent() * 0.8 / 2));
    }
}
