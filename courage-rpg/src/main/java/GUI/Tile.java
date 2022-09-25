package GUI;

import javafx.scene.layout.StackPane;

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
    }
}
