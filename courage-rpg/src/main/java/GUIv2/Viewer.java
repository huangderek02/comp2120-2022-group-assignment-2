package GUIv2;

import GUI.Controller;
import GUI.Item;
import engineV2.GameObject;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.cells.ActionCell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class Viewer {
    private Group rootView;
    private GameObject gameObject;
    private Activity userInputDelegator;

    private final Group images = new Group();
    private final Group gameWrapper = new Group();
    private final Board board = new Board();
    private final Group dicePieces = new Group();
    private final Text moneyText = new Text("0");
    private final ImageView HPImage = new ImageView(new File(Layout.URI_BASE + "ui/hpbar_1.png").toURI().toString());
    private final TextArea dialogText = new TextArea();
    private final Group itemsView = new Group();
    int itemIndex = 0;
    private Tile hero;

    public Font pixelFont = null;
    {
        try {
            System.out.println(System.getProperty("user.dir"));
            pixelFont = Font.loadFont(new FileInputStream(Layout.URI_BASE + "Minecraft.ttf"), 25);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Viewer(Parent rootViiew, GameObject gameObject, Activity userInputDelegator) {
        this.rootView = (Group) rootViiew;
        this.gameObject = gameObject;
        this.userInputDelegator = userInputDelegator;
    }

    public void init() {
        // Make background
        ImageView background = new ImageView(new File(GUI.Viewer.URI_BASE + "textures/bg.png").toURI().toString());
        rootView.getChildren().add(background);

        this.makeButton();
        this.makeText();
        this.makeHPBar();
        this.makeDialog();
        this.makeIcon();

        rootView.getChildren().add(board);
        rootView.getChildren().add(itemsView);
    }

    public void makeButton() {
        ImageView save = new ImageView(new File(Layout.URI_BASE + "buttons/save.png").toURI().toString());
        save.setLayoutX(748);
        save.setLayoutY(540);
        rootView.getChildren().add(save);
        save.setOnMouseClicked(mouseEvent -> userInputDelegator.handleMouse("save", mouseEvent));

        ImageView load = new ImageView(new File(Layout.URI_BASE + "buttons/load.png").toURI().toString());
        load.setLayoutX(873);
        load.setLayoutY(540);
        rootView.getChildren().add(load);
        load.setOnMouseClicked(mouseEvent -> userInputDelegator.handleMouse("load", mouseEvent));

        ImageView newBtn = new ImageView(new File(Layout.URI_BASE + "buttons/new.png").toURI().toString());
        newBtn.setLayoutX(745);
        newBtn.setLayoutY(595);
        rootView.getChildren().add(newBtn);
        newBtn.setOnMouseClicked(mouseEvent -> userInputDelegator.handleMouse("new", mouseEvent));

        ImageView exit = new ImageView(new File(Layout.URI_BASE + "buttons/exit.png").toURI().toString());
        exit.setLayoutX(874);
        exit.setLayoutY(595);
        rootView.getChildren().add(exit);
        exit.setOnMouseClicked(mouseEvent -> userInputDelegator.handleMouse("exit", mouseEvent));
    }

    /**
     * Create the static UI texts.
     */
    public void makeText() {
        // text images
        ImageView text1 = new ImageView(new File(Layout.URI_BASE + "text/char_name.png").toURI().toString());
        text1.setLayoutX(60);
        text1.setLayoutY(480);
        rootView.getChildren().add(text1);

        ImageView text2 = new ImageView(new File(Layout.URI_BASE + "text/repository.png").toURI().toString());
        text2.setLayoutX(300);
        text2.setLayoutY(480);
        rootView.getChildren().add(text2);

        ImageView text3 = new ImageView(new File(Layout.URI_BASE + "text/status.png").toURI().toString());
        text3.setLayoutX(720);
        text3.setLayoutY(480);
        rootView.getChildren().add(text3);

        ImageView text4 = new ImageView(new File(Layout.URI_BASE + "text/dialog.png").toURI().toString());
        text4.setLayoutX(720);
        text4.setLayoutY(60);
        rootView.getChildren().add(text4);

        ImageView text5 = new ImageView(new File(Layout.URI_BASE + "text/hp.png").toURI().toString());
        text5.setLayoutX(61);
        text5.setLayoutY(540);
        rootView.getChildren().add(text5);

        // money text
        moneyText.setText("999");
        moneyText.setFont(pixelFont);
        moneyText.setFill(Color.WHITE);
        moneyText.setLayoutX(635);
        moneyText.setLayoutY(520);
        moneyText.setTextAlignment(TextAlignment.RIGHT);
        rootView.getChildren().add(moneyText);
    }

    public void makeHPBar() {
        HPImage.setLayoutX(130);
        HPImage.setLayoutY(562);
        HPImage.setFitWidth(Layout.BAR_LEN);
        HPImage.setFitHeight(Layout.BAR_HEIGHT);
        rootView.getChildren().add(HPImage);
    }

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
        dialogText.setEditable(false);
        dialogText.setOnKeyPressed(keyEvent -> userInputDelegator.handleKeyboard(keyEvent));
        rootView.getChildren().add(dialogText);
    }

    public void makeIcon() {
        ImageView money = new ImageView(new File(Layout.URI_BASE + "textures/money.png").toURI().toString());
        money.setLayoutX(590);
        money.setLayoutY(490);
        rootView.getChildren().add(money);
    }

    public void updateMoney(int money) {
        moneyText.setText(money + "");
    }

    public void updateHP(int hp) {
        // hp
        if (hp == 100) {
            HPImage.setImage(new Image(new File(Layout.URI_BASE + "ui/hpbar_1.png").toURI().toString()));
        } else if (hp < 80 && hp >= 60) {
            HPImage.setImage(new Image(new File(Layout.URI_BASE + "ui/hpbar_2.png").toURI().toString()));
        } else if (hp > 40 && hp < 60) {
            HPImage.setImage(new Image(new File(Layout.URI_BASE + "ui/hpbar_3.png").toURI().toString()));
        } else if (hp > 20 && hp <= 40) {
            HPImage.setImage(new Image(new File(Layout.URI_BASE + "ui/hpbar_4.png").toURI().toString()));
        } else if (hp >= 10 && hp <= 20) {
            HPImage.setImage(new Image(new File(Layout.URI_BASE + "ui/hpbar_5.png").toURI().toString()));
        } else if (hp < 10 && hp >= 0) {
            HPImage.setImage(new Image(new File(Layout.URI_BASE + "ui/hpbar_6.png").toURI().toString()));
        }
    }

    public void updateBoard(ActionCell[][] map) {
        this.board.updateBoard(map, gameObject);
    }

    public void updateHero(int row, int col) {
        rootView.getChildren().remove(hero);
        this.hero = new Tile(col, row, gameObject.getImage("hero"));
        rootView.getChildren().add(hero);
    }/**
     * Update the repository with the given item.
     * @param item The item to be added to the repository.
     */
    public void addItem(ItemGUI item) {
        if (itemIndex < 10) {
            ImageView itemImage = new ImageView(gameObject.getImage(item.toString()));
            itemImage.setLayoutX(Item.getItemX(itemIndex));
            itemImage.setLayoutY(Item.getItemY(itemIndex));
            itemsView.getChildren().add(itemImage);

            itemImage.setOnMouseClicked(mouseEvent -> userInputDelegator.handleMouse(item.toString(), mouseEvent));

            itemIndex++;
        }
    }

    /**
     * Add a new line to the current dialog.
     * @param text The text to add to the dialog.
     */
    public void appendDialog(String text) {
        dialogText.appendText("\n" + text);
    }


    public void updateItems(List<ItemGUI> items) {
        itemsView.getChildren().clear();
        itemIndex = 0;
        for (ItemGUI item: items) {
            addItem(item);
        }
    }


}
