import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Xin Lu
 */
public class Viewer extends Application {

    // Game window is of size 1080p x 720p
    private static final int VIEWER_WIDTH = 1080;
    private static final int VIEWER_HEIGHT = 720;

    private final Group root = new Group();

    /**
     * Entry pointer for the game.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Courage");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        // Start the application
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}