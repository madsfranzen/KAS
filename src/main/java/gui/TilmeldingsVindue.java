package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Hotel;
import model.Konference;
import storage.Storage;

public class TilmeldingsVindue extends Application {

    private Konference konference;

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

    @Override
    public void start(Stage stage) {
        stage.setTitle("KAS");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
//        stage.setResizable(false);
        stage.show();
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
        lvwUdflugter.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvwUdflugter.setMaxHeight(150);
        pane.add(udflugtPane, 3, 5, 2, 4);
        lvwUdflugter.setDisable(true);


        Label lblSamletPris = new Label("Samlet Pris:");

        pane.add(lblSamletPris, 2, 9);
        pane.add(txfSamletPris, 3, 9);
        GridPane.setHalignment(lblSamletPris, HPos.RIGHT);
        txfSamletPris.setMaxWidth(100);
        Button btnOpretTilmelding = new Button("Opret Tilmelding");
        pane.add(btnOpretTilmelding, 4, 9);

        updateGui();

    }


    //================ Helpers ====================
    private void updateGui() {
        konference = Storage.getKonferecer().getFirst();
        lvwHoteller.getItems().setAll(Storage.getHoteller());
        lvwUdflugter.getItems().setAll(konference.getUdflugter());


    }

    //=================== Actions ================

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

    private void ledsagerØnskes(){
        boolean toggle = true;
        if (cbxLedsager.isSelected()){
            toggle = false;
        }
        txfLedsagerNavn.setDisable(toggle);
        lvwUdflugter.setDisable(toggle);
    }
}


