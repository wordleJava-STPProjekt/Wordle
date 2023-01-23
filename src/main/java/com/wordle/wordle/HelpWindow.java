package com.wordle.wordle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Objects;

public class HelpWindow {

    public static void display() {
        Stage helpWindow = new Stage();
        helpWindow.initModality(Modality.APPLICATION_MODAL);
        helpWindow.initStyle(StageStyle.UTILITY);
        helpWindow.setTitle("KAKO IGRATI");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20, 20, 20, 20));
        Label helpParagraph = new Label("Pogodite riječ u šest(6) pokušaja. \n" +
                "Svaki pokušaj mora biti valjana riječ od 5 slova. \n" +
                "Odgovor se submitta pritiskom tipke ENTER. \n\n" +
                "Nakon svakog pokušaja, boja kockice će se promijeniti \n" +
                "kako bi Vam pokazala koliko ste blizu točnom odgovoru.");
        helpParagraph.setTextAlignment(TextAlignment.CENTER);
        helpParagraph.getStyleClass().setAll("lead");

        Line linija1 = new Line();
        linija1.setStroke(Paint.valueOf("b8b8b8"));
        linija1.setEndX(2000);

        Label labelPrimjer = new Label("Primjeri");
        labelPrimjer.getStyleClass().setAll("h3");
        labelPrimjer.setTextAlignment(TextAlignment.LEFT);

        /* PRVA RIJEČ */

        ArrayList<Label> firstWord = new ArrayList<>();
        Label label1 = new Label("W");
        label1.getStyleClass().setAll("correct-letter-example");
        firstWord.add(label1);

        for (String letter : new String[]{"E", "A", "R", "Y"}) {
            Label label = new Label(letter);
            label.getStyleClass().setAll("default-letter-example");
            firstWord.add(label);
        }
        HBox firstWordVBox = new HBox(3);
        for (Label label : firstWord) firstWordVBox.getChildren().add(label);
        Label firstWordLabel = new Label("Slovo W se nalazi u riječi i na točnom je mjestu.");
        firstWordLabel.getStyleClass().setAll("lead");

        /* DRUGA RIJEČ */

        ArrayList<Label> secondWord = new ArrayList<>();
        Label labelP = new Label("P");
        labelP.getStyleClass().setAll("default-letter-example");

        Label labelI = new Label("I");
        labelI.getStyleClass().setAll("present-letter-example");

        secondWord.add(labelP);
        secondWord.add(labelI);

        for (String letter : new String[]{"L", "L", "S"}) {
            Label label = new Label(letter);
            label.getStyleClass().setAll("default-letter-example");
            secondWord.add(label);
        }

        HBox secondWordVBox = new HBox(3);

        for (Label label : secondWord) secondWordVBox.getChildren().add(label);

        Label secondWordLabel = new Label("Slovo I se nalazi u riječi, ali je na krivome mjestu.");
        secondWordLabel.getStyleClass().setAll("lead");

        /* TREĆA RIJEČ */

        ArrayList<Label> thirdWord = new ArrayList<>();

        for (String letter : new String[]{"V", "A", "G"}) {
            Label label = new Label(letter);
            label.getStyleClass().setAll("default-letter-example");
            thirdWord.add(label);
        }

        Label labelU = new Label("U");
        labelU.getStyleClass().setAll("wrong-letter-example");
        Label labelE = new Label("E");
        labelE.getStyleClass().setAll("default-letter-example");

        thirdWord.add(labelU);
        thirdWord.add(labelE);

        HBox thirdWordVBox = new HBox(3);
        for (Label label : thirdWord) thirdWordVBox.getChildren().add(label);

        Label thirdWordLabel = new Label("Slovo U se ne nalazi u riječi.");
        thirdWordLabel.getStyleClass().setAll("lead");

        Line linija2 = new Line();
        linija2.setStroke(Paint.valueOf("b8b8b8"));
        linija2.setEndX(2000);

        Button goBackButton = new Button("NAZAD");
        goBackButton.getStyleClass().setAll("btn", "btn-primary");

        goBackButton.setOnMouseClicked(me -> helpWindow.close());

        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(
                helpParagraph, linija1,
                labelPrimjer,
                firstWordVBox, firstWordLabel,
                secondWordVBox, secondWordLabel,
                thirdWordVBox, thirdWordLabel,
                linija2, goBackButton);

        Scene scene = new Scene(root, 500, 515);
        scene.getStylesheets()
                .add(Objects.requireNonNull(ScoreWindow.class.getResource("wordle.css")).toExternalForm()
                );

        helpWindow.getIcons()
                .add(new Image(Objects.requireNonNull(HelpWindow.class.getResourceAsStream("images/help.png"))));

        helpWindow.setScene(scene);
        helpWindow.showAndWait();
    }
}
