package GUI;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

/**
 * 11 x 7 2d cell matrix.
 * @author Xin Lu
 */
public class Board extends StackPane {

    List<Tile> cells = new ArrayList<>();
    Group board = new Group();

    /**
     * Generate a random board
     */
    public Board() {
        setWidth(Viewer.VIEWER_WIDTH);
        setHeight(Viewer.VIEWER_HEIGHT);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 7; j++) {
//                Tile tile = null;
//                if (i == 0 && j == 1) {
//                    tile = new Tile(i, j, TileType.HERO, TileType.GROUND_1);
//                } else {
//                    tile = new Tile(i, j, TileType.GROUND_1);
//                }
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

    //TODO add repository update image method
    // TODO add moeny update image method
    // TODO add hp, mp update image method
    // TODO add dialog update image method
}
