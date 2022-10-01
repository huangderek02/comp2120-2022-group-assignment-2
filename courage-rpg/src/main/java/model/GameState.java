package model;

import engine.Cell;
import engine.GameObject;
import javafx.scene.input.KeyCode;
import model.cells.ActionCell;

import java.util.*;

/**
 * This represents the whole state of the current gaem
 * */
public class GameState {
    private Sign stateSign;
    final private Queue<String> dialogs;
    final private List<ActionCell[][]> maps;
    final private Map<Item, Integer> repository;
    Location previousLoc = null;
    Location currentLoc = null;


    /**
     * Create the gamestate based on game object
     * @param gameObject is the game object storing some metadata
     * */
    public GameState(GameObject gameObject) {
        this.dialogs = new LinkedList<>();
        this.maps = new ArrayList<>();
        this.repository = new HashMap<>();
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
     * Get the current location of the character
     * @return Location is the current location
     * */
    public Location getCurrentLocation() {
        return this.currentLoc;
    }


    /**
     * Move to a new location
     * @param location is the destination
     * */
    public void moveTo(Location location) {
        this.previousLoc = this.currentLoc;
        this.currentLoc = location;
    }

    /**
     * Move back to the previous location
     * */
    public void moveBack() {
        this.currentLoc = this.previousLoc;
        this.previousLoc = null;
    }

    /**
     * Get the previous location of the character
     * @return Location is the previous location
     * */
    public Location getPreviousLocation() {
        return this.previousLoc;
    }

    /**
     * Get the map at the specific level
     * @param level is the current level
     * @return the map at the given level
     * */
    public Cell[][] getMap(int level) {
        return this.maps.get(this.currentLoc.level);
    }

    /**
     * Get the overall game state
     * @return a Sign representing the overall game state
     * */
    public Sign getSign() {
        return this.stateSign;
    }

    /**
     * Add an item to repository
     * @param item is to be added
     * */
    public void addItem(Item item) {
        this.repository.put(item, this.repository.getOrDefault(item, 0) + 1);
    }

    /**
     * Use an item
     * @param item is to be used
     * @return true if it is used; if not found, retutrn false
     * */
    public boolean useItem(Item item) {
        if (this.repository.containsKey(item)) {
            this.repository.put(item, this.repository.get(item) - 1);
            return true;
        } else {
            return false;
        }
    }

    public enum Sign {
        IN_GAME,
        VICTORY,
        PENDING
    }

    public enum Item {
        KEY,
        HP_RECOVERY
    }
}
