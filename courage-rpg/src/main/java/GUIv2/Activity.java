package GUIv2;

import engineV2.GameEngine;
import engineV2.GameObject;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.GameState;
import model.Location;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The controller of GUI. It will call the updates methods in viewer.
 *
 * @author Xianghao Wang
 * @author Xin Lu
 */
public class Activity {
    private GameObject gameObject;
    private GameState gameState;
    private Viewer viewer;
    private Scene scene;
    private Stage stage;
    private String template;

    /**
     * The constructor of the activity, which functions as the controller.
     *
     * @author Xin Lu
     * @author Xianghao Wang
     *
     * @param gameObject the game object that represents the whole game
     * @param scene the scene that the activity is going to be rendered on
     * @param stage the stage that the activity is going to be rendered on
     */
    public Activity(GameObject gameObject, Scene scene, Stage stage, String template) {
        this.stage = stage;
        this.gameObject = gameObject;
        this.gameState = new GameState(gameObject);
        this.scene = scene;
        this.viewer = new Viewer(scene.getRoot(), gameObject, this);
        this.template = template;
    }

    /**
     * Start the game.
     *
     * @author Xin Lu
     * @author Xianghao Wang
     */
    public void start() {
        viewer.init();
        updateView();
        this.scene.setOnKeyPressed(this::handleKeyboard);
        System.out.println(this.scene.getFocusOwner());
    }

    /**
     * Update the view of the activity. Based on the keyboard
     * input.
     *
     * @author Xin Lu
     * @author Xianghao Wang
     *
     * @param keyEvent the keyboard event
     */
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

    /**
     * Update the view of the activity. Based on the mouse
     *
     * @author Xin Lu
     * @author Xianghao Wang
     *
     * @param buttonId the id of the button
     * @param mouseEvent the mouse event
     */
    public void handleMouse(String buttonId, MouseEvent mouseEvent) {
        if (buttonId.equals("exit")) {
            System.exit(0);
        }

        if (buttonId.equals("new")) {
            try {
                this.gameObject = GameEngine.loadGameObject(template);
                this.gameState = new GameState(gameObject);
                this.updateView();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }

        if (buttonId.equals("save")) {
            String savedDir = Integer.toHexString((int) System.currentTimeMillis());
            try {
                GameEngine.saveGameObject(savedDir, this.gameState.saveGameObject());
                gameState.offerDialog("[System] Saved to " + savedDir);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        if (buttonId.equals("load")) {
//            this.gameObject = GameEngine.loadGameObject("saves/" + )
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Save Game");
            dialog.setContentText("Input your save ID: ");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                try {
                    this.gameObject = GameEngine.loadGameObject("saves/" + result.get() + "/header.json");
                    this.gameState = new GameState(gameObject);
                    this.updateView();
                } catch (Exception e) {
                    this.gameState.offerDialog("Load failed: no " + result.get());
                }
            }
        }

        if (buttonId.equals("water")) {
            gameState.useItem(GameState.Item.HP_RECOVERY);
            gameState.hp += 10;
            if (gameState.hp > 100) gameState.hp = 100;
        }
        updateView();
    }

    /**
     * Update the view of whole game.
     *
     * @author Xin Lu
     * @author Xianghao Wang
     */
    public void updateView() {
        viewer.updateMoney(gameState.getMoney());
        viewer.updateHP(gameState.getHP());
        viewer.updateBoard(gameState.getMap(gameState.getCurrentLocation().level));
        Location location = gameState.getCurrentLocation();
        viewer.updateHero(location.row, location.col);
        viewer.updateItems(getItemList());
        stage.setTitle(gameObject.getState("title") + " - level " + (location.level + 1));

        String s2;
        while ((s2 = gameState.pollDialog()) != null) {
            viewer.appendDialog(s2);
        }
    }

    /**
     * Get all items in a list
     *
     * @author Xianghao Wang
     *
     * @return the all items in a list
     * */
    public List<ItemGUI> getItemList() {
        List<ItemGUI> ret = new ArrayList<>();
        for (GameState.Item item : gameState.listItems()) {
                ret.add(ItemGUI.convert(item));
        }
        return ret;
    }
}
