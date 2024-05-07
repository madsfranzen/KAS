package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginVindue extends Stage {

    TextField txfBrugernavn = new TextField();
    PasswordField psfKodeord = new PasswordField();

    Label lblBrugernavn = new Label("Brugernavn :");
    Label lblKodeOrd = new Label("Kodeord :");


    Button btnLogin = new Button("Login");
    Button btnOpretBruger = new Button("Opret Bruger");


    public LoginVindue() {
        this.setTitle("KonferenceAdministrationsSystem");
        BorderPane pane = new BorderPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.setResizable(false);
    }

    // -------------------------------------------------------------------------

    private void initContent(BorderPane pane) {
        GridPane gridPane = new GridPane();
        pane.setCenter(gridPane);

        gridPane.setMinWidth(700);
        gridPane.setMinHeight(600);

        gridPane.setHgap(25);
        gridPane.setVgap(25);
        gridPane.setPadding(new Insets(100));

        gridPane.setAlignment(Pos.CENTER);

        Label lblHeading = new Label("KAS");
        lblHeading.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        gridPane.add(lblHeading, 0, 0);

        gridPane.add(txfBrugernavn, 0, 2);
        gridPane.add(psfKodeord, 0, 4);
        txfBrugernavn.setPromptText("Brugernavn");
        psfKodeord.setPromptText("Koderord");

        btnLogin.setOnAction(event -> loginAction());
        btnOpretBruger.setOnAction(event -> opretBrugerAction());
        gridPane.add(btnLogin, 0, 5);
        gridPane.add(btnOpretBruger, 0, 6);
        gridPane.add(lblBrugernavn, 0, 1);
        gridPane.add(lblKodeOrd, 0, 3);

        GridPane.setHalignment(lblBrugernavn, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(lblKodeOrd, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(btnLogin, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(btnOpretBruger, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(lblHeading, javafx.geometry.HPos.CENTER);
    }


    public void loginAction() {
        AdminVindue adminVindue = new AdminVindue();
        this.hide();
        adminVindue.show();
    }

    public void opretBrugerAction() {
        TilmeldingsVindue tilmeldingsVindue = new TilmeldingsVindue();
        this.hide();
        tilmeldingsVindue.show();
    }
}