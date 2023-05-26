package teamproject;

// imports
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
    // crate the buttons
    Button btnPlay = new Button("P L A Y");
    Button btnSetting = new Button("S E T T I N G S");
    Button btnAboutUs = new Button("ABOUT US");
    // create label
    Label lblTetris = new Label("TETRIS");
    // add image and text
    VBox imgAndText = new VBox(20);
    // add buttons
    VBox buttons = new VBox(10);

    BorderPane root1 = new BorderPane();
    // create image
    Image image1 = new Image(getClass().getResourceAsStream("\\Images\\homepagelogo.png"),100,100,false,false);
    ImageView imageView1 = new ImageView(image1);
    Image icon = new Image (getClass().getResourceAsStream("\\Images\\Icon.png"));

    // set value of move equal value of move in tetris class
    // A fixed value to deal with throughout the program
    public static final int MOVE = Tetris.MOVE;
    // set value of XMAX equal the same value of XMAX in tetris class
    public static int XMAX = Tetris.XMAX;
    // set value of YMAX equal the same value of YMAX in tetris class
    public static int YMAX = Tetris.YMAX;
    // create a scene with a node root1 and set the size
    Scene scene1 = new Scene(root1, XMAX, YMAX + 75);

    @Override
    public void start(Stage homeStage) throws IOException {
        // Calling Background Music Function
        music(true);

        // set the font of label TETRIS,line type and line thickness
        lblTetris.setFont(Font.font("Cambria", FontWeight.BOLD, 40));
        // set the fill color of lblTetris dark blue
        lblTetris.setStyle("-fx-text-fill: #252B39;");

        // Mixing Image & Text TETRIS
        imgAndText.setAlignment(Pos.CENTER);
        // add the nodes(image and the Text TETRIS on VBox
        imgAndText.getChildren().addAll(imageView1, lblTetris);

        //Play Button//
        // set the width of the button btnplay
        btnPlay.setMinWidth(200);
        // set the height of the button btnplay
        btnPlay.setMinHeight(30);
        // set the font of the button btnplay,line type and line thickness
        btnPlay.setFont(Font. font("Cambria", FontWeight. BOLD, 20));
        // set the fill color of the button btnplay dark blue and define the shape of the corner of the button
        btnPlay.setStyle( "-fx-background-color: #252B39; " +
                          "-fx-background-radius: 10px; " );
        // set the color of the text of the btnplay
        btnPlay.setTextFill(Color.WHITE);

        // Hover Effect
        btnPlay.setOnMouseEntered(e -> {
            // set the color of the buuton white and the rest is as they are
            btnPlay.setStyle("-fx-background-color: #a5c5ff; -fx-text-fill: white; -fx-background-radius: 10px;");
        });
        // when the mouse exited
        btnPlay.setOnMouseExited(e -> {
            // set the color of the buuton dark blue
            btnPlay.setStyle("-fx-background-color: #252B39; -fx-background-radius: 10px; ");
        });

        // To Run The Game
        btnPlay.setOnAction(e -> {
            try {
                Tetris tetris = new Tetris();
                tetris.start(new Stage());
                homeStage.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // About Us Button
        // set the width of the button btnAboutUs
        btnAboutUs.setMinWidth(200);
        // set the height of the button btnAboutUs
        btnAboutUs.setMinHeight(30);
        // set the font of the button btnAboutUs
        btnAboutUs.setFont(Font.font(20));
        // set the font of the button line type and line thickness
        btnAboutUs.setFont(Font. font("Cambria", FontWeight. BOLD, 20));
        // set the fill color of the button btnAboutUs dark blue and define the shape of the corner of the button
        btnAboutUs.setStyle("-fx-background-color: #252B39; " +
                "-fx-background-radius: 10px; ");
        // set the color of the text of the btnAboutUs
        btnAboutUs.setTextFill(Color.WHITE);

        // Hover Effect
        btnAboutUs.setOnMouseEntered(e -> {
            // set the color of the btnAboutUs white and the rest is as they are
            btnAboutUs.setStyle("-fx-background-color: #a5c5ff; -fx-text-fill: white; -fx-background-radius: 10px;");
        });
        // when the mouse exited
        btnAboutUs.setOnMouseExited(e -> {
            // set the color of the buuton dark blue
            btnAboutUs.setStyle("-fx-background-color: #252B39; " +
                    "-fx-background-radius: 10px; ");
        });
        // To Open About Us Section
        btnAboutUs.setOnAction(e -> {
            try {
                AboutUs aboutUs = new AboutUs();
                aboutUs.start(new Stage());
            } catch (Exception ex) {
                // give a chance to correct the error
                throw new RuntimeException(ex);
            }
        });

        // Setting Button
        // set the width of the button btnSetting
        btnSetting.setMinWidth(200);
        // set the height of the button btnSetting
        btnSetting.setMinHeight(30);
        // set the font of the button btnSetting
        btnSetting.setFont(Font.font(20));
        // set the font of the button line type and line thickness
        btnSetting.setFont(Font. font("Cambria", FontWeight. BOLD, 20));
        // set the fill color of the button btnSetting dark blue and define the shape of the corner of the button
        btnSetting.setStyle("-fx-background-color: #252B39; " +
                            "-fx-background-radius: 10px; ");
        // set the color of the text of the btnSetting
        btnSetting.setTextFill(Color.WHITE);

        // Hover Effect
        // when the mouse exited
        btnSetting.setOnMouseEntered(e -> {
            // set the color of the buuton white and
            btnSetting.setStyle("-fx-background-color: #a5c5ff;" +
                                " -fx-text-fill: white; " +
                                "-fx-background-radius: 10px;");
        });
        btnSetting.setOnMouseExited(e -> {
            // set the color of the btnSetting dark blue
            btnSetting.setStyle("-fx-background-color: #252B39; " +
                                "-fx-background-radius: 10px; " );
        });
        // To Open btnSetting Section
        btnSetting.setOnAction(e -> {
            try {
                Settings t3 = new Settings();
                t3.start(new Stage());
            } catch (Exception ex) {
                // give a chance to correct the error
                throw new RuntimeException(ex);
            }
        });

        // Mixing Buttons
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

    // To Add Background Music
    public void music(boolean x) {
        Tetris tetris = new Tetris();
        AudioClip music = new AudioClip(getClass().getResource("\\Audio\\Music.mp3").toExternalForm());
        music.setVolume(0.3);
        if (x == true) {
            tetris.isMusicPlaying = true;
            System.out.println("Music Is ON");
            music.play();
        }
        else {
            music.stop();
            tetris.isMusicPlaying = false;
            System.out.println("Music Is OFF");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}