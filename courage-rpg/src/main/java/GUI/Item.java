package GUI;

/**
 * Represent items collectable in the game.
 * @author Xin Lu
 */
public enum Item {
    WATER("water.png"),
    KEY("key.png");

    private final String fileName;

    Item(String fileName) {
        this.fileName = fileName;
    }

    public String toString() {
        return fileName;
    }

    public static int getItemX(int index) {
        return Viewer.ITEM_X_BASE + (index % Viewer.ITEM_X) * Viewer.ITEM_X_SIZE;
    }

    public static int getItemY(int index) {
        return Viewer.ITEM_Y_BASE + (index - Viewer.ITEM_X >= 0 ? 1 : 0) * Viewer.ITEM_Y_SIZE;
    }
}
