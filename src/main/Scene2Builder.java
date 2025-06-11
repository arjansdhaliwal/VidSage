package main;

import style.*;

import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

class Scene2UI{
    public VBox layout;
    public TextArea outputField;
    public Button backButton;
}

public class Scene2Builder {
    public static Scene2UI build(Scene backScene, Stage stage){
        VBox outputLayout = new VBox();
        outputLayout.setSpacing(12);
        outputLayout.setAlignment(Pos.CENTER);
        outputLayout.setPadding(new Insets(20));

        TextArea outputArea = new TextArea();
        outputArea.setWrapText(true);
        outputArea.setEditable(false);
        outputArea.setMaxWidth(1000);

        Button backButton = new Button("Back");
        backButton.setStyle(StyleHelper.BUTTON_STYLE);

        // Add components to layout — this is what JavaFX will actually show
        outputLayout.getChildren().addAll(outputArea, backButton);

        // Prepare an object to return the layout + useful elements for external control
        Scene2UI ui = new Scene2UI();
        ui.layout = outputLayout;
        ui.outputField = outputArea;
        ui.backButton = backButton;

        return ui;
    }
}
