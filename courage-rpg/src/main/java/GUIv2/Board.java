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
 *
 * @author Xin Lu
 * @author Xianghao Wang
 */
public class Board extends Group {
    Group board = new Group();

    public Board() {
        this.getChildren().add(board);
    }

    /**
     * Update the whole board
     *
     * @author Xiangaho Wang
     * @author Xin Lu
     *
     * @param map is the map for updating the board
     * @param gameObject is the game object
     * */
    public void updateBoard(ActionCell[][] map, GameObject gameObject) {
        board.getChildren().clear();
        List<Tile> tiles = convertTiles(map, gameObject);
        for (Tile tile : tiles) board.getChildren().add(tile);
    }

    /**
     * Convert game engine object Cell to GUI object Tile.
     *
     * @author Xianghao Wang
     * @author Xin Lu
     *
     * @param cell the given cell
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @param gameObject the game engine object
     * @return a tile corresponding to the given cell
     */
    public static Tile cellToTile(ActionCell cell, int row, int col, GameObject gameObject) {
        Tile ret = null;
        if (cell instanceof WallCell) {
            ret = new Tile(col, row, gameObject.getImage("wall"));
        } else if (cell instanceof EmptyCell) {
            ret = new Tile(col, row, gameObject.getImage("ground"));
        } else if (cell instanceof PortalCell portalCell) {
            ret = new Tile(col, row, gameObject.getImage((portalCell.isUp ? "stairs_up" : "stairs_down")), gameObject.getImage("ground"));
        } else if (cell instanceof EnemyCell enemyCell) {
            int hp = Math.min(10, enemyCell.hp / 10 + 1);
            ret = new Tile(col, row, gameObject.getImage("enemy" + hp), gameObject.getImage("ground"));
        } else if (cell instanceof WaterCell) {
            ret = new Tile(col, row, gameObject.getImage("water"), gameObject.getImage("ground"));
        } else if (cell instanceof KeyCell) {
            ret = new Tile(col, row, gameObject.getImage("key"), gameObject.getImage("ground"));
        } else if (cell instanceof DoorCell) {
            ret = new Tile(col, row, gameObject.getImage("door"), gameObject.getImage("ground"));
        } else if (cell instanceof NPCCell npcCell) {
            String name = "npc" + npcCell.id;
            ret = new Tile(col, row, gameObject.getImage(name), gameObject.getImage("ground"));
        } else if (cell instanceof MerchantCell merchantCell) {
            ret = new Tile(col, row, gameObject.getImage("merchant"), gameObject.getImage("ground"));
        } else if (cell instanceof VictoryCell) {
            ret = new Tile(col, row, gameObject.getImage("princess"), gameObject.getImage("ground"));
        } else if (cell instanceof MoneyCell) {
            ret = new Tile(col, row, gameObject.getImage("money"), gameObject.getImage("ground"));
        }

        return ret;
    }

    /**
     * Convert a list of game engine object Cells to a list of GUI object Tiles.
     *
     * @author Xianghao Wang
     * @author Xin Lu
     *
     * @param board the given 2d cell matrix
     * @param gameObject the game engine object
     * @return a list of tiles corresponding to the given cell matrix
     */
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
