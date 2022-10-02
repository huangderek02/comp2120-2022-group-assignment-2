package GUIv2;

import engineV2.GameEngine;
import engineV2.GameObject;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Entry point of the GUI.
 *
 * @author Xin Lu
 * @author Xianghao Wang
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        String template = "templates/game-template/header.json";
        Group rootView = new Group();
        Scene scene = new Scene(rootView, Layout.VIEWER_WIDTH, Layout.VIEWER_HEIGHT, Color.BLACK);
        GameObject gameObject = GameEngine.loadGameObject(template);

        stage.setScene(scene);
        stage.setTitle(gameObject.getState("title"));
        stage.show();

        new Activity(gameObject, scene, stage, template).start();
    }
}
