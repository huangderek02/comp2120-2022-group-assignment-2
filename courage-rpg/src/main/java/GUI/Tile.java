package GUI;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.File;

/**
 * @author Xin Lu
 */
public class Tile extends StackPane {

    int board_x;
    int board_y;
    TileType tileType;

    public TileType getType() {
        return tileType;
    }

    public Tile(int x, int y, TileType tileType) {
        this.board_x = x;
        this.board_y = y;
        this.tileType = tileType;

        ImageView cellBackground = new ImageView(new File("courage-rpg/src/main/resources/textures/" + tileType.toString()).toURI().toString());
        cellBackground.setFitWidth(Viewer.TILE_SIZE);
        cellBackground.setFitHeight(Viewer.TILE_SIZE);
        getChildren().add(cellBackground);
    }
}
