package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Deltager;

import java.io.File;


public class VindueManager extends Application {

    static LoginVindue loginVindue = new LoginVindue();
    static OpretHotelVindue opretHotelVindue = new OpretHotelVindue();
    static OpretBrugerVindue opretBrugerVindue = new OpretBrugerVindue();
    static OpretKonferenceVindue opretKonferenceVindue = new OpretKonferenceVindue();
    static AdminVindue adminVindue = new AdminVindue();

    public void start(Stage stage) {

        // INITIALIZE IMAGES
        File file = new File("src/main/resources/pb.png");
        Image image = new Image(file.toURI().toString());
        for (Deltager deltager : Controller.getDeltagere()) {
            deltager.setImageChosen(image);
        }
        // Åben Login Vindue og Start Program
        loginVindue.show();
    }

    public static void visOpretBrugerVindue() {
        opretBrugerVindue.clearGUI();
        opretBrugerVindue.show();
    }

    public static void visOpretHotelVindue() {
        opretHotelVindue.show();
    }


}
