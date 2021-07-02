package Bingo.Engine;

import Bingo.Engine.Model.Field;
import java.util.List;

public interface Engine {

    String toString();

    void newGame();

    List<Field> playerCard();

    List<Field> opponentCard();

    void markFieldPlayer(int index);

    void markFieldOpponent(int index);

    int pullBall();

    boolean isGameOver();

    List<Integer> pulledBalls();

    List<Integer> notPulledBalls();

    boolean isPlayerWinner();


}
