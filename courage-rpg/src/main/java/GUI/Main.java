package GUI;

import engine.GameEngine;
import engine.GameObject;
import javafx.application.Application;
import javafx.stage.Stage;

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
    }
}
