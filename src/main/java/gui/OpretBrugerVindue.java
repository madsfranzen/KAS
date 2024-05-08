package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Deltager;

import java.io.File;

public class OpretBrugerVindue extends Stage {

    private Deltager deltager;
    private final HBox bundHbox = new HBox();

    private final VBox topVbox = new VBox();
    private final TextField txfBrugernavn = new TextField();
    private final TextField txfNavn = new TextField();
    private final TextField txfAdresse = new TextField();
    private final TextField txfTlf = new TextField();
    private final TextField txfBy = new TextField();
    private final TextField txfFirma = new TextField();
    private final TextField txfLand = new TextField();

    private final PasswordField psfKodeord = new PasswordField();

    private final Label lblBrugernavn = new Label("Brugernavn :");
    private final Label lblKodeOrd = new Label("Kodeord :");

    private final Label lblNavn = new Label("Navn :");
    private final Label lblAdresse = new Label("Adresse :");

    private final Label lblTlf = new Label("Tlf :");
    private final Label lblBy = new Label("By :");

    private final Label lblFirma = new Label("Firma :");
    private final Label lblLand = new Label("Land :");


    private final Button btnOpretBruger = new Button("Opret Bruger");

    private final Button btnUploadProfilbillede = new Button("Upload Profilbillede");


    private File file = new File("src/main/resources/pb.png");
    private Image image = new Image(file.toURI().toString());
    private ImageView imgProfile = new ImageView(image);

    public OpretBrugerVindue() {
        this.setTitle("Opret Bruger");
        BorderPane pane = new BorderPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.setResizable(false);
    }

    public OpretBrugerVindue(Deltager deltager) {
        this.deltager = deltager;
        this.setTitle("Opdater Bruger");
        BorderPane pane = new BorderPane();
        this.initContent(pane);
        this.setDeltager();
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.setResizable(false);
    }

    private void initContent(BorderPane pane) {
        GridPane gridPane = new GridPane();
        pane.setCenter(gridPane);
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setPadding(new Insets(20));
        gridPane.setAlignment(Pos.CENTER);

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

        gridPane.add(topVbox, 0, 0, 7, 1);

        imgProfile.setFitWidth(250);
        imgProfile.setFitHeight(250);
        imgProfile.setPreserveRatio(true);
        topVbox.getChildren().addAll(imgProfile, btnUploadProfilbillede);
        topVbox.setAlignment(Pos.BASELINE_CENTER);
        topVbox.setSpacing(15);

        btnOpretBruger.setOnAction(event -> opretBrugerAction());
        if (deltager != null) {
            btnOpretBruger.setText("Opdater bruger");
        }

        btnUploadProfilbillede.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
        btnUploadProfilbillede.setOnAction(e -> uploadBilledeAction());

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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File fileImg = fileChooser.showOpenDialog(this);
        Image imageChosen = new Image(fileImg.toURI().toString());
        imgProfile.setImage(imageChosen);
        imgProfile.setFitWidth(250);
        imgProfile.setFitHeight(250);
        imgProfile.setPreserveRatio(true);
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
        Image imageChosen = imgProfile.getImage();

        if (brugernavn.isEmpty() || kodeord.isEmpty() || navn.isEmpty() || adresse.isEmpty() || tlf.isEmpty() || by.isEmpty() || land.isEmpty() || !erRigtigTelefonNummer(tlf)) {
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
                alert.setContentText(" Tast venligst et rigtigt telefonnummer ind");
            } else if (by.isEmpty()) {
                alert.setContentText(" Du kan ikke oprette en bruger uden en by");
            } else if (land.isEmpty()) {
                alert.setContentText(" Du kan ikke oprette en bruger uden et land");
            }
            alert.showAndWait();
        } else {
            if (deltager != null) {
                Controller.opdaterDeltager(deltager, brugernavn, kodeord, navn, adresse, by, land, tlf);
                deltager.setImageChosen(imageChosen);
                this.hide();
            } else {
                deltager = Controller.opretDeltager(brugernavn, kodeord, navn, adresse, by, land, tlf);
                deltager.setImageChosen(imageChosen);
                VindueManager.adminVindue.updateGUI();
                this.hide();
            }
        }
    }


    public void setDeltager() {
        txfBrugernavn.setText(deltager.getBrugernavn());
        psfKodeord.setText(deltager.getKodeord());
        txfNavn.setText(deltager.getNavn());
        txfAdresse.setText(deltager.getAdresse());
        txfBy.setText(deltager.getBy());
        txfFirma.setText(deltager.getFirma());
        txfLand.setText(deltager.getLand());
        txfTlf.setText(deltager.getTlf());
        imgProfile.setImage(deltager.getImageChosen());
    }

// ==================== Helpers ===================

    /* Tjekker om telefonnummeret indholder tal og ikke bogstaver samt om de er 8 cifre */
    private boolean erRigtigTelefonNummer(String nummer) {
        boolean erRigtig = true;
        if (nummer.length() != 8) {
            erRigtig = false;
        }
        for (int i = 0; i < nummer.length(); i++) {
            if (!Character.isDigit(nummer.charAt(i))) {
                erRigtig = false;
            }
        }
        return erRigtig;
    }

    /* Tjekker om navnet kun er bogstaver, "-" og mellemrum */
    private boolean erRigtigNavn(String navn) {
        boolean erRigtigt = true;
        for (int i = 0; i < navn.length(); i++) {
            char c = navn.charAt(i);
            if (!Character.isLetter(c) && c != ' ' && c != '-') {
                erRigtigt = false;
            }
        }
        return erRigtigt;
    }

    public Deltager getDeltager() {
        return deltager;
    }

    public void clearGUI() {
        txfBrugernavn.clear();
        psfKodeord.clear();
        txfLand.clear();
        txfAdresse.clear();
        txfFirma.clear();
        txfTlf.clear();
        txfBy.clear();
        txfNavn.clear();
        imgProfile.setImage(image);
    }
}

