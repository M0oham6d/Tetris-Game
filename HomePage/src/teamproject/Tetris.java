package teamproject;

import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Tetris extends Application {
    // The variables
    // Defining several variables which set the size and dimensions of the game
    public static int MOVE = 25;
    public static int SIZE = MOVE;
    public static int XMAX = SIZE * 16;
    public static int YMAX = SIZE * 20;
    //Creates a 2D array called MESH which is meant to hold the block positions
    public static int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE];
    //Setting the dimensions for the scene
    Pane p1 = new Pane();
    Pane p2 = new Pane();
    Pane p3 = new Pane();
    HBox h = new HBox(10);
    VBox root3 = new VBox(10);
    //Setting the dimensions for the scene
    Scene scene = new Scene(root3, XMAX +40, YMAX +124);
    Form object;
    Form nextObj = Controller.makeRect();
    //Setting the initial values and text strings for multiple variables
    public static int score = 0;
    int top = 0;
    boolean game = true;
    int linesNo = 0;
    int dur = 300;
    Color color1;
    Text scoretext = new Text("Score: ");
    Text leveltext = new Text("Lines: ");
    Text MusicOfftext = new Text("Press M to Stop The Music");
    //Two variables for start and pause are created with symbols as their text
    Label start = new Label("▐▐");
    Label pause = new Label("▶");
    // Add Icon
    //Setting the icon of the window to a custom image
    Image icon = new Image (getClass().getResourceAsStream("\\images\\Icon.png"));
    boolean isMusicPlaying;
    Timeline timeline;

    @Override
    public void start(Stage tetrisStage) throws IOException {
        //Initializes the 2D array named MESH with zeroes using nested for loops
        //Reset Array
        for (int i = 0; i < MESH.length; i++) {
            for (int j = 0; j < MESH[i].length; j++) {
                MESH[i][j] = 0;
            }
        }

        //Setting the size and font style for score and level texts
        scoretext.setStyle("-fx-font: 20 Cambria;");
        scoretext.setFill(Color.WHITE);
        scoretext.setX(180);
        scoretext.setY(40);

        leveltext.setStyle("-fx-font: 20 Cambria;");
        leveltext.setFill(Color.WHITE);
        leveltext.setX(50);
        leveltext.setY(40);

        MusicOfftext.setStyle("-fx-font: 15 Cambria; -fx-font-weight: bold;");
        MusicOfftext.setFill(Color.RED);
        MusicOfftext.setX(70);
        MusicOfftext.setY(85);

        //Used to adjust the position of the start Label along the x and y axis
        start.setTranslateX(30);
        start.setTranslateY(20);
        start.setStyle("-fx-font: 20 arial;");
        start.setVisible(false);

        //Setting the size and style for the pause Label
        pause.setTranslateX(35);
        pause.setStyle("-fx-font: 60 arial;");

        //Setting the size and style for p1 which is used to hold the main game board elements
        p1.setPrefSize(XMAX + 50, YMAX  );
        p1.setStyle("-fx-background-color: #252B39; -fx-background-radius: 15 15 0 0;");
        //Customizing p2 that is used to display information about the game such as the current score and the number of lines cleared
        p2.setPrefHeight(70);
        p2.setPrefWidth(300);
        p2.setStyle("-fx-background-color: #252B39; -fx-background-radius: 0 0 15 15;");
        p2.getChildren().addAll(scoretext, leveltext, MusicOfftext);
        //p3 holds the start and pause buttons
        p3.getChildren().addAll(pause,start);

        h.getChildren().addAll(p2,p3);

        //root3 is a vbox object that holds everything together and keep the elements away from the edges
        root3.setPadding(new Insets(20 ));
        root3.getChildren().addAll(p1,h);

        //Creating Rectangles & Stage
        Form a = nextObj;
        p1.getChildren().addAll(a.a, a.b, a.c, a.d);
        //keyboard input is used to control movement
        moveOnKeyPress(a);
        object = a;
        nextObj = Controller.makeRect();
        tetrisStage.setScene(scene);

        //Adds the image icon on the window
        tetrisStage.getIcons().add(icon);
        tetrisStage.setResizable(false); //Setting the window to be non-resizable by user
        tetrisStage.setTitle("Tetris Game"); //Setting the title of the window
        tetrisStage.show(); //Displaying the window

        //The TimeLine That Make Shapes Go Down Automatically
        timeline = new Timeline(new KeyFrame(Duration.millis(dur), event -> {
            //When any of the shapes reach the top of the board the counter top is incremented
            if (object.a.getY() == 0 || object.b.getY() == 0 || object.c.getY() == 0 || object.d.getY() == 0) {
                top++;

            } else
                top = 0;

            if (top == 2) { //if the value of top reaches 2 the game ends
                // GAME OVER
                game = false;
            }
            if (top == 10) { //if it reaches 10 then the game ends and the phrase game over is displayed to the user and the game window is closed
                GameOver gameOver = new GameOver();
                try {
                    gameOver.start(new Stage());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                tetrisStage.close();
            }

            if (game) {
                //when an object moves down by one row it updates the score and lines display text
                MD(object);
                scoretext.setText("Score: " + Integer.toString(score));
                leveltext.setText("Lines: " + Integer.toString(linesNo));
            }
        }));

        //To Do The Task Infinitely
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //To Pause The Game with a click of the mouse
        pause.setOnMouseClicked(e -> {
            timeline.pause();
            pause.setVisible(false);
            start.setVisible(true);
        });

        //To Continue The Game when the mouse is clicked again
        start.setOnMouseClicked(e -> {
            timeline.play();
            start.setVisible(false);
            pause.setVisible(true);
        });


    }

    //Controlling the different shapes of the game using the keyboard
    private void moveOnKeyPress(Form form) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case RIGHT:
                    Controller.MoveRight(form);
                    break;
                case DOWN:
                    MD(form);
                    score++;
                    break;
                case LEFT:
                    Controller.MoveLeft(form);
                    break;
                case UP:
                    MoveTurn(form);
                    break;
                case SPACE:
                    if (timeline.getStatus() == Timeline.Status.RUNNING) {
                        timeline.stop();
                        pause.setVisible(false);
                        start.setVisible(true);
                        System.out.println("Game Stopped");
                    } else {
                        timeline.play();
                        pause.setVisible(true);
                        start.setVisible(false);
                        System.out.println("Game Started");
                    }
                    break;
                // To Mute The Music Inside The Game
                case M:
                    HomePage homePage = new HomePage();
                    if (isMusicPlaying) {
                        homePage.music(true);
                        isMusicPlaying = false;
                    }
                    else {
                        homePage.music(false);
                        isMusicPlaying = true;
                    }
                    break;
            }
        });
    }

    //Rotating all the different shapes of the game ( j, l, o, s, t and z)
    private void MoveTurn(Form form) {
        int f = form.form;
        Rectangle a = form.a;
        Rectangle b = form.b;
        Rectangle c = form.c;
        Rectangle d = form.d;
        switch (form.getName()) {
            case "j":
            /*
                1(a)
                1(b)1(c)1(d)
            */
                if (f == 1 && Rotation(a, 1, -1) && Rotation(c, -1, -1) && Rotation(d, -2, -2)) {
                    MoveRight(form.a);
                    MD(form.a);
                    MD(form.c);
                    MoveLeft(form.c);
                    MD(form.d);
                    MD(form.d);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                    /*
                        1(b)1(a)
                        1(c)
                        1(d)
                     */
                }
                if (f == 2 && Rotation(a, -1, -1) && Rotation(c, -1, 1) && Rotation(d, -2, 2)) {
                    MD(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
                    /*
                        1(d)1(c)1(b)
                        1(a)
                    */
                }
                if (f == 3 && Rotation(a, -1, 1) && Rotation(c, 1, 1) && Rotation(d, 2, 2)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                    /*
                            1(d)
                            1(c)
                        1(a)1(b)
                    */
                }
                if (f == 4 && Rotation(a, 1, 1) && Rotation(c, 1, -1) && Rotation(d, 2, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MD(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    MD(form.d);
                    MD(form.d);
                    form.changeForm();
                    break;
                    /*
                        1(a)
                        1(b)1(c)1(d)
                     */
                }
                break;
            case "l":
            /*
                        1(a)
                1(b)1(c)1(d)
            */
                if (f == 1 && Rotation(a, 1, -1) && Rotation(c, 1, 1) && Rotation(b, 2, 2)) {
                    MoveRight(form.a);
                    MD(form.a);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    MoveUp(form.b);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveRight(form.b);
                    form.changeForm();
                    break;
					/*
                        1(b)
                        1(c)
                        1(d)1(a)
                    */
                }
                if (f == 2 && Rotation(a, -1, -1) && Rotation(b, 2, -2) && Rotation(c, 1, -1)) {
                    MD(form.a);
                    MoveLeft(form.a);
                    MoveRight(form.b);
                    MoveRight(form.b);
                    MD(form.b);
                    MD(form.b);
                    MoveRight(form.c);
                    MD(form.c);
                    form.changeForm();
                    break;
                    /*
                        1(d)1(c)1(b)
                        1(a)
                    */
                }
                if (f == 3 && Rotation(a, -1, 1) && Rotation(c, -1, -1) && Rotation(b, -2, -2)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MD(form.c);
                    MoveLeft(form.c);
                    MD(form.b);
                    MD(form.b);
                    MoveLeft(form.b);
                    MoveLeft(form.b);
                    form.changeForm();
                    break;
                    /*
                        1(a)1(d)
                            1(c)
                            1(b)
                    */
                }
                if (f == 4 && Rotation(a, 1, 1) && Rotation(b, -2, 2) && Rotation(c, -1, 1)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveLeft(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.b);
                    MoveUp(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    form.changeForm();
                    break;
                    /*
                                1(a)
                        1(b)1(c)1(d)
                    */
                }
                break;
            case "o":
            /*
                1(a)1(b)
                1(c)1(d)
            */
                break;
            case "s":
            /*
                    1(b)1(a)
                1(d)1(c)
            */
                if (f == 1 && Rotation(a, -1, -1) && Rotation(c, -1, 1) && Rotation(d, 0, 2)) {
                    MD(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
                    /*
                        1(d)
                        1(c)1(b)
                            1(a)
                     */
                }
                if (f == 2 && Rotation(a, 1, 1) && Rotation(c, 1, -1) && Rotation(d, 0, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MD(form.c);
                    MD(form.d);
                    MD(form.d);
                    form.changeForm();
                    break;
                    /*
                            1(b)1(a)
                        1(d)1(c)
                    */
                }
                if (f == 3 && Rotation(a, -1, -1) && Rotation(c, -1, 1) && Rotation(d, 0, 2)) {
                    MD(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
                    /*
                        1(d)
                        1(c)1(b)
                            1(a)
                    */
                }
                if (f == 4 && Rotation(a, 1, 1) && Rotation(c, 1, -1) && Rotation(d, 0, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MD(form.c);
                    MD(form.d);
                    MD(form.d);
                    form.changeForm();
                    break;
                    /*
                            1(b)1(a)
                        1(d)1(c)
                    */
                }
                break;
            case "t":
            /*
                1(a)1(b)1(d)
                    1(c)
            */
                if (f == 1 && Rotation(a, 1, 1) && Rotation(d, -1, -1) && Rotation(c, -1, 1)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MD(form.d);
                    MoveLeft(form.d);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    form.changeForm();
                    break;
                    /*
                            1(a)
                        1(c)1(b)
                            1(d)
                    */
                }
                if (f == 2 && Rotation(a, 1, -1) && Rotation(d, -1, 1) && Rotation(c, 1, 1)) {
                    MoveRight(form.a);
                    MD(form.a);
                    MoveLeft(form.d);
                    MoveUp(form.d);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    form.changeForm();
                    break;
                    /*
                            1(c)
                        1(a)1(b)1(d)
                    */
                }
                if (f == 3 && Rotation(a, -1, -1) && Rotation(d, 1, 1) && Rotation(c, 1, -1)) {
                    MD(form.a);
                    MoveLeft(form.a);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    MoveRight(form.c);
                    MD(form.c);
                    form.changeForm();
                    break;
                    /*
                        1(a)
                        1(b)1(c)
                        1(d)
                    */
                }
                if (f == 4 && Rotation(a, -1, 1) && Rotation(d, 1, -1) && Rotation(c, -1, -1)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveRight(form.d);
                    MD(form.d);
                    MD(form.c);
                    MoveLeft(form.c);
                    form.changeForm();
                    break;
                    /*
                        1(a)1(b)1(d)
                            1(c)
                    */
                }
                break;
            case "z":
            /*
                1(b)1(a)
                    1(c)1(d)
            */
                if (f == 1 && Rotation(b, 1, 1) && Rotation(c, -1, 1) && Rotation(d, -2, 0)) {
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                    /*
                            1(b)
                        1(c)1(a)
                        1(d)
                     */
                }
                if (f == 2 && Rotation(b, -1, -1) && Rotation(c, 1, -1) && Rotation(d, 2, 0)) {
                    MD(form.b);
                    MoveLeft(form.b);
                    MoveRight(form.c);
                    MD(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                    /*
                        1(b)1(a)
                            1(c)1(d)
                    */
                }
                if (f == 3 && Rotation(b, 1, 1) && Rotation(c, -1, 1) && Rotation(d, -2, 0)) {
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                    /*
                            1(b)
                        1(c)1(a)
                        1(d)
                    */
                }
                if (f == 4 && Rotation(b, -1, -1) && Rotation(c, 1, -1) && Rotation(d, 2, 0)) {
                    MD(form.b);
                    MoveLeft(form.b);
                    MoveRight(form.c);
                    MD(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                    /*
                        1(b)1(a)
                            1(c)1(d)
                    */
                }
                break;
            case "i":
            /*
                1(a)1(b)1(c)1(d)
            */
                if (f == 1 && Rotation(a, 2, 2) && Rotation(b, 1, 1) && Rotation(d, -1, -1)) {
                    MoveUp(form.a);
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MD(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                    /*
                        1(a)
                        1(b)
                        1(c)
                        1(d)
                    */
                }
                if (f == 2 && Rotation(a, -2, -2) && Rotation(b, -1, -1) && Rotation(d, 1, 1)) {
                    MD(form.a);
                    MD(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.a);
                    MD(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                    /*
                        1(a)1(b)1(c)1(d)
                    */
                }
                if (f == 3 && Rotation(a, 2, 2) && Rotation(b, 1, 1) && Rotation(d, -1, -1)) {
                    MoveUp(form.a);
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MD(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                    /*
                        1(a)
                        1(b)
                        1(c)
                        1(d)
                    */
                }
                if (f == 4 && Rotation(a, -2, -2) && Rotation(b, -1, -1) && Rotation(d, 1, 1)) {
                    MD(form.a);
                    MD(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.a);
                    MD(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    /*
                        1(a)1(b)1(c)1(d)
                    */
                    break;
                }
                break;
        }
    }

    //To Remove The Full Row
    private void RemoveRows(Pane pane) {
        ArrayList<Node> rects = new ArrayList<Node>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Node> newrects = new ArrayList<Node>();
        int full = 0;
        // To Check Which Line Is Full
        for (int i = 0; i < MESH[0].length; i++) {	//Columns
            for (int j = 0; j < MESH.length; j++) {	//Rows
                if (MESH[j][i] == 1)
                    full++;
            }
            if (full == MESH.length)
                lines.add(i);
            full = 0;
        }
        //To Delete The Full Row
        if (lines.size() > 0)
            do {
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                score += 50;
                linesNo++;

                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() == lines.get(0) * SIZE) {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                        pane.getChildren().remove(node);
                    } else
                        newrects.add(node);
                }

                for (Node node : newrects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() < lines.get(0) * SIZE) {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                        a.setY(a.getY() + SIZE);
                    }
                }
                lines.remove(0);
                rects.clear();
                newrects.clear();
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    try {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                rects.clear();
            } while (lines.size() > 0);
    }

    private void MD(Rectangle rect) {
        if (rect.getY() + MOVE < YMAX)
            rect.setY(rect.getY() + MOVE);

    }

    private void MoveRight(Rectangle rect) {
        if (rect.getX() + MOVE <= XMAX - SIZE)
            rect.setX(rect.getX() + MOVE);
    }

    private void MoveLeft(Rectangle rect) {
        if (rect.getX() - MOVE >= 0)
            rect.setX(rect.getX() - MOVE);
    }

    private void MoveUp(Rectangle rect) {
        if (rect.getY() - MOVE > 0)
            rect.setY(rect.getY() - MOVE);
    }

    private void MD(Form form) {
        if (form.a.getY() == YMAX - SIZE || form.b.getY() == YMAX - SIZE || form.c.getY() == YMAX - SIZE
                || form.d.getY() == YMAX - SIZE || BlankSpaceA(form) || BlankSpaceB(form) || BlankSpaceC(form) || BlankSpaceD(form)) {
            MESH[(int) form.a.getX() / SIZE][(int) form.a.getY() / SIZE] = 1;
            MESH[(int) form.b.getX() / SIZE][(int) form.b.getY() / SIZE] = 1;
            MESH[(int) form.c.getX() / SIZE][(int) form.c.getY() / SIZE] = 1;
            MESH[(int) form.d.getX() / SIZE][(int) form.d.getY() / SIZE] = 1;
            RemoveRows(p1);

            Form a = nextObj;
            nextObj = Controller.makeRect();
            object = a;
            p1.getChildren().addAll(a.a, a.b, a.c, a.d);
            moveOnKeyPress(a);
        }

        //Moving Down If Down Is Empty
        if (form.a.getY() + MOVE < YMAX && form.b.getY() + MOVE < YMAX && form.c.getY() + MOVE < YMAX
                && form.d.getY() + MOVE < YMAX) {
            int movea = MESH[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1];
            int moveb = MESH[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1];
            int movec = MESH[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1];
            int moved = MESH[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setY(form.a.getY() + MOVE);
                form.b.setY(form.b.getY() + MOVE);
                form.c.setY(form.c.getY() + MOVE);
                form.d.setY(form.d.getY() + MOVE);
            }
        }
    }

    private boolean BlankSpaceA(Form form) {
        return (MESH[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1] == 1);
    }

    private boolean BlankSpaceB(Form form) {
        return (MESH[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1] == 1);
    }

    private boolean BlankSpaceC(Form form) {
        return (MESH[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1] == 1);
    }

    private boolean BlankSpaceD(Form form) {
        return (MESH[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1] == 1);
    }

    //To Make Sure That There Is An Empty Place To Rotate The Shape
    private boolean Rotation(Rectangle rect, int x, int y) {
        boolean xb = false;
        boolean yb = false;
        if (x >= 0)
            xb = rect.getX() + x * MOVE <= XMAX - SIZE;
        if (x < 0)
            xb = rect.getX() + x * MOVE >= 0;
        if (y >= 0)
            yb = rect.getY() - y * MOVE > 0;
        if (y < 0)
            yb = rect.getY() + y * MOVE < YMAX;
        return xb && yb && MESH[((int) rect.getX() / SIZE) + x][((int) rect.getY() / SIZE) - y] == 0;
    }

    public static void main(String[] args) {
        launch(args);
    }

}