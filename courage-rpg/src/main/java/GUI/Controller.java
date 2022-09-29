package GUI;

import engine.GameObject;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {

    Viewer viewer;
    GameObject gameObject;

    public Controller(Viewer viewer, GameObject gameObject) {
        this.viewer = viewer;
        this.gameObject = gameObject;
    }

    public void init() {
        viewer.start(new Stage());
        Board b = new Board();
        viewer.getBoard().getChildren().addAll(b.board.getChildren());
        viewer.getRoot().getChildren().add(viewer.getBoard());
        viewer.makeText();
        viewer.makeIcon();
    }

    public void updateView() {
        // TODO
    }
}
