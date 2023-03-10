package GUI;

import engineV2.GameEngine;
import engineV2.GameObject;
import javafx.application.Application;
import javafx.stage.Stage;
import model.GameState;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

/**
 * Entry point of the GUI.
 * @author Xin Lu
 */
public class Main extends Application {

    // MVC components
    Viewer viewer;
    Controller controller;

    private GameObject gameObject;
    private GameState gameState;

    public void loadGame(String headerPath) throws IOException, URISyntaxException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        gameObject = GameEngine.loadGameObject(headerPath);
        gameState =  new GameState(gameObject);
    }

    @Override
    public void start(Stage stage) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            loadGame("templates/template-1/header.json");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        viewer = new Viewer();
        controller = new Controller(viewer, gameState);
    }
}
