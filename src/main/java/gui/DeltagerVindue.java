package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
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
import javafx.stage.Stage;
import model.Deltager;
import model.Konference;
import model.Tilmelding;
import storage.Storage;

import java.io.File;

public class DeltagerVindue extends Stage {

    Deltager deltager = Storage.getDeltagere().getLast();

    ListView lvwKonferencer = new ListView<>();
    TextArea txaKonferenceInfo = new TextArea();
    Button btnTilmeld = new Button("Tilmeld");
    Button btnAfmeld = new Button("Afmeld");
    Button btnLogUd = new Button("Log Ud");
    Button btnOpdater = new Button("Opdater");

    TextField txfNavn = new TextField();
    TextField txfAdresse = new TextField();
    TextField txfTlf = new TextField();
    TextField txfBy = new TextField();
    TextField txfFirma = new TextField();
    TextField txfLand = new TextField();

    ListView lvwTilmeldinger = new ListView<>();
    Button btnOpdaterTilmelding = new Button("Opdater Tilmelding");

    public DeltagerVindue() {
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.setResizable(false);
    }

    private void initContent(BorderPane pane) {

        GridPane mainPane = new GridPane();
        GridPane gridPaneL = new GridPane();
        GridPane gridPaneR = new GridPane();

        gridPaneL.setGridLinesVisible(false);
        gridPaneR.setGridLinesVisible(false);

        int width = 800;
        int height = 600;
        mainPane.setMinSize(width, height);
        mainPane.setMaxSize(width, height);
        mainPane.setAlignment(Pos.CENTER);

        pane.setCenter(mainPane);
        mainPane.add(gridPaneL, 0, 0);
        mainPane.add(gridPaneR, 1, 0);
        gridPaneL.setMinSize((width / 5) * 2, height);
        gridPaneL.setMaxSize((width / 5) * 2, height);
        gridPaneR.setMinSize((width / 5) * 3, height);
        gridPaneR.setMaxSize((width / 5) * 3, height);
        gridPaneL.setHgap(10);
        gridPaneL.setVgap(10);
        gridPaneL.setPadding(new Insets(20));
        gridPaneR.setHgap(10);
        gridPaneR.setVgap(10);
        gridPaneR.setPadding(new Insets(20));

        //================================ VENSTRE SIDE =========================//

        Label lblKonferencer = new Label("Konferencer");
        Label lblKonferenceInfo = new Label("Konference Info");
        gridPaneL.add(lblKonferencer, 0, 0);
        gridPaneL.add(lvwKonferencer, 0, 1, 2, 1);
        gridPaneL.add(lblKonferenceInfo, 0, 2);
        gridPaneL.add(txaKonferenceInfo, 0, 3, 2, 1);
        HBox btnBox = new HBox();
        btnBox.getChildren().setAll(btnTilmeld, btnAfmeld);
        btnBox.setSpacing(25);
        btnBox.setAlignment(Pos.CENTER);
        gridPaneL.add(btnBox, 0, 4, 2, 1);

        btnTilmeld.setOnAction(e -> opretTilmelding());
        btnAfmeld.setOnAction(e -> afmeld());

        ChangeListener<Konference> Konferencelistener = (ov, o, n) -> this.selectedKonferenceChanged();
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(Konferencelistener);


        //================================ HØJRE SIDE =========================//
        File file = new File("src/main/resources/pb.png");
        Image image = new Image(file.toURI().toString());
        ImageView imgProfile = new ImageView(image);
        imgProfile.setFitWidth(200);
        imgProfile.setFitHeight(200);
        imgProfile.setPreserveRatio(true);
        HBox imgBox = new HBox(imgProfile);
        gridPaneR.add(imgBox, 0, 0, 1, 2);
        imgBox.setPadding(new Insets(20, 0, 20, 50));
        VBox vBox1 = new VBox();
        vBox1.getChildren().setAll(btnLogUd, btnOpdater);
        gridPaneR.add(vBox1, 1, 0, 1, 2);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setSpacing(25);

        btnLogUd.setOnAction(e -> logUd());

        GridPane infoPane = new GridPane();
        infoPane.setHgap(10);
        infoPane.setVgap(25);
        infoPane.setGridLinesVisible(false);
        gridPaneR.add(infoPane, 0, 2, 2, 2);
        Label lblNavn = new Label("Navn");
        Label lblAdresse = new Label("Adresse");
        Label lblTlf = new Label("Tlf");
        Label lblBy = new Label("By");
        Label lblFirma = new Label("Firma");
        Label lblLand = new Label("Land");
        infoPane.add(lblNavn, 0, 0);
        infoPane.add(lblAdresse, 2, 0);
        infoPane.add(lblTlf, 0, 1);
        infoPane.add(lblBy, 2, 1);
        infoPane.add(lblFirma, 0, 2);
        infoPane.add(lblLand, 2, 2);

        infoPane.add(txfNavn, 1, 0);
        infoPane.add(txfAdresse, 3, 0);
        infoPane.add(txfTlf, 1, 1);
        infoPane.add(txfBy, 3, 1);
        infoPane.add(txfFirma, 1, 2);
        infoPane.add(txfLand, 3, 2);
        txfNavn.setEditable(false);
        txfAdresse.setEditable(false);
        txfTlf.setEditable(false);
        txfBy.setEditable(false);
        txfFirma.setEditable(false);
        txfLand.setEditable(false);

        gridPaneR.add(lvwTilmeldinger, 0, 4, 2, 1);
        lvwTilmeldinger.setMaxHeight(200);
        HBox hbox = new HBox(btnOpdaterTilmelding);
        gridPaneR.add(hbox, 0, 5, 3, 2);
        hbox.setAlignment(Pos.CENTER);

        btnOpdaterTilmelding.setOnAction(e -> opdaterTilmelding());
        initGUI();
    }

    public void initGUI() {
        txfNavn.setText(deltager.getNavn());
        txfAdresse.setText(deltager.getAdresse());
        txfTlf.setText(deltager.getTlf());
        txfBy.setText(deltager.getBy());
        txfFirma.setText(deltager.getFirma());
        txfLand.setText(deltager.getLand());
        lvwTilmeldinger.getItems().setAll(deltager.getTilmeldinger());
        lvwKonferencer.getItems().setAll(Storage.getKonferencer());
    }

    public void selectedKonferenceChanged() {
        Konference konference = (Konference) lvwKonferencer.getSelectionModel().getSelectedItem();
        // Opdater Konference Info
        if (konference != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format(konference.getNavn() + "\n"));
            sb.append(String.format("Pris Enkelt: %.2f \n", konference.getPris()));
            sb.append(String.format(konference.getBeskrivelse() + "\n"));
            sb.append(String.format(konference.getLokation() + "\n"));
            sb.append(String.format("Start Dato: " + konference.getStartDato() + "\n"));
            sb.append(String.format("Slut Dato: " + konference.getSlutDato() + "\n"));
            txaKonferenceInfo.setText(sb.toString());

        }
    }


    //========================== Actions =========================

    public void logUd() {
        LoginVindue loginVindue = new LoginVindue();
        this.hide();
        loginVindue.show();
    }

    public void opretTilmelding() {
        Konference konference = (Konference) lvwKonferencer.getSelectionModel().getSelectedItem();
        if (konference != null) {
            TilmeldingsVindue tilmeldingsVindue = new TilmeldingsVindue(konference, deltager);
            tilmeldingsVindue.showAndWait();
            initGUI();
        }
    }

    public void opdaterTilmelding(){
       Tilmelding tilmelding = (Tilmelding) lvwTilmeldinger.getSelectionModel().getSelectedItem();
       if (tilmelding != null){
           Konference konference = (Konference) tilmelding.getKonference();
           TilmeldingsVindue tilmeldingsVindue = new TilmeldingsVindue(konference, deltager, tilmelding);
           tilmeldingsVindue.showAndWait();
       }
    }

    public void afmeld() {
        Tilmelding tilmelding = (Tilmelding) lvwTilmeldinger.getSelectionModel().getSelectedItem();
        if (tilmelding != null) {
            Konference konference = (Konference) tilmelding.getKonference();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Er du sikker?");
            alert.setContentText("Vil du slette din tilmelding?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Controller.sletTilmelding(konference, deltager, tilmelding);
                    initGUI();
                } else {
                    // Do nothing
                }
            });
        }
    }
}
