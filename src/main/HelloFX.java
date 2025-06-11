package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloFX extends Application {
    public void start(Stage stage) {
         LayoutBuilder.setup(stage);
     }

    public static void main(String[] args) {
        /*
         * It sets up the JavaFX environment (window system, rendering engine, etc.)
         * Then it automatically calls the 'start(Stage first)' method we wrote where we build the actual GUI.
         */
        launch(args);
    } 
}
