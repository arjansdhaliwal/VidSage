package main;

import style.*;
import util.*;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import javafx.animation.PauseTransition;

import javafx.util.Duration;

public class LayoutBuilder {
    
    public static void setup(Stage stage){

        //Scene 1
        Scene1UI scene1 = Scene1Builder.build();
        Scene2UI scene2 = Scene2Builder.build(null, stage);

        Scene sc = new Scene(scene1.layout);

        //Hover Effects
        scene1.searchButton.setOnMouseEntered(e -> scene1.searchButton.setStyle(StyleHelper.BUTTON_HOVER_STYLE));
        scene1.searchButton.setOnMouseExited(e -> scene1.searchButton.setStyle(StyleHelper.BUTTON_NORMAL_STYLE));
        scene2.backButton.setOnMouseEntered(e -> scene2.backButton.setStyle(StyleHelper.BUTTON_HOVER_STYLE));
        scene2.backButton.setOnMouseExited(e -> scene2.backButton.setStyle(StyleHelper.BUTTON_NORMAL_STYLE));

        //ENTER BUTTON
        scene1.inputField.setOnAction(event -> {
            scene1.searchButton.setStyle(StyleHelper.BUTTON_HOVER_STYLE);
            scene1.searchButton.fire();

            
            PauseTransition pause = new PauseTransition(Duration.millis(200));
            pause.setOnFinished(e ->{
                scene1.searchButton.setStyle(StyleHelper.BUTTON_NORMAL_STYLE);
            });
            pause.play();
        });

        scene1.searchButton.setOnAction(event -> {
            String userInput = scene1.inputField.getText();
            System.out.println("User wants to research for: " + userInput);

            String result = WikiFetcher.fetchData(userInput);
            scene2.outputField.setText(result);

            sc.setRoot(scene2.layout);
        });

        scene2.backButton.setOnAction(event -> {
            sc.setRoot(scene1.layout);
        });
        scene2.backButton.setStyle(StyleHelper.BUTTON_STYLE);

        scene2.layout.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                scene2.backButton.setStyle(StyleHelper.BUTTON_HOVER_STYLE);
                scene2.backButton.fire();

                PauseTransition pause = new PauseTransition(Duration.millis(200));
                pause.setOnFinished(e -> scene2.backButton.setStyle(StyleHelper.BUTTON_NORMAL_STYLE));
                pause.play();
            }
        });

        stage.setScene(sc);
        stage.setTitle("VidSage");
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setMinWidth(400);
        stage.setMinHeight(300);
        stage.setResizable(true);
        stage.show();
    }
}
