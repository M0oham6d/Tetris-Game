// this class used to know when you lose and used to end the game
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
    //set value of move equal value of move in the tetris class
    public static final int MOVE = Tetris.MOVE;
    //set value of xmax the same value in tetris class
    public static int XMAX = Tetris.XMAX;
    //set value of ymax the same value in tetris class
    public static int YMAX = Tetris.YMAX;
    // to connect the score in this class with the score in tetris class
    public static int score = Tetris.score;
    //used to get the path of the image
    Image image = new Image(getClass().getResourceAsStream("\\images\\gameover.png"));
    //to make the image shown
    ImageView imageView = new ImageView();
    //to make an icon
    Image icon = new Image (getClass().getResourceAsStream("\\images\\Icon.png"));
    // the form of game over page
    Label gameOver = new Label("GAME OVER");
    Label scoretext = new Label("Score: " + score);

    Button newGame = new Button("New Game");
    Button exit = new Button("EXIT");
    // arangment of the buttons and labels using different panes
    VBox vBox0 = new VBox(10);
    StackPane txtscore = new StackPane(scoretext);
    HBox buttons = new HBox(10);
    BorderPane root = new BorderPane();
    // adding the pane to the scene
    Scene scene = new Scene(root, XMAX, YMAX + 124);

    @Override
    // this code when page game over open
    public void start(Stage gameOverStage) {
        //showing the photo and setting it's width and height
        imageView.setImage(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        // setting the color and shape of game over word
        gameOver.setTextFill(Color.CRIMSON);
        gameOver.setFont(Font. font("Legend", FontWeight. BOLD, 45));
        //putting the pane in center and putting the image and game over word on it
        vBox0.setAlignment(Pos.CENTER);
        vBox0.getChildren().addAll(imageView, gameOver);
        // setting the color and the font of the score
        scoretext.setTextFill(Color.WHITE);
        scoretext.setFont(Font. font("Arial", FontWeight.BOLD, 45));

        newGame.setTextFill(Color.WHITE);
        newGame.setMinWidth(150);
        newGame.setMinHeight(30);
        newGame.setFont(Font.font(20));
        newGame.setFont(Font. font("Legend", FontWeight.NORMAL, 20));
        newGame.setStyle("-fx-background-color: green; " +
                "-fx-background-radius: 10px; ");
        newGame.setTextFill(Color.WHITE);
        //making the  newgame button to change its color when mouse on it
        newGame.setOnMouseEntered(e -> {
            newGame.setStyle("-fx-background-color: #a5c5ff; -fx-text-fill: white; -fx-background-radius: 10px;");
        });

        newGame.setOnMouseExited(e -> {
            newGame.setStyle("-fx-background-color: green; " +
                    "-fx-background-radius: 10px; ");
        });
        //when you entered on the button we open a new game
        newGame.setOnAction(e -> {
            Tetris t = new Tetris();
            try {
                t.start(new Stage());
                gameOverStage.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        exit.setTextFill(Color.WHITE);
        exit.setMinWidth(150);
        exit.setMinHeight(30);
        exit.setFont(Font.font(20));
        exit.setFont(Font. font("Legend", FontWeight. BOLD, 20));
        exit.setStyle("-fx-background-color: green; " +
                "-fx-background-radius: 10px; ");
        exit.setTextFill(Color.WHITE);
        //making the exit button to change its color when mouse on it
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
        // to make the buttons exit and new game on center
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(newGame, exit);

        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #252B39;");
        root.setTop(vBox0);
        root.setCenter(txtscore);
        root.setBottom(buttons);

        // Add Icon
        gameOverStage.getIcons().add(icon);
        // to make sure that size of page is constant
        gameOverStage.setResizable(false);
        gameOverStage.setTitle("Settings");
        gameOverStage.setScene(scene);
        gameOverStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}