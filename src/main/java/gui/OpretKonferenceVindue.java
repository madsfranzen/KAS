package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Hotel;
import model.HotelTilvalg;
import model.Konference;
import model.Udflugt;

import java.time.LocalDate;
import java.util.ArrayList;


public class OpretKonferenceVindue extends Stage {

    TextField txfKonferenceNavn = new TextField();
    DatePicker dpStart = new DatePicker();
    DatePicker dpSlut = new DatePicker();
    TextField txfLokation = new TextField();
    TextArea txaBeskrivelse = new TextArea();
    TextField txfPris = new TextField();

    TextField txfUdflugtNavn = new TextField();
    TextField txfUdflugtPris = new TextField();
    DatePicker dpUdflugt = new DatePicker();
    CheckBox chbMiddag = new CheckBox("Middag Inkluderet");
    ListView lvwUdflugter = new ListView<>();
    Button btnOpretUdflugt = new Button("Opret Udflugt");
    Button btnSletUdflugt = new Button("Slet Udflugt");

    ListView lvwHoteller = new ListView<>();
    ListView lvwTilkHoteller = new ListView<>();
    Button btnTilføjHotel = new Button("Tilføj ->");
    Button btnFjernHotel = new Button("<- Fjern");
    Button btnOpretKonference = new Button("Opret Konference");

    ArrayList<Hotel> tilknyttedeHoteller = new ArrayList<>();
    ArrayList<Udflugt> udflugter = new ArrayList<>();

    public OpretKonferenceVindue() {
        this.setTitle("Opret Konference");
        BorderPane pane = new BorderPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.setResizable(false);
    }

    private void initContent(BorderPane pane) {
        GridPane gridPane = new GridPane();
        pane.setCenter(gridPane);

        GridPane paneL = new GridPane();
        GridPane paneM = new GridPane();
        GridPane paneR = new GridPane();
        gridPane.add(paneL, 0, 0);
        gridPane.add(paneM, 1, 0);
        gridPane.add(paneR, 2, 0);

        paneL.setHgap(10);
        paneL.setVgap(10);
        paneL.setPadding(new Insets(20));
        paneM.setHgap(10);
        paneM.setVgap(10);
        paneM.setPadding(new Insets(20));
        paneR.setHgap(10);
        paneR.setVgap(10);
        paneR.setPadding(new Insets(20));


        //========================================= LEFT ========================================//

        Label lblKonference = new Label("Konference");
        Label lblKonferenceNavn = new Label("Navn");
        Label lblStartDato = new Label("Start Dato");
        Label lblSlutDato = new Label("Slut Dato");
        Label lblLokation = new Label("Lokation");
        Label lblBeskrivelse = new Label("Beskrivelse");
        Label lblPris = new Label("Pris");

        paneL.add(lblKonference, 1, 0);
        paneL.add(lblKonferenceNavn, 0, 1);
        paneL.add(txfKonferenceNavn, 1, 1);
        paneL.add(lblPris, 0, 2);
        paneL.add(txfPris, 1, 2);
        paneL.add(lblStartDato, 0, 3);
        paneL.add(dpStart, 1, 3);
        paneL.add(lblSlutDato, 0, 4);
        paneL.add(dpSlut, 1, 4);
        paneL.add(lblLokation, 0, 5);
        paneL.add(txfLokation, 1, 5);
        paneL.add(lblBeskrivelse, 0, 6);
        paneL.add(txaBeskrivelse, 1, 6);

        txfKonferenceNavn.setMaxWidth(190);
        txfLokation.setMaxWidth(190);
        txaBeskrivelse.setMaxWidth(220);
        txaBeskrivelse.setMaxHeight(100);

        //======================================== MID =======================================//

        Label lblUdflugt = new Label("Udlflugt");
        Label lblUdflugtNavn = new Label("Navn");
        Label lblUdflugtPris = new Label("Pris");
        paneM.add(lblUdflugt, 0, 0);
        paneM.add(lblUdflugtNavn, 0, 1);
        paneM.add(txfUdflugtNavn, 1, 1);
        paneM.add(lblUdflugtPris, 0, 2);
        paneM.add(txfUdflugtPris, 1, 2);
        Label lblDatoUdflugt = new Label("Dato");
        paneM.add(lblDatoUdflugt, 0, 3);
        paneM.add(dpUdflugt, 1, 3, 1, 1);
        dpUdflugt.setMaxWidth(150);
        HBox hbox = new HBox(chbMiddag);
        paneM.add(hbox, 0, 4, 2, 1);
        paneM.add(lvwUdflugter, 0, 5, 2, 2);
        HBox btnBox = new HBox(btnOpretUdflugt, btnSletUdflugt);
        paneM.add(btnBox, 0, 7, 2, 1);
        btnBox.setSpacing(20);
        btnBox.setAlignment(Pos.CENTER);

        chbMiddag.setAlignment(Pos.CENTER);
        lvwUdflugter.setMaxWidth(220);
        lvwUdflugter.setMaxHeight(140);
        lvwUdflugter.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        btnOpretUdflugt.setOnAction(event -> opretUdflugt());
        btnSletUdflugt.setOnAction(event -> sletUdflugt());

        //=================================== RIGHT =========================================//

        Label lblHoteller = new Label("Hoteller");
        Label lblTilknHoteller = new Label("Tilknyttede Hoteller");


        paneR.add(lblHoteller, 0, 0);
        paneR.add(lblTilknHoteller, 2, 0);
        paneR.add(lvwHoteller, 0, 1, 1, 3);
        paneR.add(lvwTilkHoteller, 2, 1, 1, 3);
        Label lbl = new Label(" ");
        VBox vBox = new VBox(lbl, btnTilføjHotel, btnFjernHotel);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_CENTER);
        paneR.add(vBox, 1, 2, 1, 3);
        paneR.add(btnOpretKonference, 2, 4);

        lvwTilkHoteller.setMaxSize(150, 240);
        lvwHoteller.setMaxSize(150, 240);
        lvwHoteller.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvwTilkHoteller.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        lvwHoteller.getItems().setAll(Controller.getHoteller());

        btnTilføjHotel.setOnAction(event -> tilføjHotel());
        btnFjernHotel.setOnAction(event -> fjernHotel());
    }

    //========================================= METHODS ==========================================//

    public void tilføjHotel() {
        for (Object item : lvwHoteller.getSelectionModel().getSelectedItems()) {
            if (!tilknyttedeHoteller.contains(item))
                tilknyttedeHoteller.add((Hotel) item);
        }
        lvwTilkHoteller.getItems().setAll(tilknyttedeHoteller);
    }

    public void fjernHotel() {
        for (Object item : lvwTilkHoteller.getSelectionModel().getSelectedItems()) {
            if (tilknyttedeHoteller.contains(item))
                tilknyttedeHoteller.remove((Hotel) item);
        }
        lvwTilkHoteller.getItems().setAll(tilknyttedeHoteller);
    }

    public void opretUdflugt() {
        Udflugt udflugt = new Udflugt(txfUdflugtNavn.getText(), Double.parseDouble(txfUdflugtPris.getText()), dpUdflugt.getValue(), chbMiddag.isSelected());
        udflugter.add(udflugt);
        lvwUdflugter.getItems().setAll(udflugter);

        txfUdflugtNavn.clear();
        txfUdflugtPris.clear();
        dpUdflugt.setValue(null);
    }

    public void sletUdflugt() {
        for (Object item : lvwUdflugter.getSelectionModel().getSelectedItems()) {
            udflugter.remove(item);
        }
        lvwUdflugter.getItems().setAll(udflugter);
    }

    public void opretKonference() {
        // Opret Konferencen uden Udflugter
        String navn = txfKonferenceNavn.getText();
        String beskrivelse = txaBeskrivelse.getText();
        String lokation = txfLokation.getText();
        LocalDate startDato = dpStart.getValue();
        LocalDate slutDato = dpSlut.getValue();
        double pris = Double.parseDouble(txfPris.getText());
        Konference konference = Controller.opretKonference(navn, beskrivelse, lokation, startDato, slutDato, pris);

        // Tilføj Udflugterne til Konferencen
        for (Udflugt udflugt : udflugter) {
            Controller.tilføjUdflugtTilKonference(konference, udflugt);
        }
        // Clear GUI
        txfKonferenceNavn.clear();
        txfPris.clear();
        dpStart.setValue(null);
        dpSlut.setValue(null);
        txfLokation.clear();
        txaBeskrivelse.clear();
    }

    public void showAlert(String titel, String infoText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titel);
        alert.setHeaderText(infoText);
        alert.showAndWait();
    }
}
