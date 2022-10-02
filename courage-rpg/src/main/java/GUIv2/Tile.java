package GUIv2;

import GUIv2.TileType;
import GUIv2.Viewer;
import engineV2.Cell;
import javafx.scene.image.Image;
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


    public TileType getType() {
        return tileType;
    }

    /**
     * For single background tile only
     */
    public Tile(int x, int y, Image image) {
        this.board_x = getPixel_x(x);
        this.board_y = getPixel_y(y);
        this.tileType = tileType;

        ImageView cellBackground = new ImageView(image);
        getChildren().add(cellBackground);
        cellBackground.setFitWidth(GUI.Viewer.TILE_SIZE);
        cellBackground.setFitHeight(GUI.Viewer.TILE_SIZE);
        setLayoutX(board_x);
        setLayoutY(board_y);
    }

    /**
     * For multiple layers tile
     */
    public Tile(int x, int y, Image tileImage, Image backgroundImage) {
        this.board_x = getPixel_x(x);
        this.board_y = getPixel_y(y);
        ImageView stuffBackground = new ImageView(backgroundImage);
        stuffBackground.setFitWidth(GUI.Viewer.TILE_SIZE);
        stuffBackground.setFitHeight(GUI.Viewer.TILE_SIZE);
        getChildren().add(stuffBackground);

        ImageView cellBackground = new ImageView(tileImage);
        getChildren().add(cellBackground);
        ImageView cellForeground = new ImageView(tileImage);
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
