package GUIv2;

import engineV2.GameObject;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.GameState;
import model.Location;


public class Activity {
    private GameObject gameObject;
    private GameState gameState;
    private Viewer viewer;
    private Scene scene;

    public Activity(GameObject gameObject, Scene scene) {
        this.gameObject = gameObject;
        this.gameState = new GameState(gameObject);
        this.scene = scene;
        this.viewer = new Viewer(scene.getRoot(), gameObject, this);
    }

    public void start() {
        viewer.init();
        updateView();
        this.scene.setOnKeyPressed(this::handleKeyboard);
        System.out.println(this.scene.getFocusOwner());
    }

    public void handleKeyboard(KeyEvent keyEvent) {
        System.out.println("key triggerd" + " " + keyEvent.getCode());
        switch (keyEvent.getCode()) {
            case W -> gameState.handleMoveUp();
            case S -> gameState.handleMoveDown();
            case A -> gameState.handleMoveLeft();
            case D -> gameState.handleMoveRight();
        }
        updateView();
    }

    public void handleMouse(String buttonId, MouseEvent mouseEvent) {
        System.out.println(buttonId);
    }

    public void updateView() {
        viewer.updateMoney(gameState.getMoney());
        viewer.updateHP(gameState.getHP());
        viewer.updateBoard(gameState.getMap(gameState.getCurrentLocation().level));
        Location location = gameState.getCurrentLocation();
        viewer.updateHero(location.row, location.col);
    }


}
