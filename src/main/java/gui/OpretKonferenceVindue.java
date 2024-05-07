package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Hotel;
import model.HotelTilvalg;
import model.Konference;
import model.Udflugt;

import java.time.LocalDate;
import java.util.ArrayList;


public class OpretKonferenceVindue extends Stage {

    Konference konference;

    Border border = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2), new Insets(-2)));

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


    public OpretKonferenceVindue(Konference konference) {
        this.setTitle("Opret Konference");
        BorderPane pane = new BorderPane();
        this.initContent(pane);
        this.konference = konference;
        this.setKonference();
        this.changeLooks();
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

        dpStart.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (dpStart.getValue() != null && dpSlut.getValue() != null) {
                dpUdflugt.setDisable(false);
            }
        });
        dpSlut.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (dpStart.getValue() != null && dpSlut.getValue() != null) {
                dpUdflugt.setDisable(false);
            }
        });

        txfPris.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
        txfPris.textProperty().addListener((obs, oldv, newv) -> {
            try {
                txfPris.getTextFormatter().getValueConverter().fromString(newv);
                // Hvis ingen exception, er den ok
                txfPris.setBorder(null);
            } catch (NumberFormatException e) {
                txfPris.setBorder(border);
            }
        });

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

        txfUdflugtPris.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
        txfUdflugtPris.textProperty().addListener((obs, oldv, newv) -> {
            try {
                txfUdflugtPris.getTextFormatter().getValueConverter().fromString(newv);
                // Hvis ingen exception, er den ok
                txfUdflugtPris.setBorder(null);
            } catch (NumberFormatException e) {
                txfUdflugtPris.setBorder(border);
            }
        });

        dpUdflugt.setDisable(true);

        tilpasFarverForForestilling(dpUdflugt);

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
        btnOpretKonference.setOnAction(event -> opretKonference());
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
        String navn = txfUdflugtNavn.getText();
        double pris;
        if (txfPris.getText().isBlank()) {
            pris = 0;
        } else pris = Double.parseDouble(txfUdflugtPris.getText());

        // Tjekker for tomme felter
        if (navn.isEmpty()) {
            showAlert("Indtast et navn.");
        } else if (dpUdflugt.getValue() == null) {
            showAlert("Indtast en Dato.");
        } else {
            // Opretter udflugt og clear felter
            Udflugt udflugt = new Udflugt(navn, pris, dpUdflugt.getValue(), chbMiddag.isSelected());
            udflugter.add(udflugt);
            lvwUdflugter.getItems().setAll(udflugter);
            txfUdflugtNavn.clear();
            txfUdflugtPris.clear();
            dpUdflugt.setValue(null);
        }
    }

    public void sletUdflugt() {
        for (Object item : lvwUdflugter.getSelectionModel().getSelectedItems()) {
            udflugter.remove(item);
        }
        lvwUdflugter.getItems().setAll(udflugter);
    }

    public void opretKonference() {
        String navn = txfKonferenceNavn.getText();
        String beskrivelse = txaBeskrivelse.getText();
        String lokation = txfLokation.getText();
        double pris;
        if (txfPris.getText().isEmpty()) {
            pris = 0;
        } else pris = Double.parseDouble(txfPris.getText());
        LocalDate startDato = dpStart.getValue();
        LocalDate slutDato = dpSlut.getValue();

        // Tjekker for tomme felter og Errors
        if (navn.isEmpty()) {
            showAlert("Indtast et navn.");
        } else if (beskrivelse.isEmpty()) {
            showAlert("Indtast en beskrivelse.");
        } else if (lokation.isEmpty()) {
            showAlert("Indtast en lokation.");
        } else if (pris == 0) {
            showAlert("Indtast en pris.");
        } else if (dpStart.getValue() == null) {
            showAlert("Indtast en Start Dato.");
        } else if (dpSlut.getValue() == null) {
            showAlert("Indtast en Slut Dato.");
        } else if (startDato.isAfter(slutDato)) {
            showAlert("Slut Dato skal være senere end Start Dato.");
        } else if (udflugter.isEmpty()) {
            showAlert("Opret venligst udflugter. En Konference må ikke være foruden udflugter.");
        } else if (tilknyttedeHoteller.isEmpty()) {
            showAlert("Tilknyt venligst mindst et hotel.");
        } else if (konference != null) {
            Controller.opdaterKonference(konference, navn, beskrivelse, lokation, startDato, slutDato, pris, tilknyttedeHoteller, udflugter);
            VindueManager.adminVindue.updateGUI();
            this.hide();
        } else {

            Konference konference = Controller.opretKonference(navn, beskrivelse, lokation, startDato, slutDato, pris);
            // Tilføj Udflugterne til Konferencen
            for (Udflugt udflugt : udflugter) {
                Controller.tilføjUdflugtTilKonference(konference, udflugt);
            }
            for (Hotel hotel : tilknyttedeHoteller) {
                Controller.tilføjHotelTilKonference(konference, hotel);
            }

            clearGUI();
            VindueManager.adminVindue.updateGUI();
            this.hide();
        }
    }

    public void showAlert(String infoText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText(null);
        alert.setContentText(infoText);
        alert.showAndWait();
    }

    public void clearGUI() {
        txfKonferenceNavn.clear();
        txfPris.clear();
        dpStart.setValue(null);
        dpSlut.setValue(null);
        txfLokation.clear();
        txaBeskrivelse.clear();
        tilknyttedeHoteller.clear();
        udflugter.clear();
        lvwUdflugter.getItems().setAll(udflugter);
        lvwTilkHoteller.getItems().setAll(tilknyttedeHoteller);
        chbMiddag.setSelected(false);
    }

    public void setKonference() {
        txfKonferenceNavn.setText(konference.getNavn());
        txfPris.setText(String.valueOf(konference.getPris()));
        txfLokation.setText(konference.getLokation());
        dpStart.setValue(konference.getStartDato());
        dpSlut.setValue(konference.getSlutDato());
        txaBeskrivelse.setText(konference.getBeskrivelse());
        tilknyttedeHoteller.addAll(konference.getHoteller());
        lvwTilkHoteller.getItems().setAll(tilknyttedeHoteller);
        udflugter.addAll(konference.getUdflugter());
        lvwUdflugter.getItems().setAll(udflugter);
    }

    public void changeLooks() {
        btnOpretKonference.setText("Opdater Konference");
    }

    private void tilpasFarverForForestilling(DatePicker dp) {
        dp.setDayCellFactory(new Callback<DatePicker, DateCell>() {
                                 @Override
                                 public DateCell call(DatePicker param) {
                                     return new DateCell() {
                                         @Override
                                         public void updateItem(LocalDate item, boolean empty) {
                                             super.updateItem(item, empty);
                                             if (item != null) {
                                                 LocalDate startDato = dpStart.getValue();
                                                 LocalDate slutDato = dpSlut.getValue();
                                                 if ((startDato != null && item.isEqual(startDato)) || (slutDato != null && item.isEqual(slutDato)) || (item.isAfter(startDato) && item.isBefore(slutDato))) {
                                                     setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(0), Insets.EMPTY)));
                                                 } else
                                                     setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), Insets.EMPTY)));
                                             }
                                         }
                                     };
                                 }
                             }
        );
    }

}
