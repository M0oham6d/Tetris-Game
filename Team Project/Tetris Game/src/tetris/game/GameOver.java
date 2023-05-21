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
import javafx.scene.layout.Pane;
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

    @Override
    public void start(Stage gameOverStage) {
        Image image = new Image(getClass().getResourceAsStream("\\images\\gameover.png"));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        Label gameOver = new Label("GAME OVER");
        gameOver.setTextFill(Color.CRIMSON);
        gameOver.setFont(Font. font("Legend", FontWeight. BOLD, 45));

        VBox vBox0 = new VBox(10);
        vBox0.setAlignment(Pos.CENTER);
        vBox0.getChildren().addAll(imageView, gameOver);

        Label scoretext = new Label("Score \n" + score);
        scoretext.setTextFill(Color.WHITE);
        scoretext.setFont(Font. font("Arial", FontWeight.NORMAL, 45));
        StackPane pane2 = new StackPane(scoretext);

        Button newGame = new Button("New Game");
        newGame.setTextFill(Color.WHITE);
        newGame.setMinWidth(200);
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
        });

        VBox root = new VBox(100);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(vBox0, pane2, newGame);

        // Add Icon
        Image icon = new Image (getClass().getResourceAsStream("\\images\\Icon.png"));
        gameOverStage.getIcons().add(icon);

        gameOverStage.setResizable(false);
        Scene scene = new Scene(root, XMAX, YMAX + 75);
        root.setStyle("-fx-background-color: #252B39;");
        gameOverStage.setTitle("Settings");
        gameOverStage.setScene(scene);
        gameOverStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

}
