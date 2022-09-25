package GUI;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * 11 x 7 2d cell matrix.
 * @author Xin Lu
 */
public class Board {

    List<Tile> cells = new ArrayList<>();
    Group board = new Group();

    /**
     * Generate a random board
     */
    public Board() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 7; j++) {
                Tile tile = Tile.getRandomTile(i, j);
                cells.add(tile);
                board.getChildren().add(tile);
            }
        }
    }

    /**
     * Generate board by given 2d cell matrix
     * @param cells the given 2d cell matrix
     */
    public Board(List<Tile> cells) {
        this.cells = cells;
        for (Tile tile : cells) {
            board.getChildren().add(tile);
        }
    }
}
