package GUIv2;

import engineV2.GameObject;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.GameState;
import model.Location;

import java.util.ArrayList;
import java.util.List;


public class Activity {
    private GameObject gameObject;
    private GameState gameState;
    private Viewer viewer;
    private Scene scene;
    private Stage stage;

    public Activity(GameObject gameObject, Scene scene, Stage stage) {
        this.stage = stage;
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
        if (buttonId.equals("water")) {
            gameState.useItem(GameState.Item.HP_RECOVERY);
            gameState.hp += 10;
            if (gameState.hp > 100) gameState.hp = 100;
        }
        updateView();
    }

    public void updateView() {
        viewer.updateMoney(gameState.getMoney());
        viewer.updateHP(gameState.getHP());
        viewer.updateBoard(gameState.getMap(gameState.getCurrentLocation().level));
        Location location = gameState.getCurrentLocation();
        viewer.updateHero(location.row, location.col);
        viewer.updateItems(getItemList());
        stage.setTitle(gameObject.getState("title") + " - level " + (location.level + 1));
    }

    public List<ItemGUI> getItemList() {
        List<ItemGUI> ret = new ArrayList<>();
        for (GameState.Item item : gameState.listItems()) {
                ret.add(ItemGUI.convert(item));
        }
        return ret;
    }
}
