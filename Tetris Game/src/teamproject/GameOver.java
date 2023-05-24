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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameOver extends Application {

    public static final int MOVE = Tetris.MOVE;
    public static int XMAX = Tetris.XMAX;
    public static int YMAX = Tetris.YMAX;
    public static int score = Tetris.score;

    Image image = new Image(getClass().getResourceAsStream("\\images\\gameover.png"));
    ImageView imageView = new ImageView();
    Image icon = new Image (getClass().getResourceAsStream("\\images\\Icon.png"));

    Label gameOver = new Label("GAME OVER");
    Label scoretext = new Label("Score: " + score);

    Button newGame = new Button("New Game");
    Button exit = new Button("EXIT");

    VBox vBox0 = new VBox(10);
    StackPane txtscore = new StackPane(scoretext);
    HBox buttons = new HBox(10);
    BorderPane root = new BorderPane();

    Scene scene = new Scene(root, XMAX, YMAX + 124);

    @Override
    public void start(Stage gameOverStage) {
        imageView.setImage(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        gameOver.setTextFill(Color.CRIMSON);
        gameOver.setFont(Font. font("Legend", FontWeight. BOLD, 45));

        vBox0.setAlignment(Pos.CENTER);
        vBox0.getChildren().addAll(imageView, gameOver);

        scoretext.setTextFill(Color.WHITE);
        scoretext.setFont(Font. font("Arial", FontWeight.NORMAL, 45));

        newGame.setTextFill(Color.WHITE);
        newGame.setMinWidth(150);
        newGame.setMinHeight(30);
        newGame.setFont(Font.font(20));
        newGame.setFont(Font. font("Legend", FontWeight. BOLD, 20));
        newGame.setStyle("-fx-background-color: green; " +
                "-fx-background-radius: 10px; ");
        newGame.setTextFill(Color.WHITE);
        //Hover Effect
        newGame.setOnMouseEntered(e -> {
            newGame.setStyle("-fx-background-color: #a5c5ff; -fx-text-fill: white; -fx-background-radius: 10px;");
        });
        newGame.setOnMouseExited(e -> {
            newGame.setStyle("-fx-background-color: green; " +
                    "-fx-background-radius: 10px; ");
        });
        //To New Game
        newGame.setOnAction(e -> {
            Tetris t = new Tetris();
            try {
                t.start(new Stage());
                gameOverStage.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            Tetris.score = 0;
        });

        exit.setTextFill(Color.WHITE);
        exit.setMinWidth(150);
        exit.setMinHeight(30);
        exit.setFont(Font.font(20));
        exit.setFont(Font. font("Legend", FontWeight. BOLD, 20));
        exit.setStyle("-fx-background-color: green; " +
                "-fx-background-radius: 10px; ");
        exit.setTextFill(Color.WHITE);
        //Hover Effect
        exit.setOnMouseEntered(e -> {
            exit.setStyle("-fx-background-color: #a5c5ff; -fx-text-fill: white; -fx-background-radius: 10px;");
        });
        exit.setOnMouseExited(e -> {
            exit.setStyle("-fx-background-color: green; " +
                    "-fx-background-radius: 10px; ");
        });
        //To Exit The Game
        exit.setOnAction(e -> {
           System.exit(0);
        });

        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(newGame, exit);

        root.setPadding(new Insets(20));
        root.setTop(vBox0);
        root.setCenter(txtscore);
        root.setBottom(buttons);
        // Add Icon
        gameOverStage.getIcons().add(icon);

        gameOverStage.setResizable(false);
        root.setStyle("-fx-background-color: #252B39;");
        gameOverStage.setTitle("Settings");
        gameOverStage.setScene(scene);
        gameOverStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

}