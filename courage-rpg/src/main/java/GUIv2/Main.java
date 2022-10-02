package GUIv2;

import engineV2.GameEngine;
import engineV2.GameObject;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group rootView = new Group();
        Scene scene = new Scene(rootView, Layout.VIEWER_WIDTH, Layout.VIEWER_HEIGHT, Color.BLACK);
        GameObject gameObject = GameEngine.loadGameObject("templates/game-template/header.json");

        stage.setScene(scene);
        stage.setTitle(gameObject.getState("title"));
        stage.show();

        new Activity(gameObject, scene, stage).start();
    }
}
