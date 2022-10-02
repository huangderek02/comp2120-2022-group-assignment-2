package GUIv2;

public class Layout {
    // Game window is of size 1080p x 720p
    public static final int VIEWER_WIDTH = 1078;
    public static final int VIEWER_HEIGHT = 720;
    public static final double TILE_FACTOR = 11;
    public static final double TILE_SIZE = Math.ceil(657/TILE_FACTOR + 0.1);
    public static final int BOARD_X_OFFSET = 58;
    public static final int BOARD_Y_OFFSET = 59;
    public static final int BOARD_WIDTH = (int)(TILE_SIZE*TILE_FACTOR);
    public static final int BOARD_HEIGHT = (int)(TILE_SIZE*7);
    public static final String URI_BASE = "src/main/resources/";
    public static final int ITEM_X_BASE = 310;
    public static final int ITEM_Y_BASE = 540;
    public static final int ITEM_X_SIZE = 85;
    public static final int ITEM_Y_SIZE = 58;
    public static final int ITEM_X = 5;
    public static final int BAR_LEN = 150;
    public static final int BAR_HEIGHT = 15;
    public static final int BOARD_X = 11;
    public static final int BOARD_Y = 7;

}
