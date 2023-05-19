package tetris.game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;

public class HomePage extends Application {
    Color main =Color.web("#252B39");
    @Override
    public void start(Stage homeStage) throws IOException {
        //Calling Background Music Function
        music(true);

        //Home Page Image
        Image image = new Image(getClass().getResourceAsStream("\\images\\homepagelogo.png"));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        //Tetris Text
        Label tetris = new Label("TETRIS");
        tetris.setFont(Font.font("Alex", FontWeight.BOLD, 40));
        tetris.setTextFill(main);

        //Mixing Them
        VBox imgandtext = new VBox(20);
        imgandtext.setAlignment(Pos.CENTER);
        imgandtext.getChildren().addAll(imageView, tetris);

        //Play Button
        Button play = new Button("PLAY");
        play.setMinWidth(200);
        play.setMinHeight(30);
        play.setFont(Font.font(20));
        play.setFont(Font. font("Legend", FontWeight. BOLD, 20));
        play.setStyle("-fx-background-color: #252B39; " +
                "-fx-background-radius: 10px; ");
        play.setTextFill(Color.WHITE);

        //To Run The Game
        play.setOnAction(e -> {
            Tetris t = new Tetris();
            try {
                t.start(new Stage());
                homeStage.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //About Us Button
        Button aboutus = new Button("ABOUT US");
        aboutus.setMinWidth(200);
        aboutus.setMinHeight(30);
        aboutus.setFont(Font.font(20));
        aboutus.setFont(Font. font("Legend", FontWeight. BOLD, 20));
        aboutus.setStyle("-fx-background-color: #252B39; " +
                "-fx-background-radius: 10px; ");
        aboutus.setTextFill(Color.WHITE);

        //Setting Button
        Button setting = new Button("Settings");
        setting.setMinWidth(200);
        setting.setMinHeight(30);
        setting.setFont(Font.font(20));
        setting.setFont(Font. font("Legend", FontWeight. BOLD, 20));
        setting.setStyle("-fx-background-color: #252B39; " +
                "-fx-background-radius: 10px; ");
        setting.setTextFill(Color.WHITE);

        setting.setOnAction(e -> {
            Settings t = new Settings();
            try {
                t.start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //Mixing Them
        VBox buttons = new VBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(play, aboutus, setting);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(100,0,100,0));
        root.setTop(imgandtext);
        root.setBottom(buttons);

        // Add Icon
        Image icon = new Image (getClass().getResourceAsStream("\\images\\Icon.png"));
        homeStage.getIcons().add(icon);

        homeStage.setResizable(false);
        Scene scene = new Scene(root, 450, 600);
        homeStage.setTitle("Tetris Game");
        homeStage.setScene(scene);
        homeStage.show();
    }

    //To Add Background Music
    public void music(boolean x) {
        AudioClip music = new AudioClip(getClass().getResource("\\Audio\\Music.mp3").toExternalForm());
        music.setVolume(0.5);
        if (x == true)
            music.play();
        else
            music.stop();
    }
    public static void main(String[] args) {
        launch();
    }
}