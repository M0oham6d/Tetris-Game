package teamproject;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.IOException;

public class HomePage extends Application {
    // The variables
    Button btnPlay = new Button("PLAY");
    Button btnSetting = new Button("SETTINGS");
    Button btnAboutUs = new Button("ABOUT US");

    Label lblTetris = new Label("TETRIS");

    VBox imgAndText = new VBox(20);
    VBox buttons = new VBox(10);
    BorderPane root1 = new BorderPane();

    Image image1 = new Image(getClass().getResourceAsStream("\\Images\\homepagelogo.png"),100,100,false,false);
    ImageView imageView1 = new ImageView(image1);
    Image icon = new Image (getClass().getResourceAsStream("\\Images\\Icon.png"));

    public static final int MOVE = Tetris.MOVE;
    public static int XMAX = Tetris.XMAX;
    public static int YMAX = Tetris.YMAX;

    Scene scene1 = new Scene(root1, XMAX, YMAX + 75);


    @Override
    public void start(Stage homeStage) throws IOException {
        //Calling Background Music Function
        music(true);

        //Tetris Text
        lblTetris.setFont(Font.font("Alex", FontWeight.BOLD, 40));
        lblTetris.setStyle("-fx-text-fill: #252B39;");

        //Mixing Image & Text
        imgAndText.setAlignment(Pos.CENTER);
        imgAndText.getChildren().addAll(imageView1, lblTetris);

        //Play Button
        btnPlay.setMinWidth(200);
        btnPlay.setMinHeight(30);
        btnPlay.setFont(Font. font("Legend", FontWeight. BOLD, 20));
        btnPlay.setStyle("-fx-background-color: #252B39; " +
                "-fx-background-radius: 10px; ");
        btnPlay.setTextFill(Color.WHITE);

        //Hover Effect
        btnPlay.setOnMouseEntered(e -> {
            btnPlay.setStyle("-fx-background-color: #a5c5ff; -fx-text-fill: white; -fx-background-radius: 10px;");
        });
        btnPlay.setOnMouseExited(e -> {
            btnPlay.setStyle("-fx-background-color: #252B39; -fx-background-radius: 10px; ");
        });

        //To Run The Game
        btnPlay.setOnAction(e -> {
            try {
                Tetris tetris = new Tetris();
                tetris.start(new Stage());
                homeStage.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //About Us Button
        btnAboutUs.setMinWidth(200);
        btnAboutUs.setMinHeight(30);
        btnAboutUs.setFont(Font.font(20));
        btnAboutUs.setFont(Font. font("Legend", FontWeight. BOLD, 20));
        btnAboutUs.setStyle("-fx-background-color: #252B39; " +
                "-fx-background-radius: 10px; ");
        btnAboutUs.setTextFill(Color.WHITE);

        //Hover Effect
        btnAboutUs.setOnMouseEntered(e -> {
            btnAboutUs.setStyle("-fx-background-color: #a5c5ff; -fx-text-fill: white; -fx-background-radius: 10px;");
        });
        btnAboutUs.setOnMouseExited(e -> {
            btnAboutUs.setStyle("-fx-background-color: #252B39; " +
                    "-fx-background-radius: 10px; ");
        });
        //To Open About Us Section
        btnAboutUs.setOnAction(e -> {
            try {
                AboutUs aboutUs = new AboutUs();
                aboutUs.start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //Setting Button
        btnSetting.setMinWidth(200);
        btnSetting.setMinHeight(30);
        btnSetting.setFont(Font.font(20));
        btnSetting.setFont(Font. font("Legend", FontWeight. BOLD, 20));
        btnSetting.setStyle("-fx-background-color: #252B39; " +
                "-fx-background-radius: 10px; ");
        btnSetting.setTextFill(Color.WHITE);

        //Hover Effect
        btnSetting.setOnMouseEntered(e -> {
            btnSetting.setStyle("-fx-background-color: #a5c5ff; -fx-text-fill: white; -fx-background-radius: 10px;");
        });
        btnSetting.setOnMouseExited(e -> {
            btnSetting.setStyle("-fx-background-color: #252B39; " +
                    "-fx-background-radius: 10px; ");
        });

        btnSetting.setOnAction(e -> {
            try {
                Settings t3 = new Settings();
                t3.start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //Mixing Buttons
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(btnPlay, btnSetting, btnAboutUs);

        root1.setPadding(new Insets(75,0,75,0));
        root1.setTop(imgAndText);
        root1.setBottom(buttons);

        // Add Icon
        homeStage.getIcons().add(icon);

        homeStage.setResizable(false);
        homeStage.setTitle("Home Page");
        homeStage.setScene(scene1);
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