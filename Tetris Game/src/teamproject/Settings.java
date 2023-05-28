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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Settings extends Application {
    // we make 2 buttons resposible for running the music (off for stop it and on for play it)
    Button off = new Button("OFF");
    Button on = new Button("ON");
    //we make 3 labels to express the buttons wich is resposible for gamemode,theme and music
    Label gamemode = new Label("Game Mode :");
    Label theme = new Label("Theme :");
    Label music = new Label("Music :");
    //we make 2 combobox to express  gamemode and gamecolor
    ComboBox<String> CBGamemode = new ComboBox<>();
    ComboBox<String> CBColors = new ComboBox<>();

    Image icon = new Image (getClass().getResourceAsStream("\\images\\Icon.png"));
    //we make 3 HBox and 1 vbox to use them later for adding nodes
    HBox audio = new HBox(20);
    HBox hGamemode = new HBox(20);
    HBox hColorMode = new HBox(20);
    //we make borderpane to add
    BorderPane root = new BorderPane();
    Tetris t1 = new Tetris();
//    public static int dur = Tetris.dur ;

    //we make scene to add borderpane on it
    Scene scene = new Scene(root, 300, 150);

    @Override
    public void start(Stage settingsStage) {
        //Game Mode Section
        //we give gamemode proprities as font and textfill
        gamemode.setFont(Font.font("Cambria", FontWeight.BOLD, 16));
        gamemode.setTextFill(Color.WHITE);

        //we give theme  proprities as font and textfill
        theme.setFont(Font.font("Cambria", FontWeight.BOLD, 16));
        theme.setTextFill(Color.WHITE);

        //we add items to combobox gamemode like easy,normal and hard
        CBGamemode.getItems().addAll("Easy", "Normal", "Hard");
        //we use getselectionmodel to make defult value =1
        CBGamemode.getSelectionModel().select(1);
        //we give combobox gamemode some propirties
        CBGamemode.setStyle("-fx-background-color: #f2f2f2;" +
                " -fx-text-fill: #333333;" +
                " -fx-font-family: Arial;" +
                "  -fx-border-color: #cccccc;" +
                " -fx-border-width: 1px;");

        // Events For Game Mode
        CBGamemode.setOnAction(e -> {
            String selected = CBGamemode.getValue();
            switch (selected) {
                case "Easy" :
                    Tetris.dur = 900;
                    System.out.println("Mode : " +selected);
                    break;
                case "Normal" :
                    Tetris.dur = 300;
                    System.out.println("Mode : " +selected);
                    break;
                case "Hard" :
                    Tetris.dur = 100;
                    System.out.println("Mode : " +selected);
                    break;
            }
        });

        //we use HBox hGamemode to add label gamemode and combobox CBGamemode and put it on center
        hGamemode.setAlignment(Pos.CENTER);
        hGamemode.getChildren().addAll(gamemode, CBGamemode);

        // we add items to combobox CBColors like DARK,WHITE,BLUE and color GOLDENROD
        CBColors.getItems().addAll("Dark", "White", "Black");
        //we use getselectionmodel to make defult value =0
        CBColors.getSelectionModel().select(0);
        //we give combobox CBColors  some proprieties
        CBColors.setStyle(  "-fx-background-color: white;" +
                " -fx-text-fill: #333333;" +
                " -fx-font-family: Cambria;" +
                "  -fx-border-color: #cccccc;" +
                " -fx-border-width: 1px;" );

        // The Action of CompoBox
        CBColors.setOnAction(e -> {
            String getvalue = CBColors.getValue();
            if (getvalue == "Dark"){
                Tetris.color = "#252B39";
                Tetris.textColor = Color.web("#FFFFFF");
                root.setStyle("-fx-background-color: #252B39;");
                gamemode.setTextFill(Color.WHITE);
                theme.setTextFill(Color.WHITE);
                music.setTextFill(Color.WHITE);
                System.out.println("Theme : " + getvalue);
            }
            else if (getvalue == "White"){
                Tetris.color = "#DFDFDF";
                Tetris.textColor = Color.web("#000000");
                root.setStyle("-fx-background-color: #DFDFDF;");
                gamemode.setTextFill(Color.BLACK);
                theme.setTextFill(Color.BLACK);
                music.setTextFill(Color.BLACK);
                System.out.println("Theme : " + getvalue);
            }
            else {
                Tetris.color = "#000000";
                Tetris.textColor = Color.web("#FFFFFF");
                root.setStyle("-fx-background-color: black;");
                gamemode.setTextFill(Color.WHITE);
                theme.setTextFill(Color.WHITE);
                music.setTextFill(Color.WHITE);
                System.out.println("Theme : " + getvalue);
            }
        });

        //we use HBox hcolorMode to add theme and CBColors and put it on center
        hColorMode.setAlignment(Pos.CENTER);
        hColorMode.getChildren().addAll(theme, CBColors);

        //Music Section
        music.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        music.setTextFill(Color.WHITE);

        //giving button on intial value =true
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

        //we make HBox audio to add label music and buttons off on and put it on center
        audio.getChildren().addAll(music, off, on);
        audio.setAlignment(Pos.CENTER);

        //we use Borderpane to add hGame on top and hcolormode on center and audio on bottom
        root.setTop(hGamemode);
        root.setCenter(hColorMode);
        root.setBottom(audio);
        root.setPadding(new Insets(20,0,20,0));
        //giving Background color
        root.setStyle("-fx-background-color: #252B39;");

        // Add Icon
        settingsStage.getIcons().add(icon);
        //make settingsstage not resizeable
        settingsStage.setResizable(false);
        // giving title named Setting
        settingsStage.setTitle("Settings");
        // adding scene on it
        settingsStage.setScene(scene);
        // showing it
        settingsStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}