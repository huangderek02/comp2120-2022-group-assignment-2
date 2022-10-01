package model;

/**
 * This represents the character's location
 *
 * @author Xianghao Wang
 * */
public class Location {
    /**
     * The map's level
     * */
    public int level;

    /**
     * The row
     * */
    public int row;

    /**
     * The column
     * */
    public int col;

    /**
     * Construct a location
     *
     * @author Xianghao Wang
     *
     * @param level is the level of the game
     * @param row is the row in the map
     * @param col is the column in the map
     * */
    public Location(int level, int row, int col) {
        this.row = row;
        this.col = col;
        this.level = level;
    }
}
