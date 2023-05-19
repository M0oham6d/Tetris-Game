package tetris.game;


import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Tetris extends Application {
    // The variables
    public static final int MOVE = 25;
    public static final int SIZE = 25;
    public static int XMAX = SIZE * 18;
    public static int YMAX = SIZE * 24;
    public static int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE];
    private static Pane group = new Pane();
    private static Form object;
    private static Scene scene = new Scene(group, XMAX , YMAX + 75);
    public static int score = 0;
    private static int top = 0;
    private static boolean game = true;
    private static Form nextObj = Controller.makeRect();
    private static int linesNo = 0;
    Text scoretext = new Text("Score: ");
    Text level = new Text("Lines: ");
    public int dur = 300;
    Label start = new Label("▐▐");
    Label pause = new Label("▶");

    @Override
    public void start(Stage stage) throws IOException {

        //Reset Array
        for (int i = 0; i < MESH.length; i++) {
            for (int j = 0; j < MESH[i].length; j++) {
                MESH[i][j] = 0;
            }
        }

        //Creating Bottom Side
        Line line = new Line(0, YMAX, XMAX, YMAX);

        scoretext.setStyle("-fx-font: 20 arial;");
        scoretext.setY(YMAX + 35);
        scoretext.setX(20);

        level.setStyle("-fx-font: 20 arial;");
        level.setY(YMAX + 35);
        level.setX(120);
        level.setFill(Color.RED);
        group.getChildren().addAll(scoretext, line, level);

        start.setTranslateX(350);
        start.setTranslateY(YMAX + 25);
        start.setStyle("-fx-font: 20 arial;");
        group.getChildren().add(start);
        start.setVisible(false);

        pause.setTranslateX(360);
        pause.setTranslateY(YMAX + 3);
        pause.setStyle("-fx-font: 60 arial;");
        group.getChildren().add(pause);

        //Creating Rectangles & Stage
        Form a = nextObj;
        group.getChildren().addAll(a.a, a.b, a.c, a.d);
        moveOnKeyPress(a);
        object = a;
        nextObj = Controller.makeRect();
        stage.setScene(scene);

        // Add Icon
        Image icon = new Image (getClass().getResourceAsStream("\\images\\Icon.png"));
        stage.getIcons().add(icon);

        stage.setResizable(false);
        stage.setTitle("Tetris Game");
        stage.show();

        timeLine (dur);

    }

    public void timeLine (int dur) {
        //The TimeLine That Make Shapes Go Down Automatically
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(dur), event -> {
            if (object.a.getY() == 0 || object.b.getY() == 0 || object.c.getY() == 0 || object.d.getY() == 0) {
                top++;

            } else
                top = 0;

            if (top == 2) {
                // GAME OVER
                Text over = new Text("GAME OVER");
                over.setFill(Color.RED);
                over.setStyle("-fx-font: 70 arial;");
                over.setY(250);
                over.setX(10);
                group.getChildren().add(over);
                game = false;
            }
            if (top == 15)
                System.exit(0);

            if (game) {
                MoveDown(object);
                scoretext.setText("Score: \n    " + Integer.toString(score));
                level.setText("Lines: \n    " + Integer.toString(linesNo));
            }
        }));

        //To Do The Task Infinitely
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //To Pause The Game
        pause.setOnMouseClicked(e -> {
            timeline.pause();
            pause.setVisible(false);
            start.setVisible(true);
        });

        //To Continue The Game
        start.setOnMouseClicked(e -> {
            timeline.play();
            start.setVisible(false);
            pause.setVisible(true);
        });

    }
    //Controlling System Using Keyboard Buttons
    private void moveOnKeyPress(Form form) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case RIGHT:
                    Controller.MoveRight(form);
                    break;
                case DOWN:
                    MoveDown(form);
                    score++;
                    break;
                case LEFT:
                    Controller.MoveLeft(form);
                    break;
                case UP:
                    MoveTurn(form);
                    break;
            }
        });
    }

    //To Rotate The Shapes
    private void MoveTurn(Form form) {
        int f = form.form;
        Rectangle a = form.a;
        Rectangle b = form.b;
        Rectangle c = form.c;
        Rectangle d = form.d;
        switch (form.getName()) {
            case "j":
//			a
//			b	c	d
                if (f == 1 && cB(a, 1, -1) && cB(c, -1, -1) && cB(d, -2, -2)) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
//				b	a
//				c
//				d

                }

//				b	a
//				c
//				d
                if (f == 2 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, -2, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
//		d	c	b
//				a

                }

//		d	c	b
//				a
                if (f == 3 && cB(a, -1, 1) && cB(c, 1, 1) && cB(d, 2, 2)) {
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
//				d
//				c
//			a	b

                }

//				d
//				c
//			a	b
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 2, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeForm();
                    break;
//				a
//				b	c	d
                }
                break;
            case "l":
//					a
//			b	c	d
                if (f == 1 && cB(a, 1, -1) && cB(c, 1, 1) && cB(b, 2, 2)) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    MoveUp(form.b);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveRight(form.b);
                    form.changeForm();
                    break;
//					b
//					c
//					d	a

                }

//					b
//					c
//					d	a
                if (f == 2 && cB(a, -1, -1) && cB(b, 2, -2) && cB(c, 1, -1)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveRight(form.b);
                    MoveRight(form.b);
                    MoveDown(form.b);
                    MoveDown(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    form.changeForm();
                    break;
//					d	c	b
//					a

                }

//					d	c	b
//					a
                if (f == 3 && cB(a, -1, 1) && cB(c, -1, -1) && cB(b, -2, -2)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    MoveDown(form.b);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveLeft(form.b);
                    form.changeForm();
                    break;
//					d	a
//					c
//					b

                }

//					d	a
//					c
//					b
                if (f == 4 && cB(a, 1, 1) && cB(b, -2, 2) && cB(c, -1, 1)) {
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
//					a
//					d	c	b
                }
                break;
            case "o":
                break;
            case "s":
//				b	a
//			d	c
                if (f == 1 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
//			d
//			c	b
//				a

                }

//			d
//			c	b
//				a
                if (f == 2 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeForm();
                    break;
//				b	a
//			d	c

                }

//				b	a
//			d	c
                if (f == 3 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
//			d
//			c	b
//				a

                }

//			d
//			c	b
//				a
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeForm();
                    break;
//				b	a
//			d	c
                }
                break;
            case "t":
//			a	b	d
//				c
                if (f == 1 && cB(a, 1, 1) && cB(d, -1, -1) && cB(c, -1, 1)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    form.changeForm();
                    break;
//				a
//			c	b
//				d

                }

//				a
//			c	b
//				d
                if (f == 2 && cB(a, 1, -1) && cB(d, -1, 1) && cB(c, 1, 1)) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.d);
                    MoveUp(form.d);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    form.changeForm();
                    break;
//				c
//			d	b	a

                }

//				c
//			d	b	a
                if (f == 3 && cB(a, -1, -1) && cB(d, 1, 1) && cB(c, 1, -1)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    form.changeForm();
                    break;
//				d
//				b	c
//				a

                }

//				d
//				b	c
//				a
                if (f == 4 && cB(a, -1, 1) && cB(d, 1, -1) && cB(c, -1, -1)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveRight(form.d);
                    MoveDown(form.d);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    form.changeForm();
                    break;
//			a	b	d
//				c
                }
                break;
            case "z":
//			b	a
//				c	d
                if (f == 1 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
//				b
//			c	a
//			d

                }

//				b
//			c	a
//			d
                if (f == 2 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
//			b	a
//				c	d

                }

//			b	a
//				c	d
                if (f == 3 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
//				b
//			c	a
//			d

                }

//				b
//			c	a
//			d
                if (f == 4 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
//			b	a
//				c	d
                }
                break;
            case "i":
//			a	b	c	d
                if (f == 1 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    MoveUp(form.a);
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
//					a
//					b
//					c
//					d

                }

//					a
//					b
//					c
//					d
                if (f == 2 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    MoveDown(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.a);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
//			a	b	c	d

                }

//			a	b	c	d
                if (f == 3 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    MoveUp(form.a);
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
//					a
//					b
//					c
//					d

                }

//					a
//					b
//					c
//					d
                if (f == 4 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    MoveDown(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.a);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
//			a	b	c	d
                }
                break;
        }
    }

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

    private void MoveDown(Rectangle rect) {
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

    private void MoveDown(Form form) {
        if (form.a.getY() == YMAX - SIZE || form.b.getY() == YMAX - SIZE || form.c.getY() == YMAX - SIZE
                || form.d.getY() == YMAX - SIZE || moveA(form) || moveB(form) || moveC(form) || moveD(form)) {
            MESH[(int) form.a.getX() / SIZE][(int) form.a.getY() / SIZE] = 1;
            MESH[(int) form.b.getX() / SIZE][(int) form.b.getY() / SIZE] = 1;
            MESH[(int) form.c.getX() / SIZE][(int) form.c.getY() / SIZE] = 1;
            MESH[(int) form.d.getX() / SIZE][(int) form.d.getY() / SIZE] = 1;
            RemoveRows(group);

            Form a = nextObj;
            nextObj = Controller.makeRect();
            object = a;
            group.getChildren().addAll(a.a, a.b, a.c, a.d);
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

    private boolean moveA(Form form) {
        return (MESH[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1] == 1);
    }

    private boolean moveB(Form form) {
        return (MESH[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1] == 1);
    }

    private boolean moveC(Form form) {
        return (MESH[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1] == 1);
    }

    private boolean moveD(Form form) {
        return (MESH[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1] == 1);
    }

    //To Make Sure That There Is An Empty Place To Rotate The Shape
    private boolean cB(Rectangle rect, int x, int y) {
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
