package Bingo.Representation.Model;

import Bingo.Engine.Engine;
import processing.core.PGraphics;
import processing.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public abstract class BingoCard {


    public void drawBingoCard(PGraphics g, Engine engine, float x, float y, float boxSize, float boxGap, float textSize,
                              float scale) {
        float tempY, tempX;
        int index = 0;


        for (int i = 0; i < 5; i++) {
            tempX = (x - (((boxSize * 5) + (boxGap * 4)) / 2) + (i * (boxSize + boxGap))) * scale;

            for (int j = 0; j < 5; j++) {
                tempY = (y + j * (boxSize + boxGap)) * scale;

                drawBingoField(g, engine, tempX, tempY, boxSize * scale, textSize * scale, index);
                index++;
            }
        }

    }

    abstract protected void drawBingoField(PGraphics g, Engine engine, float x, float y, float boxSize, float textSize, int index);

}
