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
    // we take an object  from tetris class
    Tetris tetris = new Tetris();
    Color White =Color.web("#9493EE");
    Color Blue =Color.web("#0D98BA");
    Color GoldenRod =Color.web("#B87333");
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
    VBox Mode = new VBox(20);
    //we make borderpane to add
    BorderPane root = new BorderPane();
    Tetris t1 = new Tetris();
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
        //We tried hard but we couldn't
//        CBGamemode.setOnAction(e -> {
//            String selected = CBGamemode.getValue();
//            switch (selected) {
//                case "Easy" :
//                    t1.dur = 900;
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

        //we use HBox hGamemode to add label gamemode and combobox CBGamemode and put it on center
        hGamemode.setAlignment(Pos.CENTER);
        hGamemode.getChildren().addAll(gamemode, CBGamemode);

        // we add items to combobox CBColors like DARK,WHITE,BLUE and color GOLDENROD
        CBColors.getItems().addAll("DARK", "WHITE", "BLUE","GOLDENROD");
        //we use getselectionmodel to make defult value =0
        CBColors.getSelectionModel().select(0);
        //we give combobox CBColors  some propirties
        CBColors.setStyle(  "-fx-background-color: white;" +
                " -fx-text-fill: #333333;" +
                " -fx-font-family: Cambria;" +
                "  -fx-border-color: #cccccc;" +
                " -fx-border-width: 1px;" );

        // The Action of CompoBox
        CBColors.setOnAction(e -> {
            String getvalue = CBColors.getValue();
            if (getvalue == "DARK"){
                t1.p1.setStyle("-fx-background-color: #252B39; -fx-background-radius: 10;");
                root.setStyle("-fx-background-color: #252B39;");
            }
            if (getvalue == "WHITE"){
                t1.p1.setStyle("-fx-background-color: White; -fx-border-color: #4DAF7C; -fx-border-width: 5px;");
                root.setStyle("-fx-background-color: White;");
            }
            if (getvalue == "BLUE"){
                t1.p1.setStyle("-fx-background-color: Blue; -fx-border-color: #4DAF7C; -fx-border-width: 5px;");
                root.setStyle("-fx-background-color: Blue;");
            }
            if (getvalue == "GOLDENROD"){
                t1.p1.setStyle("-fx-background-color: GoldenRod; -fx-border-color: #4DAF7C; -fx-border-width: 5px;");
                root.setStyle("-fx-background-color: GoldenRod;");
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