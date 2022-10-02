package GUI;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.GameState;
import java.io.File;
import java.util.List;

/**
 * The controller of the GUI. It will call the updates methods in viewer
 * according to user inputs.
 * @author Xin Lu
 */
public class Controller {

    Viewer viewer;
    GameState gameState;

    public Controller(Viewer viewer, GameState gameState) {
        this.viewer = viewer;
        this.gameState = gameState;

        // Initialize the controller
        init();
    }

    public void init() {
        viewer.start(new Stage(), this);

        // Convert the cells to tiles
        List<Tile> tiles = Tile.convertTiles(gameState.getMap(gameState.getCurrentLocation().level));
        viewer.getBoard().getChildren().addAll(tiles);
        viewer.getRoot().getChildren().add(viewer.getBoard());

        // Add background image last so it is on the top of board
        ImageView background = new ImageView(new File(Viewer.URI_BASE + "textures/bg.png").toURI().toString());
        viewer.getRoot().getChildren().add(background);
        viewer.makeText();
        viewer.makeIcon();
        viewer.makeButton();
        viewer.makeHPBar();
        viewer.makeDialog();
//        viewer.initDialog();
    }

    public void handleKeyboard(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
    }
}
