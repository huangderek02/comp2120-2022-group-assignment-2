package GUIv2;

import model.cells.EnemyCell;
import engineV2.GameObject;
import javafx.scene.Group;
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

    public void updateBoard(ActionCell[][] map, GameObject gameObject) {
        board.getChildren().clear();
        List<Tile> tiles = convertTiles(map, gameObject);
        for (Tile tile : tiles) board.getChildren().add(tile);
    }

    public static Tile cellToTile(ActionCell cell, int row, int col, GameObject gameObject) {
        Tile ret = null;
        if (cell instanceof WallCell) {
            ret = new Tile(col, row, gameObject.getImage("wall"));
        } else if (cell instanceof EmptyCell) {
            ret = new Tile(col, row, gameObject.getImage("ground"));
        } else if (cell instanceof PortalCell portalCell) {
            ret = new Tile(col, row, gameObject.getImage((portalCell.isUp ? "stairs_up" : "stairs_down")), gameObject.getImage("ground"));
        } else if (cell instanceof EnemyCell enemyCell) {
            int hp = Math.max(10, enemyCell.hp / 10);
            ret = new Tile(col, row, gameObject.getImage("enemy" + hp), gameObject.getImage("ground"));
        }

        return ret;
    }

    static List<Tile> convertTiles(ActionCell[][] board, GameObject gameObject) {
        List<Tile> tiles = new ArrayList<>();
        for (int row = 0; row < GUI.Viewer.BOARD_Y; row ++) {
            for (int col = 0; col < GUI.Viewer.BOARD_X; col ++) {
                tiles.add(cellToTile(board[row][col], row, col, gameObject));
            }
        }
        return tiles;
    }
}
