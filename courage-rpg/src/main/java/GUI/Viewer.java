package GUI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    public static final int VIEWER_WIDTH = 1078;
    public static final int VIEWER_HEIGHT = 720;
    public static final double TILE_FACTOR = 11;
    public static final double TILE_SIZE = Math.ceil(657/TILE_FACTOR + 0.1);
    public static final int BOARD_X_OFFSET = 58;
    public static final int BOARD_Y_OFFSET = 59;
    public static final int BOARD_WIDTH = (int)(TILE_SIZE*TILE_FACTOR);
    public static final int BOARD_HEIGHT = (int)(TILE_SIZE*7);
    public static final String URI_BASE = "src/main/resources/";

    // Load font
    public Font pixelFont = null;
    {
        try {
            System.out.println(System.getProperty("user.dir"));
            pixelFont = Font.loadFont(new FileInputStream(URI_BASE + "Minecraft.ttf"), 25);
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

    private final Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT, Color.BLACK);

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
        primaryStage.setResizable(false);
        primaryStage.setTitle("Courage");
//        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

//        Text t = new Text("Staffan");
//        root.getChildren().add(t);

        // Start the application
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void makeText() {
        // text images
        ImageView text1 = new ImageView(new File(URI_BASE + "text/char_name.png").toURI().toString());
        text1.setLayoutX(60);
        text1.setLayoutY(480);
        root.getChildren().add(text1);

        ImageView text2 = new ImageView(new File(URI_BASE + "text/repository.png").toURI().toString());
        text2.setLayoutX(300);
        text2.setLayoutY(480);
        root.getChildren().add(text2);

        ImageView text3 = new ImageView(new File(URI_BASE + "text/status.png").toURI().toString());
        text3.setLayoutX(720);
        text3.setLayoutY(480);
        root.getChildren().add(text3);

        ImageView text4 = new ImageView(new File(URI_BASE + "text/dialog.png").toURI().toString());
        text4.setLayoutX(720);
        text4.setLayoutY(60);
        root.getChildren().add(text4);

        ImageView text5 = new ImageView(new File(URI_BASE + "text/hp.png").toURI().toString());
        text5.setLayoutX(61);
        text5.setLayoutY(540);
        root.getChildren().add(text5);

        ImageView text6 = new ImageView(new File(URI_BASE + "text/mp.png").toURI().toString());
        text6.setLayoutX(61);
        text6.setLayoutY(593);
        root.getChildren().add(text6);

        // text
        Text t = new Text("0");
        t.setFont(pixelFont);
        t.setFill(Color.WHITE);
        t.setLayoutX(635);
        t.setLayoutY(520);
        t.setTextAlignment(TextAlignment.RIGHT);
        root.getChildren().add(t);
    }

    public void makeButton() {
        ImageView save = new ImageView(new File(URI_BASE + "buttons/save.png").toURI().toString());
        save.setLayoutX(748);
        save.setLayoutY(540);
        root.getChildren().add(save);

        ImageView load = new ImageView(new File(URI_BASE + "buttons/load.png").toURI().toString());
        load.setLayoutX(873);
        load.setLayoutY(540);
        root.getChildren().add(load);

        ImageView newBtn = new ImageView(new File(URI_BASE + "buttons/new.png").toURI().toString());
        newBtn.setLayoutX(745);
        newBtn.setLayoutY(595);
        root.getChildren().add(newBtn);

        ImageView exit = new ImageView(new File(URI_BASE + "buttons/exit.png").toURI().toString());
        exit.setLayoutX(874);
        exit.setLayoutY(595);
        root.getChildren().add(exit);
    }

    public void makeIcon() {
        ImageView money = new ImageView(new File(URI_BASE + "textures/money.png").toURI().toString());
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

    public Scene getScene() {
        return scene;
    }
}