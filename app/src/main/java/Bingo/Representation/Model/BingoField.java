package Bingo.Representation.Model;

public class BingoField {
    private final float x, y, size;
    private final int index;

    public BingoField(float x, float y, float size, int index) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.index = index;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }

    public int getIndex() {
        return index;
    }
}
