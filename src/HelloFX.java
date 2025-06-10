import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
// import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

/*
 * Stage (the window)
 └── Scene (holds the content)
      └── StackPane (puts things where coder wants them)
           └── Label (text to display)
 */
public class HelloFX extends Application {
    public void start(Stage first) {
        Label label = new Label("Welcome to VidSage GUI 👋");
        // StackPane root = new StackPane(label);
        VBox root = new VBox();
        
        TextField inputField = new TextField();
        root.getChildren().addAll(label, inputField);

        Button button = new Button("Go");
        root.getChildren().add(button);
        
        // Label outputLabel = new Label();
        // button.setOnAction (event -> {
        //     String userInput = inputField.getText();
        //     System.out.println("User wants to research for: " + userInput);

        //     String result = WikiFetcher.fetchData(userInput);

        //     outputLabel.setText(result);
        // });
        // root.getChildren().add(outputLabel);

        TextArea outputArea = new TextArea();
        outputArea.setWrapText(true);       //Wrap long lines
        outputArea.setEditable(false);      //Make it read-only
        //outputArea.setPrefRowCount(20);     //Define Size
        //outputArea.setPrefColumnCount(40);  

        button.setOnAction (event -> {
            String userInput = inputField.getText();
            System.out.println("User wants to research for: " + userInput);

            String result = WikiFetcher.fetchData(userInput);

            outputArea.setText(result);
        });
        root.getChildren().add(outputArea);
        
        Scene scene = new Scene(root, 400, 200);

        first.setScene(scene);
        first.setTitle("VidSage");
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
