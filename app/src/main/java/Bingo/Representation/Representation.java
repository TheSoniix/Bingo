package Bingo.Representation;

import Bingo.Engine.Engine;
import Bingo.Engine.EngineImpl;
import Bingo.Engine.Model.Field;
import Bingo.Representation.Model.Ball;
import Bingo.Representation.Model.BingoField;
import Bingo.Representation.Model.Text;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public class Representation extends PApplet {

    public static void run() {
        PApplet.runSketch(new String[]{""}, new Representation());
    }

    // Engine of the Game
    Engine engine = new EngineImpl();


    // Colors
    int grey = color(47, 47, 47);
    int white = color(255, 252, 255);

    //  Size
    float midX, midY;
    float scale;

    // Ball
    int currBall;
    int random;

    // Counters for autoMark()
    int frameCounterBallAnimation = 0;
    int framesCounterCpuMark = 0;
    boolean animate = false;
    boolean drawn = false;
    float colCounter = 0;


    public void settings() {
        size(1280, 720);
        //   fullScreen();
    }


    public void setup() {
        scale = (width + height) / (float) (2560 + 1440);
        midX = (float) 2560 / 2;
        midY = (float) 1440 / 2;
        noStroke();
        textAlign(CENTER);
    }

    public void draw() {
        frameCounterBallAnimation++;
        background(147, 177, 173);


        // Überschrift
        Text.draw(super.g, "BINGO", midX, 100, 200, white, scale);


        // ESC
        Text.draw(super.g, "[ESC] = Exit", 100, 40, 20, grey, scale);

        // Überschrift Gegner Karte
        Text.draw(super.g, "Opponent", midX + 850, midY - 300, 32, grey, scale);


        // Draw counter
        String drawCounter = "Draw counter: " + engine.getDrawnBalls().size();
        Text.draw(super.g, drawCounter, midX - 900, midY - 300, 28, grey, scale);


        // Gezogenen Bälle
        IntStream.range(0, engine.getDrawnBalls().size()).forEach(this::gezogeneBaelle);


        // New GAme Button
        // parameter raus?
        this.newGame(midX + 1080, 25, 120, 35, 20);


        // Bingo Cards
        this.playerCard();
        this.opponentCard();


        if (!engine.isGameOver()) {

            this.eineKugelzumZiehen();
            this.kugelAnimation();

        } else {
            // Überschrift für Sieger
            Text.draw(super.g, "Game Over", midX, midY - 400, 50, white, scale);
            String winner = engine.isPlayerWinner() ? "You won!" : "Opponent won!";
            // y = 350
            Text.draw(super.g, winner, midX, midY - 300, 40, white, scale);
        }

        engine.isGameOver();
    }


    void eineKugelzumZiehen() {
        String tempText;
        if (!animate) {

            if (overCircle(midX * scale, (midY - 400) * scale, 100 * scale)) {
                colorGradient(midX * scale, (midY - 400) * scale, 150, false);
            } else {
                colorGradient(midX * scale, (midY - 400) * scale, 140, false);
            }

            tempText = (engine.getDrawnBalls().size() == 0) ? "Start" : Integer.toString(currBall);
            Ball.draw(super.g, midX, midY - 400, 100, white, scale);
            Text.draw(super.g, tempText, midX, midY - 400, 32, grey, scale);

        } else {
            tempText = Integer.toString(currBall);
            colorGradient(midX * scale, (midY - 400) * scale, 150, true);
            Ball.draw(super.g, midX, midY - 400, 100, grey, scale);
            Text.draw(super.g, tempText, midX, midY - 400, 32, white, scale);
        }
    }

    void kugelAnimation() {
        // Kugel Animation
        if (animate && frameCounterBallAnimation % 3 == 0) {
            int tempIdx = (int) random(0, engine.getNotDrawnBalls().size() - 1);
            currBall = engine.getNotDrawnBalls().get(tempIdx);
            if (frameCounterBallAnimation == 60) {
                currBall = engine.drawBall();
                animate = false;
                drawn = true;
            }
        }

        if (drawn) {
            // Cpu Funktion zum markieren wird aufgerufen
            framesCounterCpuMark++;
            if (framesCounterCpuMark == 60 * random) {
                engine.autoMarkCpuCard();
                framesCounterCpuMark = 0;
                drawn = false;
            }
        }
    }

    void gezogeneBaelle(int index) {
        colCounter = (index % 15 == 0) ? (float) index / 15 : colCounter;
        float x = ((midX - 1125) + 30 * (index % 15));
        float y = midY - 200 + 40 * colCounter;
        String tempText = Integer.toString(engine.getDrawnBalls().get(index));
        Ball.draw(super.g, x, y, 25, white, scale);
        Text.draw(super.g, tempText, x, y, 15, grey, scale);
    }

    void colorGradient(float x, float y, int radius, boolean isRed) {
        int tempRadius = (int) (radius * scale);
        int tempColorInt = isRed ? 147 : 177;
        for (int r = tempRadius; r > 0; --r) {
            if (isRed) {
                fill(tempColorInt, 177, 173);
            } else {
                fill(147, tempColorInt, 173);
            }
            ellipse(x, y, r, r);
            tempColorInt = (tempColorInt + 1);
        }
    }

    // midX + 1080 * scale,  25 * scale, 120 * scale, 35 * scale, 20 * scale
    private void newGame(float x, float y, float width, float height, float textSize) {
        boolean hover = overRect(x, y, width, height, scale);

        // Rect
        fill(hover ? color(214, 238, 255) : grey);
        rect(x * scale, y * scale, width * scale, height * scale, 10 * scale);

        // Text
        float X = x + (width / 2);
        float Y = y + (height / 2);
        Text.draw(super.g, "New Game", X, Y, textSize, hover ? grey : white, scale);
    }

    public void mousePressed() {
        if (mousePressed && mouseButton == LEFT) {

            // Ball
            if (overCircle(midX * scale, (midY - 400) * scale, 100 * scale)) {
                if (!animate) {
                    animate = true;
                    frameCounterBallAnimation = 0;
                    random = (drawn) ? random : round(random(1, 3));
                }
            }

            // New Game
            if (overRect(midX + 1080, 25, 120, 35, scale)) {
                engine.newGame();
                animate = false;
                currBall = 0;
            }

            // Player Field
            this.fieldList(midX, midY - 200, 150).stream().filter(r ->
                    overRect(r.getX(), r.getY(), r.getSize(), r.getSize(), scale)).findFirst()
                    .ifPresent(r -> engine.markPlayerCard(r.getIndex()));
        }
    }


    private void playerCard() {
        this.fieldList(midX, midY - 200, 150).forEach(this::drawPlayerField);
    }

    private void opponentCard() {
        this.fieldList(midX + 850, midY - 200, 100).forEach(this::drawOpponentField);
    }

    private List<BingoField> fieldList(float x, float y, float boxSize) {
        float boxGap = 2;
        List<BingoField> tempList = new ArrayList<>();

        IntStream.range(0, 5).forEach(i -> {
            float tempX = (x - (((boxSize * 5) + (boxGap * 4)) / 2) + (i * (boxSize + boxGap)));
            IntStream.range(0, 5).forEach(j -> {
                float tempY = (y + j * (boxSize + boxGap));
                tempList.add(new BingoField(tempX, tempY, boxSize, j + (i * 5)));
            });
        });

        return tempList;
    }


    private void drawPlayerField(BingoField tempR) {
        float x = tempR.getX();
        float y = tempR.getY();
        float size = tempR.getSize();
        int index = tempR.getIndex();

        // Namen vergabe!!
        Field currField = engine.getPlayerCard().get(index);
        String tempText = Integer.toString(currField.getValue());
        int rectColor = color(255, 252, 255);

        if (overRect(x, y, size, size, scale) && !engine.isGameOver()) {
            rectColor = color(255, 122, 75);
        } else {
            rectColor = currField.isMarked() ? color(185, 238, 255) : rectColor;
            rectColor = currField.isWinner() ? color(4, 240, 106) : rectColor;
        }

        fill(rectColor);
        rect(x * scale, y * scale, size * scale, size * scale);
        Text.draw(super.g, tempText, x + size / 2, y + size / 2, 36, 0, scale);
    }

    private void drawOpponentField(BingoField tempR) {
        float x = tempR.getX();
        float y = tempR.getY();
        float size = tempR.getSize();
        int index = tempR.getIndex();

        Field currField = engine.getCpuCard().get(index);
        int rectColor = currField.isWinner() ? color(255, 31, 61) : color(38, 38, 38);
        String text = currField.isMarked() ? Integer.toString(currField.getValue()) : "";

        fill(rectColor);
        rect(x * scale, y * scale, size * scale, size * scale);
        Text.draw(super.g, text, x + size / 2, y + size / 2, 28, 250, scale);
    }

    private boolean overRect(float x, float y, float width, float height, float scale) {
        return mouseX >= x * scale && mouseX <= (x + width) * scale &&
                mouseY >= y * scale && mouseY <= (y + height) * scale;
    }

    // auch mit scale?
    private boolean overCircle(float x, float y, float diameter) {
        float disX = x - mouseX;
        float disY = y - mouseY;
        return sqrt(sq(disX) + sq(disY)) < diameter / 2;
    }

}
