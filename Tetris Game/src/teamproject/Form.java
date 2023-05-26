package teamproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Form {
    //Creating Cubes
    //The names of the rectangles that will be displayed (4 of them)
    Rectangle a, b, c, d;
    Color color, colormain = Color.web("#252B39");
    private String name;
    public int form = 1;//Initial value for the form

    //Constructor 1
    public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d) { /// It is a Constructor
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    //Constructor 2
    public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {
        this(a,b,c,d); //The first constructor has been called ( Constructor Shaining )
        this.name = name;

        //It is used to set the color of the outer line of the shape (making a frame for the rectangle)
        a.setStroke(colormain);
        b.setStroke(colormain);
        c.setStroke(colormain);
        d.setStroke(colormain);

        //thickness of the rectangle frame
        a.setStrokeWidth(2);
        b.setStrokeWidth(2);
        c.setStrokeWidth(2);
        d.setStrokeWidth(2);

        //Identifing Colors
        //Code used to specify the color of shapes
        Color j = Color.web("#0F4AF5");
        Color l = Color.web("#F11AA0");
        Color o = Color.web("#87CEEB");
        Color s = Color.web("#1AF1A0");
        Color t = Color.web("#36FF64");
        Color z = Color.web("#F63C2E");
        Color i = Color.web("#FED23B");
        Color sss=Color.web("#FED23B");
        //This code is used to set the appropriate color for the selected shape, which is chosen according to the value of the name variable
        switch (name) {
            //If the value of the name variable is equal to (J), then the color assigned to the shape (J) is chosen.
            case "j":
                color = j;
                break;
            //If the value of the name variable is equal to (l), then the color assigned to the shape (l) is chosen.
            case "l":
                color = l;
                break;
            //If the value of the name variable is equal to (o), then the color assigned to the shape (o) is chosen.
            case "o":
                color =o;
                break;
            //If the value of the name variable is equal to (s), then the color assigned to the shape (s) is chosen.
            case "s":
                color = s;
                break;
            //If the value of the name variable is equal to (t), then the color assigned to the shape (t) is chosen.
            case "t":
                color = t;
                break;
            //If the value of the name variable is equal to (z), then the color assigned to the shape (z) is chosen.
            case "z":
                color = z;
                break;
            //If the value of the name variable is equal to (i), then the color assigned to the shape (i) is chosen.
            case "i":
                color =i;
                break;
        }
        //Filling Cubes
        //Used to fill the shape with the specified color
        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);
    }

    //Getter
    //Returns the value of random colors when called
    public String getName() {
        return name;
    }

    //Changing Forms Of Shapes
    //A function that wraps a shape (consisting of four rectangles) in four directions
    public void changeForm() {
        if (form != 4) {
            form++;
        } else {
            form = 1;
        }
    }

}