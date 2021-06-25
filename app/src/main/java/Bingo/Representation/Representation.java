package Bingo.Representation;

import Bingo.Engine.Engine;
import Bingo.Engine.EngineImpl;
import Bingo.Representation.Model.Ball;
import Bingo.Representation.Model.OpponentCard;
import Bingo.Representation.Model.PlayerCard;
import processing.core.PApplet;


public class Representation extends PApplet {

    public static void run(String[] args) {
        PApplet.runSketch(new String[]{""}, new Representation());
    }

    // Engine of the Game
    Engine engine = new EngineImpl();

    // Reps of the classes
    PlayerCard playerCard = new PlayerCard();
    OpponentCard opponentCard = new OpponentCard();
    Ball ball = new Ball();


    // Colors
    int black = 0;
    int grey = color(47, 47, 47);
    int green = color(79, 157, 69);
    int blue = color(214, 238, 255);
    int white = color(255, 252, 255);

    //  Size
    float midX, midY, scale;

    // Ball
    int currBall;

    // Counters
    int counter = 0;
    boolean first = false;


    public void settings() {
        // size(1280, 720);
        fullScreen();

        //  Size
        midX = (float) displayWidth / 2;
        midY = (float) displayHeight / 2;
        scale = (float) ((displayWidth + displayHeight) / (2560 + 1440));
    }


    public void setup() {
    }

    public void draw() {
        counter++;
        background(grey);

        textAlign(CENTER);

        // Überschrift
        fill(white);
        textSize(200 * scale);
        text("BINGO", midX, 100 * scale + (float) (textAscent() * 0.8 / 2));

        // ESC
        fill(white);
        textSize(20 * scale);
        text("[ESC] = Exit", 100, 25 * scale + (35 * scale / 2) + (float) (textAscent() * 0.8 / 2));

        // Draw counter
        fill(white);
        textSize(20 * scale);
        text("Draw counter: " + engine.getDrawnBalls().size(),
                90 * scale, displayHeight - (100 * scale) + (float) (textAscent() * 0.8 / 2) );



        for (int i = 0; i < engine.getDrawnBalls().size(); i++) {
            this.ball.drawBall(super.g, (20 + 30 * i) * scale, displayHeight - 60 * scale, 15 * scale,
                    25 * scale, black, white, engine.getDrawnBalls().get(i));
        }


        // New GAme Button
        newGame(displayWidth - 200 * scale, 25 * scale, 120 * scale, 35 * scale, 20 * scale);


        // Aktuelle Kugel
        drawBall(midX, midY - 400 * scale, 100 * scale, 32 * scale);




        // Überschrift Gegner Karte
        textSize(32 * scale);
        text("Opponent", midX + 800 * scale, midY - 300 * scale);


        // Bingo Cards
        playerCard.drawBingoCard(super.g, midX, midY - 200 * scale, 150 * scale, 2, 36 * scale, engine);
        opponentCard.drawBingoCard(super.g, midX + 800 * scale, midY - 200 * scale, 100 * scale, 2, 28 * scale, engine);


        // Automatische Markierung CPU
        if (first) {
            if (counter == 60) {
                engine.autoMarkCpuCard();
                counter = 0;
            }
        }


        engine.isGameOver();
    }



    private void newGame(float x, float y, float width, float height, float textSize) {
        boolean hover = overRect(x, y, width, height);

        // Rect
        fill(hover ? blue : 0);
        noStroke();
        rect(x, y, width, height, 10);

        // Text
        fill(hover ? 0 : white);
        textSize(textSize);
        text("New Game", x + (width / 2), y + (height / 2) + (float) (textAscent() * 0.8 / 2));

    }


    private void drawBall(float x, float y, float ballSize, float textSize) {
        ball.drawBall(super.g, x, y, textSize, ballSize, white, overCircle(x, y, ballSize) ? green : black, currBall);
    }

    public void mousePressed() {
        if (mousePressed && mouseButton == LEFT) {
            if (overCircle(midX, midY - 400 * scale, 100 * scale)) {
                currBall = engine.drawBall();
                counter = 0;
                first = true;
            }

            if (overRect(2350 * scale, 10 * scale, 120 * scale, 35 * scale)) {
                engine.newGame();
                currBall = 0;
            }
        }
    }

    public void keyPressed() {
        if (keyPressed && keyCode == 32) {
            currBall = engine.drawBall();
            counter = 0;
            first = true;
        }
    }

    private boolean overRect(float x, float y, float width, float height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    private boolean overCircle(float x, float y, float diameter) {
        float disX = x - mouseX;
        float disY = y - mouseY;
        return sqrt(sq(disX) + sq(disY)) < diameter / 2;
    }

}
