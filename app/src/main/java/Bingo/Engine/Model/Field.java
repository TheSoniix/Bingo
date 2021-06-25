package Bingo.Engine.Model;

public class Field {
    private final int value;
    private final int index;
    private boolean marked;
    private boolean winningField;

    public Field(int value, int index, boolean marked) {
        this.value = value;
        this.index = index;
        this.marked = marked;
        this.winningField = false;
    }

    public int getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMark() {
        this.marked = true;
    }

    public void setWinningField() {
        this.winningField = true;
    }

    public boolean isWinner() {return winningField;}
}
