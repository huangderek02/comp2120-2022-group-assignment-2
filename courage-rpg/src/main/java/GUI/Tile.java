package GUI;

import GUIv2.Layout;
import GUIv2.TileType;
import engineV2.Cell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.cells.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tile in the GUI board, which corresponds to a cell
 * in the game engine. Cells should be converted to tiles before
 * being used in the GUI.
 * @author Xin Lu
 */
public class Tile extends StackPane {

    private int board_x;
    private int board_y;
    private TileType tileType;
    private TileType background = null;

    /**
     * Create a list of tiles by the given cell matrix.
     * @param board the given cell matrix
     * @return a list of tiles
     */
    static List<Tile> convertTiles(Cell[][] board) {

        List<Tile> tiles = new ArrayList<>();
        for (int row = 0; row < GUI.Viewer.BOARD_Y; row ++) {
            for (int col = 0; col < GUI.Viewer.BOARD_X; col ++) {
                if (board[row][col] instanceof WallCell) {
                    Tile tile = new Tile(col, row, TileType.BRICK_1);
                    tiles.add(tile);
                } else if (board[row][col] instanceof EmptyCell) {
                    Tile tile = new Tile(col, row, TileType.GROUND_1);
                    tiles.add(tile);
                } else if (board[row][col] instanceof DoorCell) {
                    Tile tile = new Tile(col, row, TileType.SPECIAL_1, TileType.GROUND_1);
                    tiles.add(tile);
                } else if (board[row][col] instanceof PortalCell) {
                    Tile tile = new Tile(col, row, TileType.STAIR_UP, TileType.GROUND_1);
                    tiles.add(tile);
                }
            }
        }
        return tiles;
    }

    /**
     * Generate a random type of cell at the given location. (0-10, 0-6)
     * @param x the x coordinate of the cell, from 0 to 10
     * @param y the y coordinate of the cell, from 0 to 6
     * @return a random type of cell at the given location
     */
    static Tile getRandomTile(int x, int y) {
        Tile rtn;
        TileType tileType = TileType.getRandomTileType();
        if (TileType.isTexture(tileType)) {
            rtn = new Tile(x, y, tileType);
        } else {
            rtn = new Tile(x, y, tileType, TileType.getRandomBackground());
        }
        return rtn;
    }

    public TileType getType() {
        return tileType;
    }

    /**
     * For single background tile only
     */
    public Tile(int x, int y, TileType tileType) {
        this.board_x = getPixel_x(x);
        this.board_y = getPixel_y(y);
        this.tileType = tileType;

        ImageView cellBackground = new ImageView(new File(GUI.Viewer.URI_BASE + "textures/" + tileType).toURI().toString());
        getChildren().add(cellBackground);
        cellBackground.setFitWidth(GUI.Viewer.TILE_SIZE);
        cellBackground.setFitHeight(GUI.Viewer.TILE_SIZE);
        setLayoutX(board_x);
        setLayoutY(board_y);
    }

    /**
     * For multiple layers tile
     */
    public Tile(int x, int y, TileType tileType, TileType background) {
        this.board_x = getPixel_x(x);
        this.board_y = getPixel_y(y);
        this.background = background;
        ImageView stuffBackground = new ImageView(new File(GUI.Viewer.URI_BASE + "textures/" + background.toString()).toURI().toString());
        stuffBackground.setFitWidth(GUI.Viewer.TILE_SIZE);
        stuffBackground.setFitHeight(GUI.Viewer.TILE_SIZE);
        getChildren().add(stuffBackground);

        ImageView cellBackground = new ImageView(new File(GUI.Viewer.URI_BASE + "textures/" + tileType.toString()).toURI().toString());
        getChildren().add(cellBackground);
        ImageView cellForeground = new ImageView(new File(GUI.Viewer.URI_BASE + "textures/" + tileType).toURI().toString());
        getChildren().add(cellForeground);

        cellForeground.setFitWidth(GUI.Viewer.TILE_SIZE);
        cellForeground.setFitHeight(GUI.Viewer.TILE_SIZE);
        cellBackground.setFitWidth(GUI.Viewer.TILE_SIZE);
        cellBackground.setFitHeight(GUI.Viewer.TILE_SIZE);
        setLayoutX(board_x);
        setLayoutY(board_y);
    }

    public void setBackground(TileType background) {
        this.background = background;
    }

    /**
     * Calculate the x absolute coordinate in pixels on the board
     * @return x absolute coordinate in pixels on the board
     */
    static public int getPixel_x(int x_coordinate) {
        return (int) (GUI.Viewer.BOARD_X_OFFSET + Math.ceil(x_coordinate * GUI.Viewer.TILE_SIZE));
    }

    /**
     * Calculate the y absolute coordinate in pixels on the board
     * @return y absolute coordinate in pixels on the board
     */
    static public int getPixel_y(int y_coordinate) {
        return (int) (GUI.Viewer.BOARD_Y_OFFSET + Math.ceil(y_coordinate * Layout.TILE_SIZE));
    }
}
