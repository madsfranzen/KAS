package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.xml.sax.HandlerBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

//import java.lang.foreign.AddressLayout;
public class OpretBrugerVindue extends Stage {

    private HBox bundHbox = new HBox();

    private HBox topHbox = new HBox();
    private TextField txfBrugernavn = new TextField();
    private TextField txfNavn = new TextField();
    private TextField txfAdresse = new TextField();
    private TextField txfTlf = new TextField();
    private TextField txfBy = new TextField();
    private TextField txfFirma = new TextField();
    private TextField txfLand = new TextField();

    private PasswordField psfKodeord = new PasswordField();

    private Label lblBrugernavn = new Label("Brugernavn :");
    private Label lblKodeOrd = new Label("Kodeord :");

    private Label lblNavn = new Label("Navn :");
    private Label lblAdresse = new Label("Adresse :");

    private Label lblTlf = new Label("Tlf :");
    private Label lblBy = new Label("By :");

    private Label lblFirma = new Label("Firma :");
    private Label lblLand = new Label("Land :");


    private Button btnOpretBruger = new Button("Opret Bruger");

    private Button btnUploadProfilbillede = new Button("Upload Profilbillede");

    public OpretBrugerVindue() {
        this.setTitle("Opret Bruger");
        BorderPane pane = new BorderPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.setResizable(false);
    }

    private void initContent(BorderPane pane) {
        GridPane gridPane = new GridPane();
        pane.setCenter(gridPane);

//        gridPane.setMinWidth(700);
//        gridPane.setMinHeight(600);

        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setPadding(new Insets(20));

        gridPane.setAlignment(Pos.CENTER);


//        lblHeading.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));

        gridPane.add(txfBrugernavn, 1, 4);
        gridPane.add(psfKodeord, 3, 4);


        gridPane.add(txfNavn, 1, 5);
        gridPane.add(txfAdresse, 3, 5);


        gridPane.add(txfTlf, 1, 6);
        gridPane.add(txfBy, 3, 6);


        gridPane.add(txfFirma, 1, 7);
        gridPane.add(txfLand, 3, 7);


        gridPane.add(bundHbox, 0, 8, 7, 1);
        bundHbox.getChildren().add(btnOpretBruger);
        bundHbox.setAlignment(Pos.BASELINE_CENTER);

        gridPane.add(topHbox, 0, 0, 7, 1);
//        topHbox.getChildren().add(btnUploadProfilbillede);
//        topHbox.getChildren().add(profilBillede)
        topHbox.setAlignment(Pos.BASELINE_CENTER);

        btnOpretBruger.setOnAction(event -> opretBrugerAction());
//        btnOpretBruger.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        btnUploadProfilbillede.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));


        gridPane.add(lblBrugernavn, 0, 4);
        gridPane.add(lblKodeOrd, 2, 4);
        gridPane.add(lblNavn, 0, 5);
        gridPane.add(lblAdresse, 2, 5);
        gridPane.add(lblTlf, 0, 6);
        gridPane.add(lblBy, 2, 6);
        gridPane.add(lblFirma, 0, 7);
        gridPane.add(lblLand, 2, 7);

        GridPane.setHalignment(btnOpretBruger, javafx.geometry.HPos.LEFT);
        GridPane.setHalignment(lblBrugernavn, javafx.geometry.HPos.RIGHT);
        GridPane.setHalignment(lblNavn, javafx.geometry.HPos.RIGHT);
        GridPane.setHalignment(lblTlf, javafx.geometry.HPos.RIGHT);
        GridPane.setHalignment(lblFirma, javafx.geometry.HPos.RIGHT);
        GridPane.setHalignment(lblKodeOrd, javafx.geometry.HPos.RIGHT);
        GridPane.setHalignment(lblAdresse, javafx.geometry.HPos.RIGHT);
        GridPane.setHalignment(lblBy, javafx.geometry.HPos.RIGHT);
        GridPane.setHalignment(lblLand, javafx.geometry.HPos.RIGHT);

    }


    public void uploadBilledeAction() {

    }

    public void opretBrugerAction() {
        String brugernavn = txfBrugernavn.getText();
        String kodeord = psfKodeord.getText();
        String navn = txfNavn.getText();
        String adresse = txfAdresse.getText();
        String tlf = txfTlf.getText();
        String by = txfBy.getText();
        String firma = txfFirma.getText();
        String land = txfLand.getText();

        if (brugernavn.isEmpty() || kodeord.isEmpty() || navn.isEmpty() || navn.isEmpty() || adresse.isEmpty() || tlf.isEmpty() || by.isEmpty() || land.isEmpty() || !erRigtigTelefonNummer(tlf)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            if (brugernavn.isEmpty()) {
                alert.setContentText("Du kan ikke oprette en bruger uden brugernavn");
            } else if (kodeord.isEmpty()) {
                alert.setContentText("Du kan ikke oprette en bruger uden kodeord");
            } else if (navn.isEmpty()) {
                alert.setContentText(" Du kan ikke oprette en bruger uden navn");
            } else if (!erRigtigNavn(navn)) {
                alert.setContentText(" Du kan ikke oprette en bruger med tal i dit navn");
            } else if (adresse.isEmpty()) {
                alert.setContentText(" Du kan ikke oprette en bruger uden adresse");
            } else if (tlf.isEmpty()) {
                alert.setContentText(" Du kan ikke oprette en bruger uden telefonnummer");
            } else if (!erRigtigTelefonNummer(tlf)) {
                alert.setContentText(" Du kan ikke have bogstaver i dit telefonnummer");
            } else if (by.isEmpty()) {
                alert.setContentText(" Du kan ikke oprette en bruger uden en by");
            } else if (land.isEmpty()) {
                alert.setContentText(" Du kan ikke oprette en bruger uden et land");
            }
            alert.showAndWait();
        } else if (firma.isEmpty()) {
            Controller.opretDeltager(brugernavn, kodeord, navn, adresse, by, land, tlf);
            this.hide();
        }

        // Kommenteret ud da vi mangler constructor til at oprette deltager med firma

//
//        if (!firma.isEmpty()) {
//            Controller.opretDeltager(brugernavn, kodeord, navn, adresse, by, land, tlf, firma);
//        }
    }

    private boolean erRigtigTelefonNummer(String nummer) {
        if (nummer.length() != 8) {
            return false;
        }
        for (int i = 0; i < nummer.length(); i++) {
            if (!Character.isDigit(nummer.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    // Forstår ik den her funktion, pls explain jona!
    private boolean erRigtigNavn(String navn) {
        for (int i = 0; i < navn.length(); i++) {
            char c = navn.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }
}
