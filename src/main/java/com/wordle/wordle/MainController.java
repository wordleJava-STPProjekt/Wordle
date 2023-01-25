package com.wordle.wordle;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.security.Key;

public class MainController {
    private final MainHelp mainHelp = MainHelp.getInstance();

    @FXML
    public GridPane gridPane;

    @FXML
    public GridPane keyboardRow1;

    @FXML
    public GridPane keyboardRow2;

    @FXML
    public GridPane keyboardRow3;

    @FXML
    public ImageView helpIcon;

    @FXML
    public HBox  titleHBox;

    @FXML
    public ImageView restartIcon;

    public void createUI() {
        createGrid();
        createKeyboard();
        createTitleHBox();
    }

    public void createGrid() {
        mainHelp.createGrid(gridPane);
    }

    public void createKeyboard() {
        mainHelp.createKeyboard(keyboardRow1, keyboardRow2, keyboardRow3);
    }

    public void gridRequestFocus() {
        gridPane.requestFocus();
    }

    @FXML
    protected void onKeyPressed(KeyEvent keyEvent) {
        mainHelp.onKeyPressed(gridPane, keyboardRow1, keyboardRow2, keyboardRow3, keyEvent);
    }

    public void getRandomWord() {
        mainHelp.getRandomWord();
    }

    public void showHelp() {
        HelpWindow.display();
    }

    public void createTitleHBox() {
        mainHelp.createTitleHBox(titleHBox);
    }

    public void restart() {
        mainHelp.restart(restartIcon, gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
    }
}