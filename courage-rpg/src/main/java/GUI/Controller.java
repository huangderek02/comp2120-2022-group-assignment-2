package GUI;

import engine.GameObject;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.File;

/**
 * @author Xin Lu
 */
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
        // Add background image last so it is on the top of board
        ImageView background = new ImageView(new File(Viewer.URI_BASE + "textures/bg.png").toURI().toString());
        viewer.getRoot().getChildren().add(background);
        viewer.makeText();
        viewer.makeIcon();
        viewer.makeButton();
        viewer.makeHPBar();
        viewer.makeDialog();
//        viewer.initDialog();
    }

    public void handleKeyBoard() {
        viewer.getScene().setOnKeyPressed(event -> System.out.println(event.toString()));
    }
}
