package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Hotel;
import model.HotelTilvalg;
import model.Konference;

import java.util.ArrayList;

public class OpretHotelVindue extends Stage {

    Hotel hotel;

    private ArrayList<HotelTilvalg> tilvalgArr = new ArrayList<>();

    Border border = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2), new Insets(-2)));

    Button btnOpretTilvalg = new Button("Opret Tilvalg");
    Button btnSletTilvalg = new Button("Slet Tilvalg");
    Button btnOpretHotel = new Button("Opret Hotel");

    TextField txfNavnTilvalg = new TextField();
    TextField txfPris = new TextField();
    ListView lvwTilvalg = new ListView<>();

    TextField txfNavnHotel = new TextField();
    TextField txfPrisSingle = new TextField();
    TextField txfPrisDobbelt = new TextField();

    public OpretHotelVindue() {
        this.setTitle("Opret Hotel");
        BorderPane pane = new BorderPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.setResizable(false);
    }

    public OpretHotelVindue(Hotel hotel) {
        this.setTitle("Opret Hotel");
        BorderPane pane = new BorderPane();
        this.initContent(pane);
        this.hotel = hotel;
        this.setHotel();
        this.changeLooks();
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.setResizable(false);
    }

    // -------------------------------------------------------------------------

    private void initContent(BorderPane pane) {
        GridPane gridPane = new GridPane();
        pane.setCenter(gridPane);

        GridPane paneL = new GridPane();
        GridPane paneR = new GridPane();
        gridPane.add(paneL, 0, 0);
        gridPane.add(paneR, 1, 0);

        paneL.setHgap(10);
        paneL.setVgap(10);
        paneL.setPadding(new Insets(20));
        paneR.setHgap(10);
        paneR.setVgap(10);
        paneR.setPadding(new Insets(20));

        //==================== VENSTRE SIDE =====================//
        Label lblTilvalg = new Label("Tilvalg");
        Label lblNavnTilvalg = new Label("Navn");
        Label lblPrisTilvalg = new Label("Pris");
        paneL.add(lblTilvalg, 0, 0);
        paneL.add(lblNavnTilvalg, 0, 1);
        paneL.add(txfNavnTilvalg, 1, 1);
        paneL.add(lblPrisTilvalg, 0, 2);
        paneL.add(txfPris, 1, 2);
        paneL.add(lvwTilvalg, 0, 3, 2, 2);
        lvwTilvalg.setMaxHeight(100);
        lvwTilvalg.setMaxWidth(225);
        HBox hBox = new HBox(btnOpretTilvalg, btnSletTilvalg);
        hBox.setSpacing(25);
        hBox.setAlignment(Pos.CENTER);
        paneL.add(hBox, 0, 5, 2, 1);

        btnOpretTilvalg.setOnAction(event -> opretTilvalg());
        btnSletTilvalg.setOnAction(event -> sletTilvalg());


        //==================== HØJRE SIDE =====================//
        Label lblHotel = new Label("Hotel");
        Label lblHotelNavn = new Label("Navn");
        Label lblSingle = new Label("Pris Single");
        Label lblDobbelt = new Label("Pris Dobbelt");
        paneR.add(lblHotel, 1, 0);
        paneR.add(lblHotelNavn, 0, 1);
        paneR.add(txfNavnHotel, 1, 1);
        paneR.add(lblSingle, 0, 2);
        paneR.add(txfPrisSingle, 1, 2);
        paneR.add(lblDobbelt, 0, 3);
        paneR.add(txfPrisDobbelt, 1, 3);
        Label lbl = new Label(" ");
        paneR.add(lbl, 0, 4);
        paneR.add(btnOpretHotel, 1, 5);

        btnOpretHotel.setOnAction(event -> opretHotel());

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
        txfPrisSingle.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
        txfPrisSingle.textProperty().addListener((obs, oldv, newv) -> {
            try {
                txfPrisSingle.getTextFormatter().getValueConverter().fromString(newv);
                // Hvis ingen exception, er den ok
                txfPrisSingle.setBorder(null);
            } catch (NumberFormatException e) {
                txfPrisSingle.setBorder(border);
            }
        });
        txfPrisDobbelt.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
        txfPrisDobbelt.textProperty().addListener((obs, oldv, newv) -> {
            try {
                txfPrisDobbelt.getTextFormatter().getValueConverter().fromString(newv);
                // Hvis ingen exception, er den ok
                txfPrisDobbelt.setBorder(null);
            } catch (NumberFormatException e) {
                txfPrisDobbelt.setBorder(border);
            }
        });


    }

    //=============================== METHODS ==================================//

    public void opretTilvalg() {
        HotelTilvalg tilvalg = new HotelTilvalg(txfNavnTilvalg.getText(), Double.parseDouble(txfPris.getText()));
        tilvalgArr.add(tilvalg);
        lvwTilvalg.getItems().setAll(tilvalgArr);
        txfNavnTilvalg.clear();
        txfPris.clear();
    }

    public void sletTilvalg() {
        HotelTilvalg tilvalg = (HotelTilvalg) lvwTilvalg.getSelectionModel().getSelectedItem();
        tilvalgArr.remove(tilvalg);
        lvwTilvalg.getItems().setAll(tilvalgArr);
    }

    public void opretHotel() {
        String navn = txfNavnHotel.getText();
        double singlePris;
        if (txfPrisSingle.getText().isBlank()) {
            singlePris = 0;
        } else singlePris = Double.parseDouble(txfPrisSingle.getText());
        double dobbeltPris;
        if (txfPrisDobbelt.getText().isBlank()) {
            dobbeltPris = 0;
        } else dobbeltPris = Double.parseDouble(txfPrisDobbelt.getText());

        // Tjekker for tomme felter
        if (navn.isEmpty()) {
            showAlert("Indtast et navn.");
        } else if (singlePris == 0) {
            showAlert("Indtast en Pris for Single Værelse.");
        } else if (dobbeltPris == 0) {
            showAlert("Indtast en Pris for Dobbelt Værelse.");
        } else if (hotel != null) {
            hotel.opdaterInfo(navn, singlePris, dobbeltPris, tilvalgArr);
            VindueManager.adminVindue.updateGUI();
            this.hide();
        } else {
            Hotel hotel = Controller.opretHotel(navn, singlePris, dobbeltPris);
            // Tiløj Tilvalg
            for (HotelTilvalg tilvalg : tilvalgArr) {
                Controller.tilføjHotelTilvalg(hotel, tilvalg);
            }
            // Clear GUI
            clearGUI();

            // Luk Vindue og opdater det bagvedliggende vindue
            VindueManager.adminVindue.updateGUI();
            this.hide();
        }
    }

    public void clearGUI() {
        txfNavnHotel.clear();
        txfPrisSingle.clear();
        txfPrisDobbelt.clear();
        txfPris.clear();
        txfNavnTilvalg.clear();
        tilvalgArr.clear();
        lvwTilvalg.getItems().clear();
    }

    public void showAlert(String infoText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText(infoText);
        alert.showAndWait();
    }

    public void setHotel() {
        txfNavnHotel.setText(hotel.getNavn());
        txfPrisSingle.setText(Double.toString(hotel.getPrisEnkelt()));
        txfPrisDobbelt.setText(Double.toString(hotel.getPrisDobbelt()));
        tilvalgArr.addAll(hotel.getHotelTilvalg());
        lvwTilvalg.getItems().setAll(tilvalgArr);
    }

    public void changeLooks() {
        btnOpretHotel.setText("Opdater Hotel");
    }


}
