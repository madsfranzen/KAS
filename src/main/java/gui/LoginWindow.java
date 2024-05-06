package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginWindow extends Application {

    TextField txfBrugernavn = new TextField();
    TextField txfKodeord = new TextField();

    @Override
    public void start(Stage stage) {
        stage.setTitle("KonferenceAdministrationsSystem");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // -------------------------------------------------------------------------

    private void initContent(BorderPane pane) {
        GridPane gridPane = new GridPane();
        pane.setCenter(gridPane);

        gridPane.setMinWidth(700);
        gridPane.setMinHeight(600);

        gridPane.setHgap(25);
        gridPane.setVgap(25);
        gridPane.setPadding(new Insets(200));

        gridPane.setAlignment(Pos.CENTER);

        Label lblHeading = new Label("Konference-Administrations-System");
        gridPane.add(lblHeading, 0, 0);
        
        gridPane.add(txfBrugernavn, 0, 1);
        gridPane.add(txfKodeord, 0, 2);
    }
}
