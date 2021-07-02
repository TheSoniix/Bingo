package Bingo.Engine.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Card {
    private final List<Field> card;

    public Card() {
        this.card = this.fillCard();
    }

    public List<Field> getCard() {
        return card;
    }

    @Override
    public String toString() {
        String markedField = "X";
        ArrayList<ArrayList<String>> arrayRow = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            arrayRow.add(new ArrayList<>());
            for (int j = 0; j < 5; j++) {
                Field currField = this.card.get(i + (j * 5));
                String format = String.format("%5s", (currField.isMarked()) ? markedField : currField.getValue());
                arrayRow.get(i).add(format);
            }
        }
        return String.format("%5s", arrayRow.get(0)) + "\n" +
               String.format("%5s", arrayRow.get(1)) + "\n" +
               String.format("%5s", arrayRow.get(2)) + "\n" +
               String.format("%5s", arrayRow.get(3)) + "\n" +
               String.format("%5s", arrayRow.get(4)) + "\n";
    }

    private List<Field> fillCard() {
        List<Field> tempList = new ArrayList<>();
        Set<Integer> possibleNums = IntStream.rangeClosed(1, 75).boxed().collect(Collectors.toSet());
        int randomValue;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                do {
                    randomValue = this.randomNumber((i * 15) + 1, (i * 15) + 15);
                } while (!possibleNums.contains(randomValue));
                tempList.add(new Field(randomValue, (i * 5) + j, false));
                possibleNums.remove(randomValue);
            }
        }

        return tempList;
    }

    private int splitInCols(int index) {
        List<Integer> columns = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> IntStream.range(0, 5).forEach(j -> columns.add(15 * (i + 1))));
        return this.randomNumber(columns.get(index) - 14, columns.get(index));
    }

    private int randomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

}
