package model;

import engine.Cell;
import engine.GameObject;
import javafx.scene.input.KeyCode;

import java.util.*;

public class GameState {
    private Sign stateSign;
    final private Queue<String> dialogs;
    final private List<Cell[][]> maps;


    Location previousLoc = null;
    Location currentLoc = null;


    /**
     * Create the gamestate based on game object
     * @param gameObject is the game object storing some metadata
     * */
    public GameState(GameObject gameObject) {
        this.dialogs = new LinkedList<>();
        this.maps = new ArrayList<>();
    }

    /**
     * Handle a key and change state
     * @param key is the key listened from keyboard
     * */
    public void handle(KeyCode key) {

    }

    /**
     * Add the dialog into dialog queue
     * @param dialog is the string of the dialog
     * */
    public void offerDialog(String dialog) {
        dialogs.offer(dialog);
    }

    /**
     * Get and remove the dialog from the dialog queue
     * @return a String representing the dialog
     * */
    public String pollDialog() {
        if (dialogs.isEmpty()) return null;
        else return dialogs.poll();
    }

    /**
     * Get the overall game state
     * @return a Sign representing the overall game state
     * */
    public Sign getSign() {
        return this.stateSign;
    }

    public enum Sign {
        IN_GAME,
        VICTORY,
        PENDING
    }
}
