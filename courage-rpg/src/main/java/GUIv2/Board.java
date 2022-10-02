package GUIv2;

import GUI.Viewer;
import engineV2.Cell;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import model.cells.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 11 x 7 2d cell matrix. Used to render the GUI board.
 * A board corresponds to a map, i.e. a 2d array of cells in
 * the game engine.
 * @author Xin Lu
 */
public class Board extends Group {
    Group board = new Group();

    public Board() {
        this.getChildren().add(board);
    }

    public void updateBoard(ActionCell[][] map) {
        board.getChildren().clear();
        List<Tile> tiles = convertTiles(map);
        for (Tile tile : tiles) board.getChildren().add(tile);
    }

    public static Tile cellToTile(ActionCell cell, int row, int col) {
        Tile ret = null;
        if (cell instanceof WallCell) {
            ret = new Tile(col, row, TileType.BRICK_1);
        } else if (cell instanceof EmptyCell) {
            ret = new Tile(col, row, TileType.GROUND_1);
        } else if (cell instanceof HeroCell) {
            ret = new Tile(col, row, TileType.HERO, TileType.GROUND_1);
        } else if (cell instanceof EnemySpiderCell) {
            ret = new Tile(col, row, TileType.ENEMY_1, TileType.GROUND_1);
        } else if (cell instanceof DoorCell) {
            ret = new Tile(col, row, TileType.SPECIAL_1, TileType.GROUND_1);
        } else if (cell instanceof PortalCell) {
            ret = new Tile(col, row, TileType.STAIR_UP, TileType.GROUND_1);
        }

        return ret;
    }

    static List<Tile> convertTiles(ActionCell[][] board) {
        List<Tile> tiles = new ArrayList<>();
        for (int row = 0; row < GUI.Viewer.BOARD_Y; row ++) {
            for (int col = 0; col < GUI.Viewer.BOARD_X; col ++) {
                tiles.add(cellToTile(board[row][col], row, col));
            }
        }
        return tiles;
    }
}
