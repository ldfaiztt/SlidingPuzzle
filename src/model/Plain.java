package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by WHPM-1031 on 4/16/2014.
 */
public class Plain implements State {
    List<List<Tile>> matrix;
    int blank_x;
    int blank_y;

    public Plain(int size, boolean random) {
        if (random) {
            InitialRandom(size);
        } else {
            InitialOrdered(size);
        }
    }

    public Plain(Plain oth) {

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
        blank_x = x;
        blank_y = y;
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
