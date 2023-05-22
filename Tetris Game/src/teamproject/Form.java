package teamproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Form {
    //Creating Cubes
    Rectangle a;
    Rectangle b;
    Rectangle c;
    Rectangle d;
    Color color;
    Color colormain = Color.web("#252B39");
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
        this(a,b,c,d);
        this.name = name;

        a.setStroke(colormain);
        b.setStroke(colormain);
        c.setStroke(colormain);
        d.setStroke(colormain);
        a.setStrokeWidth(2);
        b.setStrokeWidth(2);
        c.setStrokeWidth(2);
        d.setStrokeWidth(2);

        //Identifing Colors
        Color j = Color.web("#3AFFFC");
        Color l = Color.web("#DBDFF1");
        Color o = Color.web("#EC37FE");
        Color s = Color.web("#C70039");
        Color t = Color.web("#36FF64");
        Color z = Color.web("#F63C2E");
        Color i = Color.web("#FED23B");

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