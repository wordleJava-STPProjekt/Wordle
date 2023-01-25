package com.wordle.wordle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class MainApplication extends Application {

    public static ArrayList<String> winningWords = new ArrayList<>();
    public static ArrayList<String> dictionaryWords = new ArrayList<>();

    private static Stage stageReference;

    @Override
    public void start(Stage stage) throws IOException {
        initializeWordLists();
        stageReference = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        MainController mainController = fxmlLoader.getController();
        mainController.createUI();
        mainController.getRandomWord();
        mainController.helpIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/help.png"))));
        mainController.restartIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/reload.png"))));

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        Scene scene = new Scene(root, 1600, 900);
        stage.setMinWidth(1600);
        stage.setMinHeight(900);
        stage.setMaxWidth(screenWidth);
        stage.setMaxHeight(screenHeight);

        stage.setTitle("WORDLEJAVAFX");
        stage.getIcons()
                .add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/icon.png"))));

        stage.setScene(scene);
        stage.show();

        mainController.gridRequestFocus();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void showWarning() {
        Warning.makeText(stageReference);
    }

    public static void quit() {
        stageReference.close();
    }

    public void initializeWordLists() {
        InputStream winning_words = getClass().getResourceAsStream("winningWords.txt");
        InputStream dictionary = getClass().getResourceAsStream("dictionary.txt");

        if (winning_words != null && dictionary != null) {
            Stream<String> winning_word_lines = new BufferedReader(new InputStreamReader(winning_words)).lines();
            winning_word_lines.forEach(winningWords::add);
            Stream<String> dictionary_lines = new BufferedReader(new InputStreamReader(dictionary)).lines();
            dictionary_lines.forEach(dictionaryWords::add);
        } else quit();
    }
}