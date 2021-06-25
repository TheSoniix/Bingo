package Bingo.Representation.Model;


import Bingo.Engine.Engine;
import processing.core.PGraphics;

public class OpponentCard extends BingoCard{

    public OpponentCard() {}

    protected void drawBingoField(float tempX, float tempY, float boxSide, float textSize, Engine engine, int index, PGraphics g) {
        String text = (engine.isCpuFieldMarked(index) ? Integer.toString(engine.getCpuCard().get(index)) : "");
        int rectColor = engine.isCpuFieldWinner(index) ? g.color(0, 204, 102) : 0;


        // Rect
        g.fill(rectColor);
        g.rect(tempX, tempY, boxSide, boxSide);

        // Text
        g.fill(250);
        g.textSize(textSize);
        float testHeight = (float) (g.textAscent() * 0.8 / 2);
        g.text(text, tempX + (boxSide /2), tempY + (boxSide / 2) + testHeight);

    }
}
