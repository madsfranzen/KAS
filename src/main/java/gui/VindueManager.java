package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class VindueManager extends Application {

    static LoginVindue loginVindue = new LoginVindue();
    static OpretHotelVindue opretHotelVindue = new OpretHotelVindue();
    static OpretBrugerVindue opretBrugerVindue = new OpretBrugerVindue();

    public void start(Stage stage) {
        loginVindue.show();
    }

    public static void visOpretBrugerVindue() {
//        loginVindue.hide();
        opretBrugerVindue.show();
    }

    public static void visOpretHotelVindue() {
        opretHotelVindue.show();
    }


}
