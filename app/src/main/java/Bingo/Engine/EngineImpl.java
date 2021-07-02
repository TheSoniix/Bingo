package Bingo.Engine;

import Bingo.Engine.Model.Balls;
import Bingo.Engine.Model.Card;
import Bingo.Engine.Model.Field;
import java.util.*;
import java.util.stream.IntStream;

public class EngineImpl implements Engine {

    private Balls balls;

    private Card playerCard;
    private Card opponentCard;

    boolean isPlayerWinner;
    boolean gameOver;

    public EngineImpl() {
        this.newGame();
    }

    @Override
    public String toString() {
        return "Your Bingo: \n" + playerCard.toString() + "\n" +
               "Cpu Bingo: \n" + opponentCard.toString();
    }

    @Override
    public void newGame() {
        this.balls = new Balls();
        this.playerCard = new Card();
        this.opponentCard = new Card();
        this.gameOver = false;
    }

    @Override
    public boolean isGameOver() {
        return this.gameOver;
    }

    @Override
    public boolean isPlayerWinner() {
        return this.isPlayerWinner;
    }

    @Override
    public List<Field> playerCard() {
        return List.copyOf(playerCard.getCard());
    }

    @Override
    public List<Field> opponentCard() {
        return List.copyOf(opponentCard.getCard());
    }

    @Override
    public int pullBall() {
        assert !this.gameOver && balls.getBalls().size() > 0 : "Game over or balls are empty";
        return this.balls.pullBall();
    }

    @Override
    public List<Integer> pulledBalls() {
        return List.copyOf(this.balls.getPulledBalls());
    }

    @Override
    public List<Integer> notPulledBalls() {
        return List.copyOf(this.balls.getBalls());
    }


    @Override
    public void markFieldPlayer(int index) {
        assert !this.gameOver && index >= 0 && index <= 25 : "Game over or wrong index!";
        this.markField(playerCard, index);
        this.isPlayerWinner = this.checkCard(playerCard);
    }

    @Override
    public void markFieldOpponent(int index) {
        assert !this.gameOver && index >= 0 && index <= 25 : "Game over or wrong index!";
        this.markField(opponentCard, index);
        this.isPlayerWinner = !this.checkCard(opponentCard);
    }

    private void markField(Card card, int index) {
        Field currField = card.getCard().get(index);
        if (this.balls.getPulledBalls().contains(currField.getValue())) {
            currField.setMark();
        }
    }

    private boolean checkCard(Card card) {
        List<Integer> tempList;
        tempList = card.getCard().stream().filter(Field::isMarked).map(Field::getIndex).toList();
        for (List<Integer> list : getWinConditions()) {
            if (tempList.containsAll(list)) {
                this.gameOver = true;
                list.forEach(index -> card.getCard().get(index).setWinningField());
            }
        }
        return this.gameOver;
    }

    private List<List<Integer>> getWinConditions() {
        List<List<Integer>> winConditions = new ArrayList<>();

        IntStream.range(0, 12).forEach(i -> winConditions.add(new ArrayList<>()));

        IntStream.range(0, 5).forEach(i ->
                IntStream.range(0, 5).forEach(j -> winConditions.get(i).add(j + (i * 5))));

        IntStream.range(0, 5).forEach(i ->
                IntStream.range(0, 5).forEach(j -> winConditions.get(i + 5).add((j * 5) + i)));

        winConditions.get(10).addAll(List.of(0, 6, 12, 18, 24));
        winConditions.get(11).addAll(List.of(4, 8, 12, 16, 20));

        return winConditions;
    }

}








