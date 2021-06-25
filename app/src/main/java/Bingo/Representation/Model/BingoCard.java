package Bingo.Representation.Model;
import Bingo.Engine.Engine;
import processing.core.PGraphics;

public abstract class BingoCard {

    public BingoCard() {}

    public void drawBingoCard(PGraphics g, float x, float y, float boxSide, float boxGap, float textSize, Engine engine) {
        float tempY, tempX;
        int index = 0;

        for (int i = 0; i < 5; i++) {
            tempX = x - (((boxSide  * 5) + (boxGap * 4)) / 2) + (i * (boxSide + boxGap)) ;

            for (int j = 0; j < 5; j++) {
                tempY = y + j * (boxSide + boxGap);

                drawBingoField(tempX, tempY, boxSide, textSize, engine, index, g);

                index++;
            }
        }
    }

    abstract protected void drawBingoField(float tempX, float tempY, float boxSide, float textSize, Engine engine, int index, PGraphics g);

    protected boolean overRect(float x, float y, float width, float height, PGraphics g) {
        return g.parent.mouseX >= x && g.parent.mouseX <= x + width && g.parent.mouseY >= y && g.parent.mouseY <= y + height;
    }
}
