package model;

import engineV2.Cell;
import engineV2.GameObject;
import javafx.scene.input.KeyCode;
import model.cells.ActionCell;

import java.util.*;

/**
 * This represents the entire state of the current game
 *
 * @author Xianghao Wang
 * */
public class GameState {
    @Deprecated
    private Sign stateSign;
    final private Queue<String> dialogs;
    final private List<ActionCell[][]> maps;
    final private Map<Item, Integer> repository;
    final private GameObject gameObject;
    Location previousLoc = null;
    Location currentLoc = null;
    public int hp;
    public int money;

    public boolean isVictory = false;


    /**
     * Create the gamestate based the game object passed into the gameObject argument
     *
     * @author Xianghao Wang
     *
     * @param gameObject is the game object storing some metadata
     * */
    public GameState(GameObject gameObject) {
        this.dialogs = new LinkedList<>();
        this.maps = new ArrayList<>();
        this.repository = new HashMap<>();
        this.gameObject = gameObject;
        load();
    }

    /**
     * Load game state from game object
     *
     * @author Xianghao Wang
     * */
    private void load() {
       loadDialogs();
       loadMaps();
       loadRepository();
       loadLocation();
       loadNumericalStates();
    }

    /**
     * Load all of dialogs
     *
     * @author Xianghao Wang
     * */
    private void loadDialogs() {
        String str = gameObject.getState("dialogs");
        if (str == null) return;

        String[] tokens = str.split("&");
        for (String token : tokens) {
            offerDialog(token);
        }
    }

    /**
     * Load all of maps
     *
     * @author Xianghao Wang
     * */
    private void loadMaps() {
        for (int i = 0; i < gameObject.getMapCount(); i ++) {
            Cell[][] rawMap = gameObject.getMap(i);
            ActionCell[][] map = new ActionCell[rawMap.length][rawMap[0].length];
            for (int row = 0; row < map.length; row ++) {
                for (int col = 0; col < map[0].length; col ++) {
                    map[row][col] = (ActionCell) rawMap[row][col];
                }
            }
            this.maps.add(map);
        }
    }


    /**
     * Load all items to repository
     *
     * @author Xianghao Wang
     * */
    private void loadRepository() {
        String str = gameObject.getState("repository");
        if (str == null) return;
        String[] tokens = str.split("&");
        for (String token : tokens) {
            String[] pieces = token.split("\\*");
            String itemName = pieces[0];
            String number = pieces[1];
            Item item = switch (itemName) {
                case "KEY" -> Item.KEY;
                case "HP_RECOVERY" -> Item.HP_RECOVERY;
                default -> null;
            };
            if (item == null) continue;
            int n = Integer.parseInt(number);
            repository.put(item, n);
        }
    }

    /**
     * Load the current location
     *
     * @author Xianghao Wang
     * */
    private void loadLocation() {
        String str = gameObject.getState("location");
        if (str == null) return;
        String[] tokens = str.split("&");
        int level = Integer.parseInt(tokens[0]);
        int row = Integer.parseInt(tokens[1]);
        int col = Integer.parseInt(tokens[2]);
        this.currentLoc = new Location(level, row, col);
    }

    /**
     * Load numerical states
     *
     * @author Xianghao Wang
     * */
    private void loadNumericalStates() {
        this.hp = Integer.parseInt(gameObject.getState("hp"));
        this.money = Integer.parseInt(gameObject.getState("money"));
    }


    /**
     * Get HP value
     *
     * @author Xianghao Wang
     *
     * @return the HP value
     * */
    public int getHP() {
        return this.hp;
    }

    /**
     * Get money value
     *
     * @author Xianghao Wang
     *
     * @return the money value
     * */
    public int getMoney() {
        return this.money;
    }

    /**
     * Handle a key and change state
     *
     * @author Xianghao Wang
     *
     * @param key is the key listened from keyboard
     * */
    @Deprecated
    public void handle(KeyCode key) {
        Location newLoc = switch (key) {
            case W -> new Location(currentLoc.level, currentLoc.row - 1, currentLoc.col);
            case S -> new Location(currentLoc.level, currentLoc.row + 1, currentLoc.col);
            case A -> new Location(currentLoc.level, currentLoc.row, currentLoc.col - 1);
            case D -> new Location(currentLoc.level, currentLoc.row, currentLoc.col + 1);
            default -> null;
        };

        // Invalid key
        if (newLoc == null) return;

        // Check boundary
        if (newLoc.row < 0 || newLoc.row >= getMap(newLoc.level).length || newLoc.col < 0 || newLoc.col >= getMap(newLoc.level)[0].length) {
            return;
        }

        // Move to new location and act
        moveTo(newLoc);
        ActionCell cell = getMap(newLoc.level)[newLoc.row][newLoc.col];
        cell.act(this);
    }

    /**
     * Handle the up movement
     *
     * @author Xianghao Wang
     * */
    public void handleMoveUp() {
        Location newLocation = new Location(currentLoc.level, currentLoc.row - 1, currentLoc.col);
        if (!checkBoundary(newLocation)) return;

        // Change location
        moveTo(newLocation);

        // Trigger cell's action
        getCell(newLocation).act(this);
    }

    /**
     * Handle the down movement
     *
     * @author Xianghao Wang
     * */
    public void handleMoveDown() {
        Location newLocation = new Location(currentLoc.level, currentLoc.row + 1, currentLoc.col);
        if (!checkBoundary(newLocation)) return;

        // Change location
        moveTo(newLocation);

        // Trigger cell's action
        getCell(newLocation).act(this);
    }

    /**
     * Handle the left movement
     *
     * @author Xianghao Wang
     * */
    public void handleMoveLeft() {
        Location newLocation = new Location(currentLoc.level, currentLoc.row, currentLoc.col - 1);
        if (!checkBoundary(newLocation)) return;

        // Change location
        moveTo(newLocation);

        // Trigger cell's action
        getCell(newLocation).act(this);
    }

    /**
     * Handle the right movement
     *
     * @author Xianghao Wang
     * */
    public void handleMoveRight() {
        Location newLocation = new Location(currentLoc.level, currentLoc.row, currentLoc.col + 1);
        if (!checkBoundary(newLocation)) return;

        // Change location
        moveTo(newLocation);

        // Trigger cell's action
        getCell(newLocation).act(this);
    }

    /**
     * Get the cell at the given location
     *
     * @author Xianghao Wang
     *
     * @param location is the given location
     * @return is the cell at the location
     * */
    public ActionCell getCell(Location location) {
        return this.getMap(location.level)[location.row][location.col];
    }

    /**
     * Check whether a location is out of map
     *
     * @author Xianghao Wang
     *
     * @param location is to be checked
     * @return true if it is in the map; otherwise false
     * */
    public boolean checkBoundary(Location location) {
        return location.row >= 0 && location.row < getMap(location.level).length
                && location.col >= 0 && location.col < getMap(location.level)[0].length;
    }

    /**
     * Add the dialog into dialog queue
     *
     * @author Xianghao Wang
     *
     * @param dialog is the string of the dialog
     * */
    public void offerDialog(String dialog) {
        dialogs.offer(dialog);
    }

    /**
     * Get and remove the dialog from the dialog queue
     *
     * @author Xianghao Wang
     *
     * @return a String representing the dialog
     * */
    public String pollDialog() {
        if (dialogs.isEmpty()) return null;
        else return dialogs.poll();
    }

    /**
     * Get the current location of the character
     *
     * @author Xianghao Wang
     *
     * @return Location is the current location
     * */
    public Location getCurrentLocation() {
        return this.currentLoc;
    }


    /**
     * Move to a new location
     *
     * @author Xianghao Wang
     *
     * @param location is the destination
     * */
    public void moveTo(Location location) {
        this.previousLoc = this.currentLoc;
        this.currentLoc = location;
    }

    /**
     * Move back to the previous location
     *
     * @author Xianghao Wang
     * */
    public void moveBack() {
        this.currentLoc = this.previousLoc;
        this.previousLoc = null;
    }

    /**
     * Get the previous location of the character
     *
     * @author Xianghao Wang
     *
     * @return Location is the previous location
     * */
    public Location getPreviousLocation() {
        return this.previousLoc;
    }

    /**
     * Get the map at the specific level
     *
     * @author Xianghao Wang
     *
     * @param level is the current level
     * @return the map at the given level
     * */
    public ActionCell[][] getMap(int level) {
        return this.maps.get(this.currentLoc.level);
    }

    public int getMapCount() {
        return this.maps.size();
    }

    /**
     * Get the overall game state
     *
     * @author Xianghao Wang
     *
     * @return a Sign representing the overall game state
     * */
    @Deprecated
    public Sign getSign() {
        return this.stateSign;
    }

    /**
     * Add an item to repository
     *
     * @author Xianghao Wang
     *
     * @param item is to be added
     * */
    public void addItem(Item item) {
        this.repository.put(item, this.repository.getOrDefault(item, 0) + 1);
    }

    /**
     * Use an item
     *
     * @author Xianghao Wang
     *
     * @param item is to be used
     * @return true if it is used; if not found, retutrn false
     * */
    public boolean useItem(Item item) {
        if (this.repository.containsKey(item)) {
            this.repository.put(item, this.repository.get(item) - 1);
            if (this.repository.get(item).equals(0)) {
                this.repository.remove(item);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get the number of the given item
     *
     * @author Xianghao Wang
     *
     * @param item is the given item
     * @return the number of the given item
     * */
    public int get(Item item) {
        Integer count = this.repository.get(item);
        return count == null ? -1 : count;
    }

    /**
     * List all the items contained
     *
     * @author Xianghao Wang
     *
     * @return a list of all items contained
     *
     * */
    public List<Item> listItems() {
        List<Item> ret = new ArrayList<>();
        for (Item item : this.repository.keySet()) {
            for (int i = 0; i < this.repository.get(item); i ++) {
                ret.add(item);
            }
        }
        return ret;
    }


    /**
     * Represents the game sign
     *
     * @author Xianghao Wang
     * */
    @Deprecated
    public enum Sign {
        IN_GAME,
        VICTORY,
        PENDING
    }

    /**
     * Represents item in repository
     *
     * @author Xianghao Wang
     * */
    public enum Item {
        KEY,
        HP_RECOVERY
    }
}
