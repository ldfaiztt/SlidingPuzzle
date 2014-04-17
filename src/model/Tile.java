package model;

public class Tile {
    private int value;

    public Tile(int val) {
        value = val;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        if (value != tile.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
