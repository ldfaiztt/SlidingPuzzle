package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Plain implements State {
    List<List<Tile>> matrix;
    Coordinate coorBlank;

    public Plain(int size, boolean random) {
        if (random) {
            InitialRandom(size);
        } else {
            InitialOrdered(size);
        }
    }

    public Plain(Plain oth) {
        coorBlank = new Coordinate(oth.coorBlank);

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

    private void UpdateBlankPos(int row, int col) {
        coorBlank = new Coordinate(row, col);
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

    private boolean CheckRange(Coordinate coor) {
        int row = coor.getRow();
        int col = coor.getCol();

        return row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(0).size();

    }

    public Tile getTile(Coordinate c) {
        return matrix.get(c.getRow()).get(c.getCol());
    }

    public void setTile(Coordinate c, Tile val) {
        matrix.get(c.getRow()).set(c.getCol(), val);
    }

    public boolean Swap(Coordinate c1, Coordinate c2) {
        if (!CheckRange(c1) || !CheckRange(c2)) {
            return false;
        }

        Tile temp = getTile(c1);
        setTile(c1, getTile(c2));
        setTile(c2, temp);

        return true;
    }

    public boolean BlankDown() {
        return Swap(coorBlank, new Coordinate(coorBlank.getRow() + 1, coorBlank.getCol()));
    }

    public boolean BlankUp() {
        return Swap(coorBlank, new Coordinate(coorBlank.getRow() - 1, coorBlank.getCol()));
    }

    public boolean BlankLeft() {
        return Swap(coorBlank, new Coordinate(coorBlank.getRow(), coorBlank.getCol() - 1));
    }

    public boolean BlankRight() {
        return Swap(coorBlank, new Coordinate(coorBlank.getRow(), coorBlank.getCol() + 1));
    }

    public int CountDiff(Plain oth) {
        if (matrix.size() != oth.matrix.size()) {
            return -1;
        }

        if (matrix.size() > 0 && oth.matrix.size() > 0 && matrix.get(0).size() != oth.matrix.size()) {
            return -1;
        }

        int count = 0;
        for (int i = 0; i < matrix.size(); i++) {
            List<Tile> row = matrix.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (!row.get(j).isEmpty() && !row.get(j).equals(oth.matrix.get(i).get(j))) {
                    count++;
                }
            }
        }

        return count;
    }

    private int FindDist(Integer val, Coordinate coor) {
        for (int i = 0; i < matrix.size(); i++) {
            List<Tile> row = matrix.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j).getValue() == val) {
                    return coor.ManhatonDist(new Coordinate(i, j));
                }
            }
        }

        return 0;
    }

    public int CountDist(Plain oth) {
        if (matrix.size() != oth.matrix.size()) {
            return -1;
        }

        if (matrix.size() > 0 && oth.matrix.size() > 0 && matrix.get(0).size() != oth.matrix.size()) {
            return -1;
        }

        int sum = 0;
        for (int i = 0; i < oth.matrix.size(); i++) {
            List<Tile> row = oth.matrix.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (!row.get(j).isEmpty()) {
                    Coordinate coor = new Coordinate(i, j);
                    sum += FindDist(row.get(j).getValue(), coor);
                }
            }
        }

        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plain)) return false;

        Plain plain = (Plain) o;

        return matrix.equals(plain.matrix);

    }

    @Override
    public int hashCode() {
        return matrix.hashCode();
    }
}
