package GUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    Viewer viewer = new Viewer();

    @Override
    public void start(Stage stage) throws Exception {
        viewer.start(new Stage());
    }
}
