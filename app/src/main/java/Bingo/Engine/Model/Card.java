package Bingo.Engine.Model;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Card {
    private final List<Field> card;
    private boolean bingo;
    // Liste mit index oder value von der Sieger Linie
    // ist bingo ist noch total unnötig

    // CONSTRUCOR
    public Card() {
        this.card = this.fillCardList();
        this.bingo = false;
    }

    // GETTERS
    public List<Field> getCard() {
        return card;
    }

    public boolean isBingo() {
        return bingo;
    }

    // BUILD STRING OF THE CARD
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
        return  String.format("%5s", arrayRow.get(0)) + "\n" +
                String.format("%5s", arrayRow.get(1)) + "\n" +
                String.format("%5s", arrayRow.get(2)) + "\n" +
                String.format("%5s", arrayRow.get(3)) + "\n" +
                String.format("%5s", arrayRow.get(4)) + "\n";
    }


    // MAKING CARD
    private List<Field> fillCardList() {
        List<Field> tempList = new ArrayList<>();
        Set<Integer> numbers = IntStream.rangeClosed(1, 75).boxed().collect(Collectors.toSet());

        for (int i = 0; i < 25; i++) {
            int randomValue = splitInColumns(i);

            while (!numbers.contains(randomValue)) {
                randomValue = splitInColumns(i);
            }
            tempList.add(new Field(randomValue, i, false));
            numbers.remove(randomValue);
        }
        return tempList;
    }

    private int splitInColumns(int index) {
        List<Integer> columns = new ArrayList<>();
        IntStream.iterate(0, n -> n + 1).limit(5).forEach(i -> IntStream.range(0,5).forEach(j -> columns.add(15 * (i + 1))));
        return this.randomNumber(columns.get(index) - 14, columns.get(index));
    }

    //inclusive min and max
    private int randomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

}
