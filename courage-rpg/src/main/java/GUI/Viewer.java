package GUI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

/**
 * @author Xin Lu
 */
public class Viewer extends Application {

    // Game window is of size 1080p x 720p
    private static final int VIEWER_WIDTH = 1080;
    private static final int VIEWER_HEIGHT = 720;
    public static final double TILE_FACTOR = 11;
    public static final double TILE_SIZE = 657/TILE_FACTOR;

    private final Group root = new Group();
    private final Group images = new Group();
    private final Group gameWrapper = new Group();
    private final Group board = new Group();
    private final Group dicePieces = new Group();

//    protected Parent makeContent() {
//
//    }

    /**
     * Entry pointer for the game.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Courage");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        // Add background image
        ImageView background = new ImageView(new File("courage-rpg/src/main/resources/textures/bg.png").toURI().toString());
        root.getChildren().add(background);

        // Debug
        Tile test = new Tile(0, 0, TileType.GRASS);
        root.getChildren().add(test);

        // Start the application
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}