package tetris.game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Settings extends Application {
    Tetris tetris = new Tetris();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage settingsStage) {
        //Game Mode Section
        Label gamemode = new Label("Game Mode");
        gamemode.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Easy", "Normal", "Hard");
        comboBox.getSelectionModel().select(1);
        comboBox.setStyle("-fx-background-color: #f2f2f2;" +
                " -fx-text-fill: #333333;" +
                " -fx-font-family: Arial;" +
                "  -fx-border-color: #cccccc;" +
                " -fx-border-width: 1px;");
        HBox game = new HBox(20);
        game.setAlignment(Pos.CENTER);
        game.getChildren().addAll(gamemode, comboBox);

        //Music Section
        Label music = new Label("Music");
        music.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Button off = new Button("OFF");
        Button on = new Button("ON");

        //To Close & Open The Music
        HomePage homePage = new HomePage();
        off.setOnAction(e -> {
            homePage.music(false);
        });
        on.setOnAction(e -> {
            homePage.music(true);
        });

        HBox audio = new HBox(20);
        audio.getChildren().addAll(music, off, on);
        audio.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(game);
        root.setBottom(audio);
        root.setPadding(new Insets(0,0,20,0));

        // Add Icon
        Image icon = new Image (getClass().getResourceAsStream("\\images\\Icon.png"));
        settingsStage.getIcons().add(icon);
        settingsStage.setResizable(false);
        Scene scene = new Scene(root, 300, 150);
        settingsStage.setTitle("Settings");
        settingsStage.setScene(scene);
        settingsStage.show();
    }
}
