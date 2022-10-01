package GUI;

import engine.Cell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.cells.*;

import javax.swing.text.View;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xin Lu
 */
public class Tile extends StackPane {

    private int board_x;
    private int board_y;
    private TileType tileType;
    private TileType background = null;

    static List<Tile> convertTiles(Cell[][] board) {

        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < Viewer.BOARD_X; i++) {
            for (int j = 0; j < Viewer.BOARD_Y; j++) {
                if (board[i][j] instanceof WallCell) {
                    Tile tile = new Tile(i, j, TileType.BRICK_1);
                    tiles.add(tile);
                    continue;
                }
                if (board[i][j] instanceof EmptyCell) {
                    Tile tile = new Tile(i, j, TileType.GROUND_1);
                    tiles.add(tile);
                    continue;
                }
                if (board[i][j] instanceof HeroCell) {
                    Tile tile = new Tile(i, j, TileType.HERO, TileType.GROUND_1);
                    tiles.add(tile);
                    continue;
                }
                if (board[i][j] instanceof EnemySpiderCell) {
                    Tile tile = new Tile(i, j, TileType.ENEMY_1, TileType.GROUND_1);
                    tiles.add(tile);
                    continue;
                }
                if (board[i][j] instanceof DoorCell) {
                    Tile tile = new Tile(i, j, TileType.SPECIAL_1, TileType.GROUND_1);
                    tiles.add(tile);
                    continue;
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

        ImageView cellBackground = new ImageView(new File(Viewer.URI_BASE + "textures/" + tileType).toURI().toString());
        getChildren().add(cellBackground);
        cellBackground.setFitWidth(Viewer.TILE_SIZE);
        cellBackground.setFitHeight(Viewer.TILE_SIZE);
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
        ImageView stuffBackground = new ImageView(new File(Viewer.URI_BASE + "textures/" + background.toString()).toURI().toString());
        stuffBackground.setFitWidth(Viewer.TILE_SIZE);
        stuffBackground.setFitHeight(Viewer.TILE_SIZE);
        getChildren().add(stuffBackground);

        ImageView cellBackground = new ImageView(new File(Viewer.URI_BASE + "textures/" + tileType.toString()).toURI().toString());
        getChildren().add(cellBackground);
        ImageView cellForeground = new ImageView(new File(Viewer.URI_BASE + "textures/" + tileType).toURI().toString());
        getChildren().add(cellForeground);

        cellForeground.setFitWidth(Viewer.TILE_SIZE);
        cellForeground.setFitHeight(Viewer.TILE_SIZE);
        cellBackground.setFitWidth(Viewer.TILE_SIZE);
        cellBackground.setFitHeight(Viewer.TILE_SIZE);
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
        return (int) (Viewer.BOARD_X_OFFSET + Math.ceil(x_coordinate * Viewer.TILE_SIZE));
    }

    /**
     * Calculate the y absolute coordinate in pixels on the board
     * @return y absolute coordinate in pixels on the board
     */
    static public int getPixel_y(int y_coordinate) {
        return (int) (Viewer.BOARD_Y_OFFSET + Math.ceil(y_coordinate * Viewer.TILE_SIZE));
    }
}
