package GUIv2;

import GUI.Viewer;
import model.GameState;

/**
 * Represent items collectable in the game.
 * @author Xin Lu
 */
public enum ItemGUI {
    WATER("water"),
    KEY("key");

    private final String fileName;

    public static ItemGUI convert(GameState.Item item) {
        return switch (item) {
            case KEY -> ItemGUI.KEY;
            case HP_RECOVERY -> ItemGUI.WATER;
        };
    }

    ItemGUI(String fileName) {
        this.fileName = fileName;
    }

    public String toString() {
        return fileName;
    }

    public static int getItemX(int index) {
        return GUI.Viewer.ITEM_X_BASE + (index % GUI.Viewer.ITEM_X) * GUI.Viewer.ITEM_X_SIZE;
    }

    public static int getItemY(int index) {
        return GUI.Viewer.ITEM_Y_BASE + (index - GUI.Viewer.ITEM_X >= 0 ? 1 : 0) * Viewer.ITEM_Y_SIZE;
    }
}
