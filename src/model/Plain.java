package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Plain implements State {
    List<List<Tile>> matrix;
    int blank_row;
    int blank_col;

    public Plain(int size, boolean random) {
        if (random) {
            InitialRandom(size);
        } else {
            InitialOrdered(size);
        }
    }

    public Plain(Plain oth) {
        blank_row = oth.blank_row;
        blank_col = oth.blank_col;

        matrix = new LinkedList<List<Tile>>();

        for (List<Tile> row : oth.matrix) {
            matrix.add(new ArrayList<Tile>(row));
        }
    }

    private List<Integer> Shuffle(int size) {
        List<Integer> random = new ArrayList<Integer>(size * size);
        for (int i = 0; i < random.size(); i++) {
            random.set(i, i);
        }

        Collections.shuffle(random);

        return random;
    }

    private void UpdateBlankPos(int x, int y) {
        blank_row = x;
        blank_col = y;
    }

    private void InitialRandom(int size) {
        matrix = new ArrayList<List<Tile>>();
        List<Integer> random = Shuffle(size);

        for (int i = 0; i < size; i++) {
            List<Tile> row = new ArrayList<Tile>();
            for (int j = 0; j < size; j++) {
                Tile item = new Tile(random.get(i * size + j));
                if (item.isEmpty()) {
                    UpdateBlankPos(i, j);
                }
                row.add(item);
            }
            matrix.add(row);
        }
    }

    private void InitialOrdered(int size) {
        matrix = new ArrayList<List<Tile>>(size);

        for (int i = 0; i < size; i++) {
            List<Tile> row = new ArrayList<Tile>();
            for (int j = 0; j < size; j++) {
                Tile item = new Tile(i * size + j);
                if (item.isEmpty()) {
                    UpdateBlankPos(i, j);
                }
                row.add(item);
            }
            matrix.add(row);
        }
    }

    private boolean CheckRange(int row, int col) {
        if (row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(0).size()) {
            return true;
        }

        return false;
    }

    public boolean Swap(int r1, int c1, int r2, int c2) {
        if (!CheckRange(r1, c1) || !CheckRange(r2, c2)) {
            return false;
        }

        Tile temp = matrix.get(r1).get(c1);
        matrix.get(r1).set(c1, matrix.get(r2).get(c2));
        matrix.get(r2).set(c2, temp);

        return true;
    }

    public boolean BlankDown() {
        return Swap(blank_row, blank_col, blank_row + 1, blank_col);
    }

    public boolean BlankUp() {
        return Swap(blank_row, blank_col, blank_row - 1, blank_col);
    }

    public boolean BlankLeft() {
        return Swap(blank_row, blank_col, blank_row, blank_col - 1);
    }

    public boolean BlankRight() {
        return Swap(blank_row, blank_col, blank_row, blank_col + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plain)) return false;

        Plain plain = (Plain) o;

        if (!matrix.equals(plain.matrix)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return matrix.hashCode();
    }
}
