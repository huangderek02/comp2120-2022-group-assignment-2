package GUI;

import engine.Cell;
import engine.GameEngine;
import engine.GameObject;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.List;

/**
 * @author Xin Lu
 */
public class Main extends Application {

    // MVC components
    Viewer viewer;
    GameObject gameObject ;
    Controller controller;

    GameEngine engine = new GameEngine();

    @Override
    public void start(Stage stage) throws ClassNotFoundException {
        viewer = new Viewer();
        controller = new Controller(viewer, null);
        controller.init();
        controller.handleKeyBoard();

        String str = "model.cells.WallCell&123&hello";
        Pair<Class<Cell>, List<String>> actual = GameEngine.parseArgument(str);
        System.out.println(actual);
    }
}
