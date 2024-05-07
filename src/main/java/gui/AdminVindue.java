package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Deltager;
import model.Hotel;
import model.Konference;
import storage.Storage;

public class AdminVindue extends Stage {

    // BRUGER
    ListView lvwBrugere = new ListView<>();
    TextArea txaBrugerInfo = new TextArea();
    ListView lvwTilmeldinger = new ListView<>();
    Button btnOpretBruger = new Button("Opret");
    Button btnOpdaterBruger = new Button("Opdater");
    Button btnSletbruger = new Button("Slet");
    Button btnTilmeldBruger = new Button("   Tilmeld   ");
    Button btnAfmeldBruger = new Button("   Afmeld    ");

    // KONFERENCE
    ListView lvwKonferencer = new ListView<>();
    TextArea txaKonferenceInfo = new TextArea();
    ListView lvwUdflugter = new ListView<>();
    Button btnOpretKonference = new Button("Opret");
    Button btnOpdaterKonference = new Button("Opdater");
    Button btnSletKonference = new Button("Slet");
    Button btnHentTilmeldte = new Button("Hent Tilmeldte");
    Button btnHentBookinger = new Button("Hent Bookinger");

    // HOTEL
    ListView lvwHoteller = new ListView<>();
    TextArea txaHotelInfo = new TextArea();
    ListView lvwTilvalg = new ListView<>();
    Button btnOpretHotel = new Button("Opret");
    Button btnOpdaterHotel = new Button("Opdater");
    Button btnSletHotel = new Button("Slet");

    public AdminVindue() {
        BorderPane pane = new BorderPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(BorderPane pane) {
        GridPane mainPane = new GridPane();
        GridPane brugerPane = new GridPane();
        GridPane konferencePane = new GridPane();
        GridPane hotelPane = new GridPane();

        mainPane.setGridLinesVisible(false);
        brugerPane.setGridLinesVisible(false);
        konferencePane.setGridLinesVisible(false);
        hotelPane.setGridLinesVisible(false);

        pane.setCenter(mainPane);
        int width = 1500;
        int height = 500;
        mainPane.setMaxWidth(width);
        mainPane.setMinWidth(width);
        mainPane.setMaxHeight(height);
        mainPane.setMinHeight(height);

        mainPane.setAlignment(Pos.CENTER);

        mainPane.add(brugerPane, 0, 0);
        mainPane.add(konferencePane, 1, 0);
        mainPane.add(hotelPane, 2, 0);

        //============================= BRUGER PANE ==========================//

        brugerPane.setHgap(10);
        brugerPane.setVgap(10);
        brugerPane.setPadding(new Insets(20));
        brugerPane.setMinWidth(width / 3);
        brugerPane.setMinHeight(height);
        brugerPane.setMaxWidth(width / 3);
        brugerPane.setMaxHeight(height);

        Label lblBruger = new Label("Brugere");
        Label lblBrugerInfo = new Label("Bruger Info");
        Label lblTilmeldinger = new Label("Tilmeldinger");
        brugerPane.add(lblBruger, 0, 0);
        brugerPane.add(lblBrugerInfo, 1, 0);
        brugerPane.add(lblTilmeldinger, 1, 2);

        brugerPane.add(lvwBrugere, 0, 1, 1, 3);
        lvwBrugere.setMinWidth((width / 3) / 3);
        brugerPane.add(txaBrugerInfo, 1, 1);
        txaBrugerInfo.setMinHeight(120);
        brugerPane.add(lvwTilmeldinger, 1, 3);

        HBox hboxBruger1 = new HBox();
        HBox hboxBruger2 = new HBox();

        hboxBruger1.getChildren().addAll(btnOpretBruger, btnOpdaterBruger, btnSletbruger);
        hboxBruger2.getChildren().addAll(btnTilmeldBruger, btnAfmeldBruger);

        hboxBruger1.setSpacing(50);
        hboxBruger1.setAlignment(Pos.CENTER);
        hboxBruger2.setSpacing(50);
        hboxBruger2.setAlignment(Pos.CENTER);

        brugerPane.add(hboxBruger1, 0, 4, 2, 1);
        brugerPane.add(hboxBruger2, 0, 5, 2, 1);

        ChangeListener<Deltager> DeltagerListener = (ov, o, n) -> this.selectedDeltagerChanged();
        lvwBrugere.getSelectionModel().selectedItemProperty().addListener(DeltagerListener);

        //============================= KONFERENCE PANE ==========================//

        konferencePane.setHgap(10);
        konferencePane.setVgap(10);
        konferencePane.setPadding(new Insets(20));
        konferencePane.setMinWidth(width / 3);
        konferencePane.setMinHeight(height);
        konferencePane.setMaxWidth(width / 3);
        konferencePane.setMaxHeight(height);

        Label lblKonference = new Label("Konferencer");
        Label lblKonferenceInfo = new Label("Konference Info");
        Label lblUdflugter = new Label("Udflugter");
        konferencePane.add(lblKonference, 0, 0);
        konferencePane.add(lblKonferenceInfo, 1, 0);
        konferencePane.add(lblUdflugter, 1, 2);

        konferencePane.add(lvwKonferencer, 0, 1, 1, 3);
        lvwKonferencer.setMinWidth((width / 3) / 3);
        konferencePane.add(txaKonferenceInfo, 1, 1);
        txaKonferenceInfo.setMinHeight(120);
        konferencePane.add(lvwUdflugter, 1, 3);

        HBox hboxKonference1 = new HBox();
        HBox hboxKonference2 = new HBox();

        hboxKonference1.getChildren().addAll(btnOpretKonference, btnOpdaterKonference, btnSletKonference);
        hboxKonference2.getChildren().addAll(btnHentTilmeldte, btnHentBookinger);

        hboxKonference1.setSpacing(50);
        hboxKonference1.setAlignment(Pos.CENTER);
        hboxKonference2.setSpacing(50);
        hboxKonference2.setAlignment(Pos.CENTER);

        konferencePane.add(hboxKonference1, 0, 4, 2, 1);
        konferencePane.add(hboxKonference2, 0, 5, 2, 1);

        ChangeListener<Konference> Konferencelistener = (ov, o, n) -> this.selectedKonferenceChanged();
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(Konferencelistener);

        //============================= HOTEL PANE ==========================//

        hotelPane.setHgap(10);
        hotelPane.setVgap(10);
        hotelPane.setPadding(new Insets(20));
        hotelPane.setMinWidth(width / 3);
        hotelPane.setMinHeight(height);
        hotelPane.setMaxWidth(width / 3);
        hotelPane.setMaxHeight(height);

        Label lblHotel = new Label("Hoteller");
        Label lblHotelInfo = new Label("Hotel Info");
        Label lblTilvalg = new Label("Hotel Tilvalg");
        hotelPane.add(lblHotel, 0, 0);
        hotelPane.add(lblHotelInfo, 1, 0);
        hotelPane.add(lblTilvalg, 1, 2);

        hotelPane.add(lvwHoteller, 0, 1, 1, 3);
        lvwHoteller.setMinWidth((width / 3) / 3);
        hotelPane.add(txaHotelInfo, 1, 1);
        txaHotelInfo.setMinHeight(120);
        txaHotelInfo.setEditable(false);
        hotelPane.add(lvwTilvalg, 1, 3);

        HBox hboxHotel1 = new HBox();
        hboxHotel1.getChildren().addAll(btnOpretHotel, btnOpdaterHotel, btnSletHotel);
        hboxHotel1.setSpacing(50);
        hboxHotel1.setAlignment(Pos.CENTER);
        hotelPane.add(hboxHotel1, 0, 4, 2, 1);
        hboxHotel1.setMinHeight(62);

        ChangeListener<Hotel> Hotellistener = (ov, o, n) -> this.selectedHotelChanged();
        lvwHoteller.getSelectionModel().selectedItemProperty().addListener(Hotellistener);

        btnOpretHotel.setOnAction(event -> VindueManager.visOpretHotelVindue());

        updateGUI();
    }

    public void selectedDeltagerChanged() {
        Deltager deltager = (Deltager) lvwBrugere.getSelectionModel().getSelectedItem();
        // Opdater listview af Tilmeldinger
        lvwTilmeldinger.getItems().setAll(deltager.getTilmeldinger());
        // Opdater Bruger Info
        txaBrugerInfo.setText(deltager.infoToString());
    }

    public void selectedHotelChanged() {
        Hotel hotel = (Hotel) lvwHoteller.getSelectionModel().getSelectedItem();
        // Opdater listview af HotelTilvalg
        lvwTilvalg.getItems().setAll(hotel.getHotelTilvalg());
        // Opdater Hotel Info
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(hotel.getNavn() + "\n"));
        sb.append(String.format("Pris Enkelt: %.2f \n", hotel.getPrisEnkelt()));
        sb.append(String.format("Pris Dobbelt: %.2f \n", hotel.getPrisDobbelt()));
        txaHotelInfo.setText(sb.toString());
    }

    public void selectedKonferenceChanged() {
        Konference konference = (Konference) lvwKonferencer.getSelectionModel().getSelectedItem();
        // Opdater listview af Udflugtter
        lvwUdflugter.getItems().setAll(konference.getUdflugter());
        // Opdater Konference Info
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(konference.getNavn() + "\n"));
        sb.append(String.format("Pris Enkelt: %.2f \n", konference.getPris()));
        sb.append(String.format(konference.getBeskrivelse() + "\n"));
        sb.append(String.format(konference.getLokation() + "\n"));
        sb.append(String.format("Start Dato: " + konference.getStartDato() + "\n"));
        sb.append(String.format("Slut Dato: " + konference.getSlutDato() + "\n"));
        txaKonferenceInfo.setText(sb.toString());
    }

    public void updateGUI() {
        lvwBrugere.getItems().setAll(Controller.getDeltagere());
        lvwKonferencer.getItems().setAll(Controller.getKonferencer());
        lvwHoteller.getItems().setAll(Controller.getHoteller());
    }
}
