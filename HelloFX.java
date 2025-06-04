import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
 * Stage (the window)
 └── Scene (holds the content)
      └── StackPane (puts things where coder wants them)
           └── Label (text to display)
 */
public class HelloFX extends Application{
    public void start(Stage first){
        Label label = new Label("Welcome to VidSage GUI 👋");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 400, 200);
        
        first.setScene(scene);
        first.setTitle("VidSage Researcher");
        first.show();
    }
    public static void main(String[] args) {
        /*
         * It sets up the JavaFX environment (window system, rendering engine, etc.)
         * Then it automatically calls the 'start(Stage first)' method we wrote where we build the actual GUI.
         */
        launch(args);
    }
}
