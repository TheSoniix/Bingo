package Bingo.Representation.Model;

import Bingo.Engine.Engine;
import Bingo.Engine.Model.Field;
import Bingo.Representation.Utils.Hover;
import processing.core.PGraphics;

public class PlayerCard extends BingoCard {

    @Override
    protected void drawBingoField(PGraphics g, Engine engine, float x, float y, float boxSize, float textSize, int index) {
        int rectColor = g.color(255, 252, 255);
        Field currField = engine.getPlayerCard().get(index);

        if (Hover.rect(g, x, y, boxSize, boxSize) &&
                !engine.isGameOver()) {
            rectColor = g.color(255, 122, 75);
            if (g.parent.mousePressed && g.parent.mouseButton == g.LEFT) {
                engine.markPlayerCard(index);
            }
        } else {
            rectColor = currField.isMarked() ? g.color(185, 238, 255) : rectColor;
            rectColor = currField.isWinner() ? g.color(4, 240, 106) : rectColor;
        }

        g.fill(rectColor);
        g.rect(x, y, boxSize, boxSize);

        float midBox = boxSize / 2;
        Text.draw(g, Integer.toString(currField.getValue()), x + midBox, y + midBox, textSize, 0, 1);
    }

}
