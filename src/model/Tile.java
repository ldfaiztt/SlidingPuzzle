package model;

public class Tile {
    private int value;

    public Tile(int val) {
        value = val;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        return value == tile.value;

    }

    @Override
    public int hashCode() {
        return value;
    }
}
