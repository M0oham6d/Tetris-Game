package teamproject;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Settings extends Application {
    Tetris tetris = new Tetris();
    Color darkMode =Color.web("#252B39");
    Color surprise =Color.web("#9493EE");

    Button off = new Button("OFF");
    Button on = new Button("ON");
    Label gamemode = new Label("Game Mode :");
    Label theme = new Label("Theme :");
    Label music = new Label("Music :");
    ComboBox<String> CBGamemode = new ComboBox<>();
    ComboBox<String> CBColors = new ComboBox<>();
    Image icon = new Image (getClass().getResourceAsStream("\\images\\Icon.png"));
    HBox audio = new HBox(20);
    HBox hGamemode = new HBox(20);
    HBox hColorMode = new HBox(20);
    VBox Mode = new VBox(20);
    BorderPane root = new BorderPane();
    Tetris t1 = new Tetris();
    Scene scene = new Scene(root, 300, 150);

    @Override
    public void start(Stage settingsStage) {
        //Game Mode Section
        gamemode.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        gamemode.setTextFill(Color.WHITE);

        theme.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        theme.setTextFill(Color.WHITE);

        CBGamemode.getItems().addAll("Easy", "Normal", "Hard");
        CBGamemode.getSelectionModel().select(1);
        CBGamemode.setStyle("-fx-background-color: #f2f2f2;" +
                " -fx-text-fill: #333333;" +
                " -fx-font-family: Arial;" +
                "  -fx-border-color: #cccccc;" +
                " -fx-border-width: 1px;");
        // Events For Game Mode
//        CBGamemode.setOnAction(e -> {
//            String selected = CBGamemode.getValue();
//            switch (selected) {
//                case "Easy" :
//                    t1.dur = 600;
//                    System.out.println(selected);
//                    break;
//                case "Normal" :
//                    t1.dur = 300;
//                    System.out.println(selected);
//                    break;
//                case "Hard" :
//                    t1.dur = 100;
//                    System.out.println(selected);
//                    break;
//            }
//        });

        hGamemode.setAlignment(Pos.CENTER);
        hGamemode.getChildren().addAll(gamemode, CBGamemode);



        CBColors.getItems().addAll("Dark", "White", "Surprise");
        CBColors.getSelectionModel().select(0);
        CBColors.setStyle(  "-fx-background-color: white;" +
                " -fx-text-fill: #333333;" +
                " -fx-font-family: Arial;" +
                "  -fx-border-color: #cccccc;" +
                " -fx-border-width: 1px;" );

//        CBColors.setOnAction(e -> {
//            String getvalue = CBColors.getValue();
//            if (getvalue == "Dark"){
//                t1.p1.setStyle("-fx-background-color: #252B39; -fx-background-radius: 10;");
//                root.setStyle("-fx-background-color: #252B39;");
//            }
//            if (getvalue == "WHITE"){
//                t1.p1.setStyle("-fx-background-color: white; -fx-border-color: #4DAF7C; -fx-border-width: 5px;");
//                root.setStyle("-fx-background-color: white;");
//            }
//            if (getvalue == "BLUE"){
//                t1.p1.setStyle("-fx-background-color: #0D98BA; -fx-border-color: #4DAF7C; -fx-border-width: 5px;");
//                root.setStyle("-fx-background-color: #0D98BA;");
//            }
//            if (getvalue == "GOLDENROD"){
//                t1.p1.setStyle("-fx-background-color: #B87333; -fx-border-color: #4DAF7C; -fx-border-width: 5px;");
//                root.setStyle("-fx-background-color: #B87333;");
//            }
//        });

        hColorMode.setAlignment(Pos.CENTER);
        hColorMode.getChildren().addAll(theme, CBColors);

        //Music Section
        music.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        music.setTextFill(Color.WHITE);

        on.setDisable(true);

        //To Close & Open The Music
        off.setOnAction(e -> {
            HomePage homePage = new HomePage();
            homePage.music(false);
            off.setDisable(true);
            on.setDisable(false);
        });
        on.setOnAction(e -> {
            HomePage homePage = new HomePage();
            homePage.music(true);
            on.setDisable(true);
            off.setDisable(false);
        });

        audio.getChildren().addAll(music, off, on);
        audio.setAlignment(Pos.CENTER);

        //Mode.getChildren().addAll(hGamemode,hColorMode);

        root.setTop(hGamemode);
        root.setCenter(hColorMode);
        root.setBottom(audio);
        root.setPadding(new Insets(20,0,20,0));

        // Add Icon
        settingsStage.getIcons().add(icon);

        settingsStage.setResizable(false);
        root.setStyle("-fx-background-color: #252B39;");
        settingsStage.setTitle("Settings");
        settingsStage.setScene(scene);
        settingsStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}