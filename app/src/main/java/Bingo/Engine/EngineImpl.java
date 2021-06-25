package Bingo.Engine;

import Bingo.Engine.Model.Balls;
import Bingo.Engine.Model.Card;
import Bingo.Engine.Model.Field;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EngineImpl implements Engine {

    // BALLS
    private Balls balls;

    // PLAYER
    private Card playerCard;

    // CPU
    private Card cpuCard;


    // Constructor
    public EngineImpl() {
        this.balls = new Balls();
        this.playerCard = new Card();
        this.cpuCard = new Card();
    }


    @Override
    public String toString() {
        return "Your Bingo: \n" + playerCard.toString() + "\n" +
                "Cpu Bingo: \n" + cpuCard.toString();
    }

    @Override
    public void newGame() {
        this.balls = new Balls();
        this.playerCard = new Card();
        this.cpuCard = new Card();
    }

    @Override
    public boolean isGameOver() {
        return checkCard(playerCard) || checkCard(cpuCard) || balls.getDrawnBalls().size() == 75;
    }

    @Override
    public List<Integer> getPlayerCard() {
        return playerCard.getCard().stream().map(Field::getValue).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getCpuCard() {
        return cpuCard.getCard().stream().map(Field::getValue).collect(Collectors.toList());
    }


    // INDEX ÜBER 25 WIRFT EIN ERROR
    @Override
    public boolean isPlayerFieldMarked(int index) {
        return playerCard.getCard().get(index).isMarked();
    }

    // INDEX ÜBER 25 WIRFT EIN ERROR
    @Override
    public boolean isPlayerFieldWinner(int index) {
        return playerCard.getCard().get(index).isWinner();
    }

    // INDEX ÜBER 25 WIRFT EIN ERROR
    @Override
    public boolean isCpuFieldMarked(int index) {
        return cpuCard.getCard().get(index).isMarked();
    }

    // INDEX ÜBER 25 WIRFT EIN ERROR
    @Override
    public boolean isCpuFieldWinner(int index) {
        return cpuCard.getCard().get(index).isWinner();
    }


    @Override
    public List<Integer> getDrawnBalls() {
        return this.balls.getDrawnBalls();
    }


    @Override
    public void markPlayerCard(int index) {
        if (index <= 25) {this.markField(playerCard.getCard().get(index));}
    }

    @Override
    public void autoMarkCpuCard() {
        cpuCard.getCard().stream()
                .filter(field -> balls.getDrawnBalls().contains(field.getValue()))
                .forEach(Field::setMark);
    }

    @Override
    public int drawBall() {
        return this.balls.drawBall();
    }

    private void markField(Field field) {
        if (this.balls.getDrawnBalls().contains(field.getValue())) {
            field.setMark();
        }
    }


    // CHECK IF BINGO
    private boolean checkCard(Card card) {
        boolean winner = false;
        List<Integer> tempMarkedList = card.getCard().stream().filter(Field::isMarked).map(Field::getIndex).toList();
        for (List<Integer> list : getWinningConditions()) {
            if (tempMarkedList.containsAll(list)) {
                winner = true;
                list.forEach(index -> card.getCard().get(index).setWinningField());
            }
        }

        return winner;

//        return getWinningConditions().stream().anyMatch(tempMarkedList::containsAll);
    }


    private List<List<Integer>> getWinningConditions() {
        List<List<Integer>> winningConditions = new ArrayList<>();
        IntStream.range(0, 12).forEach(i -> winningConditions.add(new ArrayList<>()));

        //normal iterieren vertikal gerecht
        IntStream.range(0, 5).forEach(i -> IntStream.range(0, 5).forEach(j -> winningConditions.get(i).add(j + (i * 5))));

        // + 5 iterieren horizontal
        IntStream.range(0, 5).forEach(i -> IntStream.range(0, 5).forEach(j -> winningConditions.get(i + 5).add((j * 5) + i)));

        // Diagonal runter
        winningConditions.get(10).addAll(List.of(0, 6, 12, 18, 24));

        // Diagonal hoch
        winningConditions.get(11).addAll(List.of(4, 8, 12, 16, 20));

        return winningConditions;
    }


}








