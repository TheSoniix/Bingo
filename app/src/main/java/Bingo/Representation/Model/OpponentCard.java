package Bingo.Representation.Model;


import Bingo.Engine.Engine;
import Bingo.Engine.Model.Field;
import processing.core.PGraphics;

public class OpponentCard extends BingoCard {

    @Override
    protected void drawBingoField(PGraphics g, Engine engine, float x, float y, float boxSize, float textSize, int index) {
        Field currField = engine.getCpuCard().get(index);
        //0, 204, 102 grün?
        int rectColor = currField.isWinner() ? g.color(255, 31, 61) : g.color(38, 38, 38);
        String text = currField.isMarked() ? Integer.toString(currField.getValue()) : "";

        // Rect
        g.fill(rectColor);
        g.rect(x, y, boxSize, boxSize);

        // Text
        float midBox = boxSize / 2;

        Text.draw(g, text, x + midBox, y + midBox, textSize, 250, 1);

    }


}
