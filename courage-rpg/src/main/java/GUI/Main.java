package GUI;

import engine.Cell;
import engine.GameEngine;
import engine.GameObject;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.GameState;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Xin Lu
 */
public class Main extends Application {

    // MVC components
    Viewer viewer;
    Controller controller;

    private GameObject  gameObject;
    private GameState gameState;

    public void loadGame(String headerPath) throws IOException, URISyntaxException, ClassNotFoundException {
        gameObject = GameEngine.loadGame(headerPath);
        gameState =  new GameState(gameObject);

    }

    @Override
    public void start(Stage stage) throws ClassNotFoundException {
        viewer = new Viewer();
        controller = new Controller(viewer, null);
        controller.init();
        controller.handleKeyBoard();


    }
}
