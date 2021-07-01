package Bingo.Representation.Utils;

import processing.core.PGraphics;

public class Hover {

    public static boolean rect(PGraphics g, float x, float y, float width, float height) {
        return g.parent.mouseX >= x && g.parent.mouseX <= x + width &&
                g.parent.mouseY >= y && g.parent.mouseY <= y + height;
    }

    public static boolean circle(PGraphics g, float x, float y, float diameter) {
        float disX = x - g.parent.mouseX;
        float disY = y - g.parent.mouseY;
        return Math.sqrt((disX * disX) + (disY * disY)) < diameter / 2;
    }
}
