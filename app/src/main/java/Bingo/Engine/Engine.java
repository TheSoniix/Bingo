package Bingo.Engine;

import java.util.List;

public interface Engine {

    String toString();

    void newGame();

    List<Integer> getPlayerCard();

    List<Integer> getCpuCard();

    boolean isPlayerFieldMarked(int index);

    boolean isPlayerFieldWinner(int index);

    boolean isCpuFieldMarked(int index);

    boolean isCpuFieldWinner(int index);

    void markPlayerCard(int index);

    void autoMarkCpuCard();

    int drawBall();

    boolean isGameOver();

    List<Integer> getDrawnBalls();



}
