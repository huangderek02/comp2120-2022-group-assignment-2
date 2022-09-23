import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new Group(), 500, 500));
        stage.show();
    }

    public static void main(String[] args) {
        launch(App.class, args);
    }
}
