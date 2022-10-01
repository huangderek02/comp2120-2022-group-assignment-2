package GUI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Make methods are the initialization methods and should be called in the
 * init stage of controller only. Update methods are the methods that update
 * the GUI components and should be called in the update stage of controller.
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
    public static final int ITEM_X_BASE = 310;
    public static final int ITEM_Y_BASE = 540;
    public static final int ITEM_X_SIZE = 85;
    public static final int ITEM_Y_SIZE = 58;
    public static final int ITEM_X = 5;
    public static final int BAR_LEN = 150;
    public static final int BAR_HEIGHT = 15;
    public static final int BOARD_X = 11;
    public static final int BOARD_Y = 7;

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
    private final Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT, Color.BLACK);
    private final Text moneyText = new Text("0");
    private final ImageView HPImage = new ImageView(new File(URI_BASE + "ui/hpbar_1.png").toURI().toString());
    private final ImageView MPImage = new ImageView(new File(URI_BASE + "ui/hpbar_1.png").toURI().toString());
    private final TextArea dialogText = new TextArea();

    private int itemIndex = 0;
    private int money = 0;
    private int hp = 100; // Initialized
    private int mp = 100; // Initialized


    /**
     * Entry pointer for the game.
     */
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Courage");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Create the static UI texts.
     */
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

        // money text
        moneyText.setText(money + "");
        moneyText.setFont(pixelFont);
        moneyText.setFill(Color.WHITE);
        moneyText.setLayoutX(635);
        moneyText.setLayoutY(520);
        moneyText.setTextAlignment(TextAlignment.RIGHT);
        root.getChildren().add(moneyText);
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

    public void makeHPBar() {
        HPImage.setLayoutX(130);
        HPImage.setLayoutY(562);
        HPImage.setFitWidth(BAR_LEN);
        HPImage.setFitHeight(BAR_HEIGHT);
        root.getChildren().add(HPImage);

        MPImage.setLayoutX(130);
        MPImage.setLayoutY(615);
        MPImage.setFitWidth(BAR_LEN);
        MPImage.setFitHeight(BAR_HEIGHT);
        root.getChildren().add(MPImage);
    }

    /**
     * Create the dialog box and set the style properties.
     */
    public void makeDialog() {
        dialogText.setLayoutX(732);
        dialogText.setLayoutY(120);
        dialogText.setPrefWidth(270);
        dialogText.setPrefHeight(340);
        dialogText.setFont(pixelFont);
        dialogText.setEditable(false);
        dialogText.setWrapText(true);
        dialogText.setText("");

        dialogText.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px;");
        dialogText.getStylesheets().add(getClass().getResource("/dialog.css").toExternalForm());
        root.getChildren().add(dialogText);
    }

    /**
     * Create a new dialog with the given text.
     * @param text The text to display in the dialog.
     */
    public void initDialog(String text) {
        dialogText.setText(text);
    }

    /**
     * Add a new line to the current dialog.
     * @param text The text to add to the dialog.
     */
    public void appendDialog(String text) {
        dialogText.appendText("\n" + text);
    }

    /**
     * Set the HP and MP bars according to current HP and MP.
     */
    public void updateHPMPBar() {
        // hp
        if (hp < 80 && hp >= 60) {
            HPImage.setImage(new Image(new File(URI_BASE + "ui/hpbar_2.png").toURI().toString()));
        }
        if (hp > 40 && hp < 60) {
            HPImage.setImage(new Image(new File(URI_BASE + "ui/hpbar_3.png").toURI().toString()));
        }
        if (hp > 20 && hp <= 40) {
            HPImage.setImage(new Image(new File(URI_BASE + "ui/hpbar_4.png").toURI().toString()));
        }
        if (hp >= 10 && hp <= 20) {
            HPImage.setImage(new Image(new File(URI_BASE + "ui/hpbar_5.png").toURI().toString()));
        }
        if (hp < 10 && hp >= 0) {
            HPImage.setImage(new Image(new File(URI_BASE + "ui/hpbar_6.png").toURI().toString()));
        }

        // mp
        if (mp < 80 && mp >= 60) {
            MPImage.setImage(new Image(new File(URI_BASE + "ui/hpbar_2.png").toURI().toString()));
        }
        if (mp > 40 && mp < 60) {
            MPImage.setImage(new Image(new File(URI_BASE + "ui/hpbar_3.png").toURI().toString()));
        }
        if (mp > 20 && mp <= 40) {
            MPImage.setImage(new Image(new File(URI_BASE + "ui/hpbar_4.png").toURI().toString()));
        }
        if (mp >= 10 && mp <= 20) {
            MPImage.setImage(new Image(new File(URI_BASE + "ui/hpbar_5.png").toURI().toString()));
        }
        if (mp < 10 && mp >= 0) {
            MPImage.setImage(new Image(new File(URI_BASE + "ui/hpbar_6.png").toURI().toString()));
        }
    }

    /**
     * Update the repository with the given item.
     * @param item The item to be added to the repository.
     */
    public void addItem(Item item) {
        if (itemIndex < 10) {
            ImageView itemImage = new ImageView(new File(URI_BASE + "textures/" + item.toString()).toURI().toString());
            itemImage.setLayoutX(Item.getItemX(itemIndex));
            itemImage.setLayoutY(Item.getItemY(itemIndex));
            root.getChildren().add(itemImage);
            itemIndex++;
        }
    }

    /**
     * Update the money text. Maximum money is 99999.
     * @param amount The amount to be added to the money.
     */
    public void addMoney(int amount) {
        if (money + amount <= 99999) {
            money += amount;
            moneyText.setText(money + "");
        } else {
            moneyText.setText("99999");
        }
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

    public void setItemIndex(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }
}