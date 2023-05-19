package tetris.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Form {
    //Creating Cubes
    Rectangle a;
    Rectangle b;
    Rectangle c;
    Rectangle d;
    Color color;
    private String name;
    public int form = 1;

    //Constructor 1
    public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    //Constructor 2
    public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;

        //Identifing Colors
        Color j = Color.web("#B47B1C");
        Color l = Color.web("#FFC300");
        Color o = Color.web("#FF5733");
        Color s = Color.web("#C70039");
        Color t = Color.web("#900C3F");
        Color z = Color.web("#581845");
        Color i = Color.web("#26B37D");

        //Coloring Shapes
        switch (name) {
            case "j":
                color = j;
                break;
            case "l":
                color = l;
                break;
            case "o":
                color = o;
                break;
            case "s":
                color = s;
                break;
            case "t":
                color = t;
                break;
            case "z":
                color = z;
                break;
            case "i":
                color = i;
                break;

        }
        //Filling Cubes
        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);
    }


    //Getter
    public String getName() {
        return name;
    }


    //Changing Forms Of Shapes
    public void changeForm() {
        if (form != 4) {
            form++;
        } else {
            form = 1;
        }
    }
}