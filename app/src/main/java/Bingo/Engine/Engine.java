package Bingo.Engine;

import Bingo.Engine.Model.Field;

import java.util.List;

public interface Engine {

    String toString();

    void newGame();

    List<Field> getPlayerCard();

    List<Field> getCpuCard();

    void markPlayerCard(int index);

    void autoMarkCpuCard();

    int drawBall();

    boolean isGameOver();

    List<Integer> getDrawnBalls();

    List<Integer> getNotDrawnBalls();

    boolean isPlayerWinner();


}
