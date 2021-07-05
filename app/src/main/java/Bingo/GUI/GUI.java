package Bingo.GUI;

import Bingo.Engine.Bingo;
import Bingo.Engine.BingoImpl;
import Bingo.Engine.Models.Field;
import Bingo.GUI.Models.Text;
import processing.core.PApplet;

import java.util.*;
import java.util.stream.IntStream;


public class GUI extends PApplet {

    public static void run() {
        PApplet.runSketch(new String[]{""}, new GUI());
    }

    // Engine of the Game
    Bingo engine = new BingoImpl();

    // Colors
    int grey = color(47, 47, 47);
    int white = color(255, 252, 255);

    //  Size
    int midX, midY;
    float scale;

    // Ball
    int currBall;
    int counterMulitplier;

    // Counters
    int animationCounter = 0;
    int markCounter = 0;
    boolean animate = false;
    boolean pulled = false;
    int colCounter = 0;

    // FieldLists
    List<HashMap<String, Integer>> pFieldInfo = new ArrayList<>();
    List<HashMap<String, Integer>> oFieldInfo = new ArrayList<>();

    public void settings() {
        fullScreen();
    }

    public void setup() {
        scale = (float) (width + height) / (2560 + 1440);
        midX = 2560 / 2;
        midY = 1440 / 2;
        noStroke();
        textAlign(CENTER);

        pFieldInfo = this.fieldList(midX - 379, midY - 200, 150);
        oFieldInfo = this.fieldList(midX + 596, midY - 200, 100);
    }

    public void draw() {
        background(147, 177, 173);

        // Überschrift
        new Text("BINGO", midX, 100, 200, white, scale).draw(super.g);

        // ESC
        new Text("[ESC] = Exit", 100, 40, 20, grey, scale).draw(super.g);

        // Überschrift Gegner Karte
        new Text("Opponent", midX + 850, midY - 300, 32, grey, scale).draw(super.g);

        // Draw counter
        String drawCounter = "Draw counter: " + engine.drawnBalls().size();
        new Text(drawCounter, midX - 900, midY - 300, 28, grey, scale).draw(super.g);

        // Gezogenen Bälle
        IntStream.range(0, engine.drawnBalls().size()).forEach(this::drawnBalls);

        // New GAme Button
        this.newGame();

        // Bingo Cards
        this.playerCard();
        this.opponentCard();

        if (!engine.isGameOver()) {
            if (animate) {
                this.paintAnimatedBall();
                this.animateBall();
            } else {
                this.paintNotAnimatedBall();
            }
            if (pulled) {
                this.autoMarkingOpponent();
            }
        } else {
            new Text("Game Over", midX, midY - 400, 50, white, scale).draw(super.g);
            String winner = engine.isPOneWinner() ? "You won!" : "Opponent won!";
            new Text(winner, midX, midY - 300, 40, white, scale).draw(super.g);
        }
        animationCounter++;
    }

    void paintNotAnimatedBall() {
        if (overCircle(midX, midY - 400)) {
            colorGradient(midX * scale, (midY - 400) * scale, 150 * scale, false);
        } else {
            colorGradient(midX * scale, (midY - 400) * scale, 140 * scale, false);
        }
        String tempText = (engine.drawnBalls().size() == 0) ? "Start" : Integer.toString(currBall);
        fill(white);
        circle(midX * scale, (midY - 400) * scale, 100 * scale);
        new Text(tempText, midX, midY - 400, 32, grey, scale).draw(super.g);

    }

    void paintAnimatedBall() {
        String tempText = Integer.toString(currBall);
        colorGradient(midX * scale, (midY - 400) * scale, 150 * scale, true);
        fill(grey);
        circle(midX * scale, (midY - 400) * scale, 100 * scale);
        new Text(tempText, midX, midY - 400, 32, white, scale).draw(super.g);
    }

    void autoMarkingOpponent() {
        if (markCounter == 60 * (counterMulitplier + 0.5)) {
            engine.pTwoCard().forEach(field -> {
                if (!engine.isGameOver()) {
                    engine.pTwoMarkField(field.getIndex());
                }
            });
            markCounter = 0;
            pulled = false;
        }
        markCounter++;
    }

    void animateBall() {
        if (animationCounter % 3 == 0) {
            int tempIdx = (int) random(0, engine.notDrawnBalls().size() - 1);
            currBall = engine.notDrawnBalls().get(tempIdx);
            if (animationCounter == 60) {
                currBall = engine.drawBall();
                animate = false;
                pulled = true;
            }
        }
    }

    void drawnBalls(int index) {
        colCounter = (index % 15 == 0) ? (index / 15) : colCounter;
        int x = ((midX - 1180) + 40 * (index % 15));
        int y = midY - 200 + 40 * colCounter;
        String tempText = Integer.toString(engine.drawnBalls().get(index));
        fill(white);
        circle(x * scale, y * scale, 35 * scale);
        new Text(tempText, x, y, 20, grey, scale).draw(super.g);
    }

    void colorGradient(float x, float y, float radius, boolean isRed) {
        int tempRadius = (int) radius;
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

    private void newGame() {
        boolean hover = overRect(2360, 25, 120, 35);
        // Rect
        fill(hover ? color(214, 238, 255) : grey);
        rect(2360 * scale, 25 * scale, 120 * scale, 35 * scale, 10 * scale);
        // Text
        int X = 2360 + 120 / 2;
        int Y = 25 + 35 / 2;
        new Text("New Game", X, Y, 20, hover ? grey : white, scale).draw(super.g);
    }

    public void mousePressed() {
        if (mousePressed && mouseButton == LEFT) {
            // Ball
            if (overCircle(midX, midY - 400)) {
                if (!animate) {
                    animate = true;
                    animationCounter = 0;
                    counterMulitplier = (pulled) ? counterMulitplier : round(random(2, 4));
                }
            }
            // New Game
            if (overRect(midX + 1080, 25, 120, 35)) {
                engine.newGame();
                animate = false;
                currBall = 0;
            }
            // Bingo Field
            pFieldInfo.stream().filter(field ->
                    overRect(field.get("X"), field.get("Y"), field.get("size"), field.get("size")))
                    .findFirst().ifPresent(field -> engine.pOneMarkField(field.get("index")));
        }
    }

    private void playerCard() {
        pFieldInfo.forEach(field ->
                this.pDrawField(field.get("X"), field.get("Y"), field.get("size"), field.get("index")));
    }

    private void opponentCard() {
        oFieldInfo.forEach(field ->
                this.oDrawField(field.get("X"), field.get("Y"), field.get("size"), field.get("index")));
    }

    private List<HashMap<String, Integer>> fieldList(int x, int y, int size) {
        List<HashMap<String, Integer>> fieldList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int X = x + i * (size + 2);
            for (int j = 0; j < 5; j++) {
                int Y = y + j * (size + 2);
                int index = j + (i * 5);
                fieldList.add(new HashMap<>(Map.of("X", X, "Y", Y, "size", size, "index", index)));
            }
        }
        return fieldList;
    }

    private void pDrawField(int x, int y, int size, int index) {
        Field currField = engine.pOneCard().get(index);
        String tempText = Integer.toString(currField.getValue());
        int rectColor = color(255, 252, 255);

        if (overRect(x, y, size, size) && !engine.isGameOver()) {
            rectColor = color(255, 122, 75);
        } else {
            rectColor = currField.isMarked() ? color(185, 238, 255) : rectColor;
            rectColor = currField.isWinner() ? color(4, 240, 106) : rectColor;
        }

        fill(rectColor);
        rect(x * scale, y * scale, size * scale, size * scale);
        new Text(tempText, x + size / 2, y + size / 2, 36, 0, scale).draw(super.g);
    }

    private void oDrawField(int x, int y, int size, int index) {
        Field currField = engine.pTwoCard().get(index);
        int rectColor = currField.isWinner() ? color(255, 31, 61) : color(38, 38, 38);
        String text = currField.isMarked() ? Integer.toString(currField.getValue()) : "";

        fill(rectColor);
        rect(x * scale, y * scale, size * scale, size * scale);
        new Text(text, x + size / 2, y + size / 2, 28, 250, scale).draw(super.g);
    }

    private boolean overRect(float x, float y, float width, float height) {
        return mouseX >= x * scale && mouseX <= (x + width) * scale &&
                mouseY >= y * scale && mouseY <= (y + height) * scale;
    }

    private boolean overCircle(float x, float y) {
        float disX = (x * scale) - mouseX;
        float disY = (y * scale) - mouseY;
        return sqrt(sq(disX) + sq(disY)) < ((float) 100 * scale) / 2;
    }
}
