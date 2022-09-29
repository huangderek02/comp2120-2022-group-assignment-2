package GUI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Xin Lu
 */
public class Viewer {

    // Game window is of size 1080p x 720p
    public static final int VIEWER_WIDTH = 1080;
    public static final int VIEWER_HEIGHT = 720;
    public static final double TILE_FACTOR = 11;
    public static final double TILE_SIZE = 657/TILE_FACTOR + 0.5;
    public static final int BOARD_X_OFFSET = 58;
    public static final int BOARD_Y_OFFSET = 59;
    public static final int BOARD_WIDTH = (int)(TILE_SIZE*TILE_FACTOR);
    public static final int BOARD_HEIGHT = (int)(TILE_SIZE*7);

    // Load font
    public Font pixelFont = null;
    {
        try {
            pixelFont = Font.loadFont(new FileInputStream("courage-rpg/src/main/resources/Minecraft.ttf"), 25);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final Group root = new Group();
    private final Group images = new Group();
    private final Group gameWrapper = new Group();
    private final Group board = new Group();
    private final Group dicePieces = new Group();
    private final Group text = new Group();

//    public void init() {
//        // Debug
//        Board b = new Board();
//        board.getChildren().addAll(b.board.getChildren());
//        root.getChildren().add(board);
//    }

    /**
     * Entry pointer for the game.
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Courage");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        Text t = new Text("Staffan");
//        t.setStyle("-fx-font-size: 100");
//        t.setFont(pixelFont);
        root.getChildren().add(t);

        // Add background image
        ImageView background = new ImageView(new File("courage-rpg/src/main/resources/textures/bg.png").toURI().toString());
        root.getChildren().add(background);

        // Start the application
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void makeText() {
        // text images
        ImageView text1 = new ImageView(new File("courage-rpg/src/main/resources/text/char_name.png").toURI().toString());
        text1.setLayoutX(60);
        text1.setLayoutY(480);
        root.getChildren().add(text1);

        ImageView text2 = new ImageView(new File("courage-rpg/src/main/resources/text/repository.png").toURI().toString());
        text2.setLayoutX(300);
        text2.setLayoutY(480);
        root.getChildren().add(text2);

        ImageView text3 = new ImageView(new File("courage-rpg/src/main/resources/text/status.png").toURI().toString());
        text3.setLayoutX(720);
        text3.setLayoutY(480);
        root.getChildren().add(text3);

        ImageView text4 = new ImageView(new File("courage-rpg/src/main/resources/text/dialog.png").toURI().toString());
        text4.setLayoutX(720);
        text4.setLayoutY(60);
        root.getChildren().add(text4);

        // text
        Text t = new Text("0");
        t.setFont(pixelFont);
        t.setFill(Color.WHITE);
        t.setLayoutX(635);
        t.setLayoutY(520);
        t.setTextAlignment(TextAlignment.RIGHT);
        root.getChildren().add(t);
    }

    public void makeIcon() {
        ImageView money = new ImageView(new File("courage-rpg/src/main/resources/textures/money.png").toURI().toString());
        money.setLayoutX(590);
        money.setLayoutY(490);
        root.getChildren().add(money);
    }

    public Group getRoot() {
        return root;
    }

    public Group getImages() {
        return images;
    }

    public Group getGameWrapper() {
        return gameWrapper;
    }

    public Group getBoard() {
        return board;
    }

    public Group getDicePieces() {
        return dicePieces;
    }
}