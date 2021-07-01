package Bingo.Engine;

import Bingo.Engine.Model.Balls;
import Bingo.Engine.Model.Card;
import Bingo.Engine.Model.Field;

import java.util.*;
import java.util.stream.IntStream;

public class EngineImpl implements Engine {

    // BALLS
    private Balls balls;

    // PLAYER
    private Card playerCard;

    // CPU
    private Card cpuCard;

    boolean isPlayerWinner;
    boolean gameOver;


    // Constructor
    public EngineImpl() {
        this.balls = new Balls();
        this.playerCard = new Card();
        this.cpuCard = new Card();
        this.gameOver = false;
    }


    @Override
    public String toString() {
        return "Your Bingo: \n" + playerCard.toString() + "\n" + "Cpu Bingo: \n" + cpuCard.toString();
    }

    @Override
    public void newGame() {
        this.balls = new Balls();
        this.playerCard = new Card();
        this.cpuCard = new Card();
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

    //Immutable?? GUI könnte Field verändern, doch dadurch wird die Engine nicht beeinträchtigt
    @Override
    public List<Field> getPlayerCard() {
        return List.copyOf(playerCard.getCard());
    }

    //Immutable??
    @Override
    public List<Field> getCpuCard() {
        return List.copyOf(cpuCard.getCard());
    }

    @Override
    public int drawBall() {
        assert !this.gameOver || balls.getBalls().size() > 0;
        return this.balls.drawBall();
    }

    @Override
    public List<Integer> getDrawnBalls() {
        return List.copyOf(this.balls.getDrawnBalls());
    }

    @Override
    public List<Integer> getNotDrawnBalls() {
        return List.copyOf(this.balls.getBalls());
    }


    @Override
    public void markPlayerCard(int index) {
        assert !this.gameOver || index <= 25;
        this.markField(playerCard.getCard().get(index));
        if (this.checkCard(playerCard)) {
            this.isPlayerWinner = true;
        }
    }

    @Override
    public void autoMarkCpuCard() {
        assert !this.gameOver;
        cpuCard.getCard().stream()
                .filter(field -> balls.getDrawnBalls().contains(field.getValue())).forEach(Field::setMark);
        if (this.checkCard(cpuCard)) {
            this.isPlayerWinner = false;
        }
    }


    private void markField(Field field) {
        if (this.balls.getDrawnBalls().contains(field.getValue())) {
            field.setMark();
        }
    }


    // CHECK IF BINGO
    private boolean checkCard(Card card) {
        List<Integer> tempMarkedList = card.getCard().stream().filter(Field::isMarked).map(Field::getIndex).toList();
        for (List<Integer> list : getWinConditions()) {
            if (tempMarkedList.containsAll(list)) {
                this.gameOver = true;
                list.forEach(index -> card.getCard().get(index).setWinningField());
            }
        }
        return this.gameOver;
    }


    private List<List<Integer>> getWinConditions() {
        List<List<Integer>> winConditions = new ArrayList<>();
        IntStream.range(0, 12).forEach(i -> winConditions.add(new ArrayList<>()));

        // SENKRECHT
        IntStream.range(0, 5).forEach(i -> IntStream.range(0, 5).forEach(j -> winConditions.get(i).add(j + (i * 5))));

        // Waagerecht
        IntStream.range(0, 5).forEach(i -> IntStream.range(0, 5).forEach(j -> winConditions.get(i + 5).add((j * 5) + i)));

        winConditions.get(10).addAll(List.of(0, 6, 12, 18, 24));

        winConditions.get(11).addAll(List.of(4, 8, 12, 16, 20));

        return winConditions;
    }

}








