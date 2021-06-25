package Bingo.Representation.Model;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Ball {


    public Ball() {
    }

    public void drawBall(PGraphics g, float x, float y, float textSize, float ballSize,
                         int textColor, int circleColor, int value) {
        g.noStroke();
        g.fill(circleColor);
        g.circle(x, y, ballSize);
        g.fill(textColor);
        g.textSize(textSize);
        g.text(value, x, y + (float) (g.textAscent() * 0.8 / 2));
    }
}
