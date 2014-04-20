package model;

/**
 * User: Ding
 * Date: 4/17/2014
 * Time: 2:56 PM
 */
public class Coordinate {
    private int row;
    private int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Coordinate(Coordinate oth) {
        this.row = oth.row;
        this.col = oth.col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int ManhatonDist(Coordinate coor) {
        return Math.abs(coor.getCol() - col) + Math.abs(coor.getRow() - row);
    }
}
