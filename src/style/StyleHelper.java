package style;

import javafx.scene.text.Font;

public class StyleHelper {
    public static final String INPUT_STYLE =
        "-fx-background-radius: 24px;" +
        "-fx-font-size: 13px;" +
        "-fx-padding: 8 16;" +
        "-fx-background-color: #E0E0E0;" +
        "-fx-border-color: #ccc;" +
        "-fx-border-radius: 24px;" +
        "-fx-border-width: 1px;";

    public static final String BUTTON_STYLE =
        "-fx-background-radius: 24px;" +
        "-fx-background-color: #E0E0E0;" +
        "-fx-font-size: 13px;" +
        "-fx-padding: 8 16;" +
        "-fx-border-color: #ccc;" +
        "-fx-border-radius: 24px;" +
        "-fx-border-width: 1px;";

    public static final String BUTTON_HOVER_STYLE =
        "-fx-background-radius: 24px;" +
        "-fx-background-color: rgb(130, 128, 128);" +
        "-fx-font-size: 13px;" +
        "-fx-text-fill: white;" +
        "-fx-padding: 8 16;" +
        "-fx-border-color: #bbb;" +
        "-fx-border-radius: 24px;" +
        "-fx-border-width: 1px;";

    public static final String BUTTON_NORMAL_STYLE =
        BUTTON_STYLE + "-fx-text-fill: black;";

    public static Font loadSimanja(double size){
        return Font.loadFont(
            StyleHelper.class.getResourceAsStream("/assets/Fonts/Simanja/Simanja.ttf"), size
        );
    }
}
