package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class TilmeldingsVindue extends Stage {

    private Konference konference;
    private Deltager deltager;
    private Tilmelding tilmelding;

    private DatePicker dpCheckIn = new DatePicker();
    private DatePicker dpCheckUd = new DatePicker();
    private DatePicker dpDeltagerFra = new DatePicker();
    private DatePicker dpDeltagerTil = new DatePicker();

    private CheckBox cbxHotelØnskes = new CheckBox("Hotel Ønskes");

    private ListView lvwHoteller = new ListView();
    private ListView lvwHotelTilvalg = new ListView();

    private TextArea txaHotelInfo = new TextArea();

    private CheckBox cbxForedragsholder = new CheckBox("Foredragsholder");
    private CheckBox cbxLedsager = new CheckBox("Ledsager");

    private TextField txfLedsagerNavn = new TextField();

    private ListView lvwUdflugter = new ListView();

    private TextField txfSamletPris = new TextField();
    private Label lblError = new Label();

    public TilmeldingsVindue(Konference konference, Deltager deltager) {
        this.konference = konference;
        this.deltager = deltager;
        this.setTitle("KAS");
        GridPane pane = new GridPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public TilmeldingsVindue(Konference konference, Deltager deltager, Tilmelding tilmelding) {
        this.konference = konference;
        this.deltager = deltager;
        this.tilmelding = tilmelding;
        this.setTitle("KAS");
        GridPane pane = new GridPane();
        this.initContent(pane);
        this.setTilmelding();
        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setVgap(20);
        pane.setHgap(20);
        pane.setMaxSize(800, 470);
//        pane.setGridLinesVisible(true);


        //================ Hotel Information ======================
        Label lblCheckIn = new Label("Check In:");
        Label lblCheckUd = new Label("Check Ud:");
        pane.add(lblCheckIn, 0, 0);
        pane.add(lblCheckUd, 1, 0);
        pane.add(dpCheckIn, 0, 1);
        pane.add(dpCheckUd, 1, 1);
        dpCheckIn.setMaxWidth(115);
        dpCheckUd.setMaxWidth(115);
        dpCheckIn.setDisable(true);
        dpCheckUd.setDisable(true);

        pane.add(cbxHotelØnskes, 2, 1);
        cbxHotelØnskes.setOnAction(e -> hotelØnskes());

        Label lblHoteller = new Label("Hoteller:");
        pane.add(lblHoteller, 0, 2);
        pane.add(lvwHoteller, 0, 3, 2, 6);
        lvwHoteller.setMaxHeight(350);
        ChangeListener<Hotel> listener = (ov, o, n) -> this.hotelValg();
        lvwHoteller.getSelectionModel().selectedItemProperty().addListener(listener);
        lvwHoteller.setDisable(true);

        Label lblHotelInfo = new Label("Hotel Info:");
        pane.add(lblHotelInfo, 2, 3);
        pane.add(txaHotelInfo, 2, 4, 1, 2);
        txaHotelInfo.setEditable(false);
        txaHotelInfo.setMaxHeight(75);
        txaHotelInfo.setMaxWidth(175);
        txaHotelInfo.setDisable(true);

        Label lblHotelTilvalg = new Label("Hotel Tilvalg:");
        pane.add(lblHotelTilvalg, 2, 6);
        pane.add(lvwHotelTilvalg, 2, 7, 1, 2);
        lvwHotelTilvalg.setMaxWidth(175);
        lvwHotelTilvalg.setDisable(true);


        //================== Deltager og ledsager info ======================

        Label lblDeltagerStart = new Label("Deltager Fra");
        Label lblDeltagerTil = new Label("Til");
        pane.add(lblDeltagerStart, 3, 0);
        pane.add(lblDeltagerTil, 4, 0);
        pane.add(dpDeltagerFra, 3, 1);
        pane.add(dpDeltagerTil, 4, 1);
        dpDeltagerFra.setMaxWidth(115);
        dpDeltagerTil.setMaxWidth(115);

        pane.add(cbxForedragsholder, 3, 2);
        pane.add(cbxLedsager, 3, 3);
        cbxLedsager.setOnAction(e -> ledsagerØnskes());

        GridPane ledsagerPane = new GridPane();
        Label lblLedsagerNavn = new Label("Ledsager Navn:");
        ledsagerPane.add(lblLedsagerNavn, 0, 0);
        ledsagerPane.add(txfLedsagerNavn, 1, 0);
        ledsagerPane.setHgap(10);
        pane.add(ledsagerPane, 3, 4, 2, 1);
        txfLedsagerNavn.setMaxWidth(150);
        txfLedsagerNavn.setDisable(true);

        //========= Udflugter ==========

        GridPane udflugtPane = new GridPane();
        Label lblUdflugter = new Label("Udflugter:");
        udflugtPane.setVgap(15);
        udflugtPane.add(lblUdflugter, 0, 0);
        udflugtPane.add(lvwUdflugter, 0, 1);
        lvwUdflugter.setMaxHeight(150);
        pane.add(udflugtPane, 3, 5, 2, 4);
        lvwUdflugter.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvwUdflugter.setDisable(true);


        Label lblSamletPris = new Label("Samlet Pris:");

        pane.add(lblSamletPris, 2, 9);
        pane.add(txfSamletPris, 3, 9);
        GridPane.setHalignment(lblSamletPris, HPos.RIGHT);
        txfSamletPris.setMaxWidth(100);
        Button btnOpretTilmelding = new Button("Opret Tilmelding");
        if (tilmelding != null) {
            btnOpretTilmelding.setText("Opdater Tilmelding");
        }
        pane.add(btnOpretTilmelding, 4, 9);
        btnOpretTilmelding.setOnAction(e -> opretTilmelding());

        lblError.setTextFill(Color.RED);
        pane.add(lblError, 0, 9);

        updateGui();
        tilpasFarverForForestilling(dpCheckIn);
        tilpasFarverForForestilling(dpCheckUd);
        tilpasFarverForForestilling(dpDeltagerFra);
        tilpasFarverForForestilling(dpDeltagerTil);

    }


    public void setTilmelding() {
        dpDeltagerFra.setValue(tilmelding.getStartDato());
        dpDeltagerTil.setValue(tilmelding.getSlutDato());
        cbxForedragsholder.setSelected(tilmelding.isForedragsholder());
        if (tilmelding.getLedsager() != null) {
            cbxLedsager.setSelected(true);
            ledsagerØnskes();
            txfLedsagerNavn.setText(tilmelding.getLedsager().getNavn());
            for (Object udflugt : lvwUdflugter.getItems()) {
                if (tilmelding.getUdflugter().contains(udflugt)) {
                    lvwUdflugter.getSelectionModel().select(udflugt);
                }
            }
        }
        if (tilmelding.getBooking() != null) {
            Booking booking = tilmelding.getBooking();
            cbxHotelØnskes.setSelected(true);
            hotelØnskes();
            dpCheckIn.setValue(booking.getStartDato());
            dpCheckUd.setValue(booking.getSlutDato());
            lvwHoteller.getSelectionModel().select(booking.getHotel());
            for (Object tilvalg : lvwHotelTilvalg.getItems()) {
                if (booking.getTilvalg().contains(tilvalg)) {
                    lvwHotelTilvalg.getSelectionModel().select(tilvalg);
                }
            }
        }

    }


    //================ Helpers ====================
    private void updateGui() {
        lvwHoteller.getItems().setAll(Storage.getHoteller());
        lvwUdflugter.getItems().setAll(konference.getUdflugter());
    }

    public void showAlert(String infoText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText(null);
        alert.setContentText(infoText);
        alert.showAndWait();
    }

    //=================== Actions ================


    private void opretTilmelding() {
        boolean inputIsValid = true;
        LocalDate startDato = dpDeltagerFra.getValue();
        LocalDate slutDato = dpDeltagerTil.getValue();
        boolean foredragsholder = cbxForedragsholder.isSelected();
        String ledsagerNavn = txfLedsagerNavn.getText();
        LocalDate bookingStartDato = dpCheckIn.getValue();
        LocalDate bookingSlutDato = dpCheckUd.getValue();
        Hotel hotel = (Hotel) lvwHoteller.getSelectionModel().getSelectedItem();

        if (startDato == null) {
            inputIsValid = false;
            showAlert("Indtast en start dato");
        } else if (startDato.isBefore(konference.getStartDato()) || startDato.isAfter(konference.getSlutDato())) {
            showAlert("Indtast en gyldig start dato");
            inputIsValid = false;
        } else if (slutDato == null) {
            inputIsValid = false;
            showAlert("Indtast en slut Dato.");
        } else if (slutDato.isBefore(konference.getStartDato()) || slutDato.isAfter(konference.getSlutDato()) || startDato.isAfter(slutDato)) {
            showAlert("Indtast en gyldig Slut Dato");
            inputIsValid = false;
        } else if (ledsagerNavn.equalsIgnoreCase("") && cbxLedsager.isSelected()) {
            inputIsValid = false;
            showAlert("Indtast vendligst navnet på din ledsager");
        } else if (cbxHotelØnskes.isSelected()) {
            if (bookingStartDato == null) {
                inputIsValid = false;
                showAlert("Indtast en startdato på booking");
            } else if (bookingStartDato.isBefore(konference.getStartDato()) || bookingStartDato.isAfter(konference.getSlutDato())) {
                showAlert("Indtast en gyldig startdato på booking ");
                inputIsValid = false;
            } else if (bookingSlutDato == null) {
                inputIsValid = false;
                showAlert("Indtast en startdato på booking ");
            } else if (bookingSlutDato.isBefore(konference.getStartDato()) || bookingSlutDato.isAfter(konference.getSlutDato()) || bookingStartDato.isAfter(bookingSlutDato)) {
                showAlert("Indtast en gyldig slutdato på booking ");
                inputIsValid = false;
            } else if (hotel == null) {
                showAlert("Vælg veligst et hotel");
                inputIsValid = false;
            }

        }

        if (tilmelding != null) {
            Controller.sletTilmelding(konference, deltager, tilmelding);
            tilmelding = null;
        }
        if (inputIsValid) {
            tilmelding = Controller.opretTilmelding(startDato, slutDato, foredragsholder, deltager, konference);

            for (Object udflugt : lvwUdflugter.getSelectionModel().getSelectedItems()) {
                tilmelding.tilføjUdflugt((Udflugt) udflugt);
            }

            if (cbxLedsager.isSelected()) {
                Ledsager ledsager = Controller.opretLedsager(ledsagerNavn, tilmelding);
            }
            Booking booking = null;
            if (cbxHotelØnskes.isSelected()) {
                booking = Controller.opretBooking(bookingStartDato, bookingSlutDato, tilmelding, hotel);
                for (Object tilvalg : lvwHotelTilvalg.getSelectionModel().getSelectedItems()) {
                    booking.tilføjTilvalg((HotelTilvalg) tilvalg);
                }
            }
            this.hide();
        }

    }

    private void hotelValg() {
        Hotel hotel = (Hotel) lvwHoteller.getSelectionModel().getSelectedItem();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(hotel.getNavn() + "\n"));
        sb.append(String.format("Pris Enkelt: %.2f \n", hotel.getPrisEnkelt()));
        sb.append(String.format("Pris Dobbelt: %.2f \n", hotel.getPrisDobbelt()));
        txaHotelInfo.setText(sb.toString());
        lvwHotelTilvalg.getItems().setAll(hotel.getHotelTilvalg());
    }

    private void hotelØnskes() {
        boolean toggle = true;
        if (cbxHotelØnskes.isSelected()) {
            toggle = false;
        }
        dpCheckIn.setDisable(toggle);
        dpCheckUd.setDisable(toggle);
        lvwHoteller.setDisable(toggle);
        lvwHotelTilvalg.setDisable(toggle);
        txaHotelInfo.setDisable(toggle);
    }

    private void ledsagerØnskes() {
        boolean toggle = true;
        if (cbxLedsager.isSelected()) {
            toggle = false;
        }
        txfLedsagerNavn.setDisable(toggle);
        lvwUdflugter.setDisable(toggle);
    }


//==============================================================================================

    private void tilpasFarverForForestilling(DatePicker dp) {
        dp.setDayCellFactory(new Callback<DatePicker, DateCell>() {
                                 @Override
                                 public DateCell call(DatePicker param) {
                                     return new DateCell() {
                                         @Override
                                         public void updateItem(LocalDate item, boolean empty) {
                                             super.updateItem(item, empty);
                                             if (item != null) {
                                                 LocalDate startDato = konference.getStartDato();
                                                 LocalDate slutDato = konference.getSlutDato();
                                                 if ((startDato != null && item.isEqual(startDato)) || (slutDato != null && item.isEqual(slutDato)) || (item.isAfter(startDato) && item.isBefore(slutDato))) {
                                                     setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), Insets.EMPTY)));
                                                 }
                                             }
                                         }
                                     };
                                 }
                             }
        );
    }
}