package teamproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class AboutUs extends Application {
    Label lblEsmat = new Label("Mahmoud Esmat (C)");
    Label lblMuhammed = new Label("Altarouti 👀");
    Label lblGamal = new Label("Mo.Gamal");
    Label lblSamaa = new Label("Samaa Eltohamy");
    Label lblSanaa = new Label("Sanaa Swilam");
    Label lblAya = new Label("Aya Shawky");
    Label lblSara = new Label("Sara Mamdouh");
    Label lblZahraa = new Label("Zahraa Hassan");
    Label lblSalma = new Label("Salma Sameh");
    Label lblYoumna = new Label("Youmna Yasser");
    Label lblNayera = new Label("Nayera Hazem");

    Label lblteamName = new Label("Ｂａｎｄａ Ｔｅａｍ");
    Label lblver = new Label("Version: 11.2");
    Line line = new Line(195, 130, 195, 350);
    Image image2 = new Image(getClass().getResourceAsStream("\\images\\Banda.png"),100,100,false,false);
    ImageView imageView2 = new ImageView(image2);
    Image icon2 = new Image (getClass().getResourceAsStream("images\\Icon.png"));
    HBox teamandimage = new HBox(10);
    VBox esmat1 = new VBox(10);
    VBox esmat2 = new VBox(10);
    HBox esmat3 = new HBox(10);
    BorderPane p1 = new BorderPane();
    Pane names = new Pane();
    VBox root2 = new VBox(20);
    Scene scene2 = new Scene(root2, 450, 675);

    @Override
    public void start(Stage AboutUs) {
        //Top Side
        lblteamName.setTextFill(Color.WHITE);
        lblteamName.setStyle("-fx-color: white;" +
                " -fx-font-weight: bold;" +
                " -fx-font-size: 25;" +
                " -fx-background-color: #607d8b;" +
                " -fx-background-radius: 5px;" +
                " -fx-padding: 10px;");

        //line
        line.setStroke(Color.WHITE);

        //Bottom Side (Version)
        lblver.setTextFill(Color.WHITE);
        lblver.setTranslateY(480);

        lblEsmat.setStyle("-fx-font-size: 25; -fx-font-weight: bold; -fx-text-fill: white ;");
        lblEsmat.setTranslateX(80);
        lblEsmat.setTranslateY(70);

        lblGamal.setStyle("-fx-font-size: 25; -fx-text-fill: #B4DFD9;");
        lblGamal.setTranslateX(230);
        lblGamal.setTranslateY(120);

        lblSara.setStyle("-fx-font-size: 25; -fx-text-fill: #B4DFD9;");
        lblSara.setTranslateY(120);

        lblZahraa.setStyle("-fx-font-size: 25; -fx-text-fill: #B4DFD9;");
        lblZahraa.setTranslateY(170);

        lblMuhammed.setStyle("-fx-font-size: 25; -fx-text-fill: #B4DFD9;");
        lblMuhammed.setTranslateX(230);
        lblMuhammed.setTranslateY(170);

        lblSanaa.setStyle("-fx-font-size: 25; -fx-text-fill: #B4DFD9;");
        lblSanaa.setTranslateY(220);

        lblSamaa.setStyle("-fx-font-size: 25; -fx-text-fill: #B4DFD9;");
        lblSamaa.setTranslateX(230);
        lblSamaa.setTranslateY(220);

        lblSalma.setStyle("-fx-font-size: 25; -fx-text-fill: #B4DFD9;");
        lblSalma.setTranslateX(230);
        lblSalma.setTranslateY(270);

        lblYoumna.setStyle("-fx-font-size: 25; -fx-text-fill: #B4DFD9;");
        lblYoumna.setTranslateY(270);

        lblNayera.setStyle("-fx-font-size: 25; -fx-text-fill: #B4DFD9;");
        lblNayera.setTranslateY(320);

        lblAya.setStyle("-fx-font-size: 25; -fx-text-fill: #B4DFD9; ");
        lblAya.setTranslateX(230);
        lblAya.setTranslateY(320);

        teamandimage.setAlignment(Pos.CENTER);
        teamandimage.getChildren().addAll(lblteamName, imageView2);

        p1.setTop(teamandimage);
        names.getChildren().addAll(lblMuhammed, lblSara, lblSamaa, lblAya, lblYoumna, lblEsmat, lblGamal, lblNayera, lblSalma, lblSanaa, lblZahraa, lblver, line);
        root2.getChildren().addAll(p1,names);
        root2.setStyle("-fx-background-color: #252B39;");
        root2.setPadding(new Insets(30));

        AboutUs.getIcons().add(icon2);
        AboutUs.setResizable(false);
        AboutUs.setTitle("About US");
        AboutUs.setScene(scene2);
        AboutUs.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}