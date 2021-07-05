package Bingo.Engine;

import Bingo.Engine.Models.Balls;
import Bingo.Engine.Models.Card;
import Bingo.Engine.Models.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BingoImpl implements Bingo {
    private Balls balls;
    private Card pOneCard;
    private Card pTwoCard;
    boolean isPOneWinner;
    boolean gameOver;

    public BingoImpl() {
        this.newGame();
    }

    @Override
    public String toString() {
        return "Player one bingo: \n" + pOneCard.toString() + "\n" +
                "Player two bingo: \n" + pTwoCard.toString();
    }

    @Override
    public void newGame() {
        this.balls = new Balls();
        this.pOneCard = new Card();
        this.pTwoCard = new Card();
        this.gameOver = false;
    }

    @Override
    public boolean isGameOver() {
        return this.gameOver;
    }

    @Override
    public boolean isPOneWinner() {
        return this.isPOneWinner;
    }

    @Override
    public List<Field> pOneCard() {
        return pOneCard.getCard().stream().map(field ->
                new Field(field.getValue(), field.getIndex(), field.isMarked())).collect(Collectors.toList());
    }

    @Override
    public List<Field> pTwoCard() {
        return pTwoCard.getCard().stream().map(field ->
                new Field(field.getValue(), field.getIndex(), field.isMarked())).collect(Collectors.toList());
    }

    @Override
    public int drawBall() {
        assert !this.gameOver && balls.getBalls().size() > 0 : "Game over or balls are empty";
        return this.balls.drawBall();
    }

    @Override
    public List<Integer> drawnBalls() {
        return List.copyOf(this.balls.getDrawnBalls());
    }

    @Override
    public List<Integer> notDrawnBalls() {
        return List.copyOf(this.balls.getBalls());
    }

    @Override
    public void pOneMarkField(int index) {
        assert !this.gameOver && index >= 0 && index <= 25 : "Game over or wrong index!";
        this.markField(pOneCard, index);
        this.isPOneWinner = this.checkCard(pOneCard);
    }

    @Override
    public void pTwoMarkField(int index) {
        assert !this.gameOver && index >= 0 && index <= 25 : "Game over or wrong index!";
        this.markField(pTwoCard, index);
        this.isPOneWinner = !this.checkCard(pTwoCard);
    }

    private void markField(Card card, int index) {
        Field currField = card.getCard().get(index);
        if (this.balls.getDrawnBalls().contains(currField.getValue())) {
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








