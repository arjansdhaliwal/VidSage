package main;

import style.*;
import util.*;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import javafx.animation.PauseTransition;

import javafx.util.Duration;

public class LayoutBuilder {
    
    private static double sceneWidth = 800.00000;
    private static double sceneHeight = 600.00000;
    
    public static void setup(Stage stage){

        //Scene 1
        Scene1UI scene1 = Scene1Builder.build();
        Scene page1 = new Scene(scene1.layout, sceneWidth, sceneHeight);

        scene1.searchButton.setOnMouseEntered(e -> scene1.searchButton.setStyle(StyleHelper.BUTTON_HOVER_STYLE));
        scene1.searchButton.setOnMouseExited(e -> scene1.searchButton.setStyle(StyleHelper.BUTTON_NORMAL_STYLE));

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

        //Scene 2
        Scene2UI scene2 = Scene2Builder.build(null, stage);
        Scene page2 = new Scene(scene2.layout, sceneWidth, sceneHeight);

        scene1.searchButton.setOnAction(event -> {
            String userInput = scene1.inputField.getText();
            System.out.println("User wants to research for: " + userInput);

            String result = WikiFetcher.fetchData(userInput);
            scene2.outputField.setText(result);

            stage.setScene(page2);
        });

        scene2.backButton.setOnAction(event -> {
            stage.setScene(page1);
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

        scene2.backButton.setOnMouseEntered(e -> scene2.backButton.setStyle(StyleHelper.BUTTON_HOVER_STYLE));
        scene2.backButton.setOnMouseExited(e -> scene2.backButton.setStyle(StyleHelper.BUTTON_NORMAL_STYLE));
        
        stage.setScene(page1);
        stage.setTitle("VidSage");
        stage.show();
    }
}
