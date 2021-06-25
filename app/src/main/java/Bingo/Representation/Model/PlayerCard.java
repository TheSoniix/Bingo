package Bingo.Representation.Model;

import Bingo.Engine.Engine;
import processing.core.PGraphics;

public class PlayerCard extends BingoCard {

    public PlayerCard() {}

    protected void drawBingoField(float tempX, float tempY, float boxSide, float textSize, Engine engine, int index, PGraphics g) {
        int rectColor;

        if (overRect(tempX, tempY, boxSide, boxSide, g)) {
            rectColor = g.color(255, 113, 31);
            if (g.parent.mousePressed && g.parent.mouseButton == g.parent.LEFT) { engine.markPlayerCard(index); }
        } else {
            rectColor = (engine.isPlayerFieldMarked(index)) ? g.color(160, 221, 230) : g.color(255, 252, 255);
            rectColor = (engine.isPlayerFieldWinner(index)) ? g.color(0, 204, 102) : rectColor;
        }

        g.fill(rectColor);
        g.rect(tempX, tempY, boxSide, boxSide);

        g.fill(0);
        g.textSize(textSize);
        g.text(Integer.toString(engine.getPlayerCard().get(index)), tempX + (boxSide / 2),
                tempY + (float) (g.textAscent() * 0.8 / 2) + (boxSide / 2));
    }
}
