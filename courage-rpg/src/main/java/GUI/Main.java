package GUI;

import engine.GameEngine;
import engine.GameObject;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Xin Lu
 */
public class Main extends Application {

    // MVC components
    Viewer viewer;
    GameObject gameObject ;
    Controller controller;

    GameEngine engine;

    @Override
    public void start(Stage stage) {
        viewer = new Viewer();
        controller = new Controller(viewer, null);
        controller.init();
        controller.handleKeyBoard();
    }
}
