package GUI;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.File;

/**
 * @author Xin Lu
 */
public class Tile extends StackPane {

    private int board_x;
    private int board_y;
    private TileType tileType;
    private TileType background = null;

    /**
     * Generate a random type of cell at the given location. (0-10, 0-6)
     * @param x the x coordinate of the cell, from 0 to 10
     * @param y the y coordinate of the cell, from 0 to 6
     * @return a random type of cell at the given location
     */
    static Tile getRandomTile(int x, int y) {
        return new Tile(x, y, TileType.getRandomTileType());
    }

    public TileType getType() {
        return tileType;
    }

    /**
     * For single background tile
     */
    public Tile(int x, int y, TileType tileType) {
        this.board_x = getPixel_x(x);
        this.board_y = getPixel_y(y);
        this.tileType = tileType;

        System.out.println(new File("courage-rpg/src/main/resources/textures/" + tileType.toString()).toURI());
        ImageView cellBackground = new ImageView(new File("courage-rpg/src/main/resources/textures/" + tileType).toURI().toString());
        getChildren().add(cellBackground);
//        if (!TileType.isTexture(tileType)) {
//            ImageView cellForeground = new ImageView(new File("courage-rpg/src/main/resources/textures/" + tileType.toString()).toURI().toString());
//            getChildren().add(cellForeground);
//            cellForeground.setFitWidth(Viewer.TILE_SIZE);
//            cellForeground.setFitHeight(Viewer.TILE_SIZE);
//        }
        cellBackground.setFitWidth(Viewer.TILE_SIZE);
        cellBackground.setFitHeight(Viewer.TILE_SIZE);
        setLayoutX(board_x);
        setLayoutY(board_y);
    }

    /**
     * For multiple layers tile
     */
    public Tile(int x, int y, TileType tileType, TileType background) {
        this.background = background;
        ImageView stuffBackground = new ImageView(new File("courage-rpg/src/main/resources/textures/" + background.toString()).toURI().toString());
        stuffBackground.setFitWidth(Viewer.TILE_SIZE);
        stuffBackground.setFitHeight(Viewer.TILE_SIZE);
        getChildren().add(stuffBackground);

        ImageView cellBackground = new ImageView(new File("courage-rpg/src/main/resources/textures/" + tileType.toString()).toURI().toString());
        getChildren().add(cellBackground);
        ImageView cellForeground = new ImageView(new File("courage-rpg/src/main/resources/textures/" + tileType).toURI().toString());
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
        return Viewer.BOARD_X_OFFSET + (int)(x_coordinate * Viewer.TILE_SIZE);
    }

    /**
     * Calculate the y absolute coordinate in pixels on the board
     * @return y absolute coordinate in pixels on the board
     */
    static public int getPixel_y(int y_coordinate) {
        return Viewer.BOARD_Y_OFFSET + (int)(y_coordinate * Viewer.TILE_SIZE);
    }
}
