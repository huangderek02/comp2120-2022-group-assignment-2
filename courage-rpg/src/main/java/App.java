import GUI.Main;
import engine.GameEngine;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {

    }

//    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException {
//        GameEngine.loadGame("templates/template-0/header.json");
//
//        // Launch javafx window
//        launch(App.class, args);
//    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}
