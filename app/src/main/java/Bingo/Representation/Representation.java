package Bingo.Representation;

import Bingo.Engine.Engine;
import Bingo.Engine.EngineImpl;
import Bingo.Representation.Model.Ball;
import Bingo.Representation.Model.OpponentCard;
import Bingo.Representation.Model.PlayerCard;
import Bingo.Representation.Model.Text;
import Bingo.Representation.Utils.Hover;
import processing.core.PApplet;

import java.util.stream.IntStream;


public class Representation extends PApplet {

    public static void run(String[] args) {
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

    // Reps of the classes
    PlayerCard playerCard = new PlayerCard();
    OpponentCard opponentCard = new OpponentCard();


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
        this.newGame();



        // Bingo Cards
        playerCard.drawBingoCard(super.g, engine, midX, midY - 200, 150, 2,
                36, scale);
        opponentCard.drawBingoCard(super.g, engine, midX + 850, midY - 200, 100,
                2, 28, scale);


        if (!engine.isGameOver()) {

            this.eineKugelzumZiehen();
            this.kugelAnimation();

        } else {
            // Überschrift für Sieger
            String winner = engine.isPlayerWinner() ? "Game Over! You won!" : "Game Over! Opponent won!";
            Text.draw(super.g, winner, midX, midY - 350, 50, white, scale);
        }

        engine.isGameOver();
    }


    void eineKugelzumZiehen() {
        String tempText;
        if (!animate) {

            if (Hover.circle(super.g, midX * scale, (midY - 400) * scale, 100 * scale)) {
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
    private void newGame() {
        float x = (midX + 1080) * scale;
        float y = 25 * scale;
        float width = 120 * scale;
        float height = 35 * scale;
        boolean hover = Hover.rect(super.g, x, y, width, height);

        // Rect
        fill(hover ? color(214, 238, 255) : grey);
        rect(x, y, width, height, 10 * scale);

        // Text
        Text.draw(super.g, "New Game", x, y, 20 * scale, hover ? grey : white, 1);
    }

    public void mousePressed() {
        if (mousePressed && mouseButton == LEFT) {
            if (Hover.circle(super.g, midX * scale, (midY - 400) * scale, 100 * scale)) {
                if (!animate) {
                    animate = true;
                    frameCounterBallAnimation = 0;
                    random = (drawn) ? random : round(random(1, 3));

                }
            }

            if (Hover.rect(super.g, width - 200 * scale, 25 * scale, 120 * scale,
                    35 * scale)) {
                engine.newGame();
                animate = false;
                currBall = 0;
            }
        }
    }


}
