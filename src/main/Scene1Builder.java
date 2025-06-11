package main;

import style.*;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;

// Scene1UI is a simple container used to return multiple UI elements from Scene1Builder.
// It holds only the parts needed by LayoutBuilder (like layout, input field, and button),
// while keeping all other layout details (like spacing, labels, HBox) private to this file.
class Scene1UI{
    public VBox layout;
    public TextField inputField;
    public Button searchButton;
}   

public class Scene1Builder {
    public static Scene1UI build(){
            VBox inputScene = new VBox();
            inputScene.setSpacing(12);
            inputScene.setPadding(new Insets(20));
            inputScene.setAlignment(Pos.CENTER);
            inputScene.setMaxWidth(400);

            Label label = new Label("VidSage");
            Font font = StyleHelper.loadSimanja(48);
            label.setFont(font);
            
            inputScene.getChildren().add(label);

            TextField inputField = new TextField();
            inputField.setMaxWidth(300);
            inputField.setStyle(StyleHelper.INPUT_STYLE);
            
            Button searchButton = new Button("🔍");
            searchButton.setStyle(StyleHelper.BUTTON_STYLE);

            HBox inputAlign = new HBox();
            inputAlign.setSpacing(10);
            inputAlign.setAlignment(Pos.CENTER);
            
            inputAlign.getChildren().addAll(inputField, searchButton);

            inputScene.getChildren().addAll(inputAlign);

            Scene1UI ui = new Scene1UI();
            ui.layout = inputScene;
            ui.inputField = inputField;
            ui.searchButton = searchButton;
            return ui;
    }
}