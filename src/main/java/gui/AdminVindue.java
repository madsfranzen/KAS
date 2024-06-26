package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.*;

import java.util.Optional;

public class AdminVindue extends Stage {

    // BRUGER
    private final ListView<Deltager> lvwBrugere = new ListView<>();
    private final TextArea txaBrugerInfo = new TextArea();
    private final ListView<Tilmelding> lvwTilmeldinger = new ListView<>();
    private final Button btnOpretBruger = new Button("Opret");
    private final Button btnOpdaterBruger = new Button("Opdater");
    private final Button btnSletbruger = new Button("Slet");
    private final Button btnTilmeldBruger = new Button("   Tilmeld   ");
    private final Button btnAfmeldBruger = new Button("   Afmeld    ");
    private final Button btnLogud = new Button("LOG UD");

    // KONFERENCE
    private final ListView<Konference> lvwKonferencer = new ListView<>();
    private final TextArea txaKonferenceInfo = new TextArea();
    private final ListView<Udflugt> lvwUdflugter = new ListView<>();
    private final Button btnOpretKonference = new Button("Opret");
    private final Button btnOpdaterKonference = new Button("Opdater");
    private final Button btnSletKonference = new Button("Slet");
    private final Button btnHentTilmeldte = new Button("Print Tilmeldte");
    private final Button btnPrintUdflugter = new Button("Print Udflugter");

    // HOTEL
    private final ListView lvwHoteller = new ListView<>();
    private final TextArea txaHotelInfo = new TextArea();
    private final ListView lvwTilvalg = new ListView<>();
    private final Button btnOpretHotel = new Button("Opret");
    private final Button btnOpdaterHotel = new Button("Opdater");
    private final Button btnSletHotel = new Button("Slet");
    private final Button btnHentBookinger = new Button("Print Alle Bookinger");
    private final HBox hBoxHotel2 = new HBox();

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
        int width = 1400;
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
        brugerPane.setStyle("-fx-border-color: black");

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
        txaBrugerInfo.setEditable(false);
        brugerPane.add(lvwTilmeldinger, 1, 3);

        HBox hboxBruger1 = new HBox();
        HBox hboxBruger2 = new HBox();

        hboxBruger1.getChildren().addAll(btnOpretBruger, btnOpdaterBruger, btnSletbruger);
        hboxBruger2.getChildren().addAll(btnTilmeldBruger, btnAfmeldBruger);

        btnOpretBruger.setOnAction(e -> opretBruger());
        btnOpdaterBruger.setOnAction(e -> opdaterBruger());
        btnTilmeldBruger.setOnAction(e -> opretTilmelding());
        btnAfmeldBruger.setOnAction(e -> afmeld());
        btnSletbruger.setOnAction(e -> sletDeltager());

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
        konferencePane.setStyle("-fx-border-color: black");

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
        txaKonferenceInfo.setEditable(false);
        konferencePane.add(lvwUdflugter, 1, 3);

        HBox hboxKonference1 = new HBox();
        HBox hboxKonference2 = new HBox();

        hboxKonference1.getChildren().addAll(btnOpretKonference, btnOpdaterKonference, btnSletKonference);
        hboxKonference2.getChildren().addAll(btnHentTilmeldte, btnPrintUdflugter);


        hboxKonference1.setSpacing(50);
        hboxKonference1.setAlignment(Pos.CENTER);
        hboxKonference2.setSpacing(50);
        hboxKonference2.setAlignment(Pos.CENTER);

        konferencePane.add(hboxKonference1, 0, 4, 2, 1);
        konferencePane.add(hboxKonference2, 0, 5, 2, 1);

        ChangeListener<Konference> Konferencelistener = (ov, o, n) -> this.selectedKonferenceChanged();
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(Konferencelistener);

        btnOpretKonference.setOnAction(event -> {
            VindueManager.opretKonferenceVindue.clearGUI();
            VindueManager.opretKonferenceVindue.show();
        });
        btnOpdaterKonference.setOnAction(event -> opdaterKonference());
        btnSletKonference.setOnAction(e -> sletKonference());
        btnHentTilmeldte.setOnAction(e -> hentTilmeldte());
        btnPrintUdflugter.setOnAction(e -> hentLedsagerePåUdflugter());

        //============================= HOTEL PANE ==========================//

        hotelPane.setHgap(10);
        hotelPane.setVgap(10);
        hotelPane.setPadding(new Insets(20));
        hotelPane.setMinWidth(width / 3);
        hotelPane.setMinHeight(height);
        hotelPane.setMaxWidth(width / 3);
        hotelPane.setMaxHeight(height);
        hotelPane.setStyle("-fx-border-color: black");

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

        txaHotelInfo.maxWidth(100);
        lvwTilvalg.maxWidth(100);

        HBox hboxHotel1 = new HBox();
        hboxHotel1.getChildren().addAll(btnOpretHotel, btnOpdaterHotel, btnSletHotel);
        hboxHotel1.setSpacing(50);
        hboxHotel1.setAlignment(Pos.CENTER);
        hotelPane.add(hboxHotel1, 0, 4, 2, 1);
        hBoxHotel2.getChildren().addAll(btnHentBookinger);
        hBoxHotel2.setSpacing(50);
        hBoxHotel2.setAlignment(Pos.CENTER);
        hotelPane.add(hBoxHotel2, 0, 5, 2, 1);


        HBox hboxInfoLogud = new HBox();
        hboxInfoLogud.getChildren().addAll(lblHotelInfo, btnLogud);
        hboxInfoLogud.setSpacing(15);
        hboxInfoLogud.setAlignment(Pos.BASELINE_LEFT);

        hotelPane.add(hboxInfoLogud, 1, 0, 2, 1);
        btnLogud.setOnAction(event -> logUd());

        ChangeListener<Hotel> Hotellistener = (ov, o, n) -> this.selectedHotelChanged();
        lvwHoteller.getSelectionModel().selectedItemProperty().addListener(Hotellistener);

        btnOpretHotel.setOnAction(event -> {
            VindueManager.opretHotelVindue.clearGUI();
            VindueManager.visOpretHotelVindue();
        });
        btnOpdaterHotel.setOnAction(event -> opdaterHotel());
        btnSletHotel.setOnAction(e -> sletHotel());
        btnHentBookinger.setOnAction(e -> hentBookinger());
        updateGUI();
    }

    //========================== Updates ====================================

    public void logUd() {
        LoginVindue loginVindue = new LoginVindue();
        this.hide();
        loginVindue.show();
    }

    public void selectedDeltagerChanged() {
        Deltager deltager = lvwBrugere.getSelectionModel().getSelectedItem();
        if (deltager != null) {
            // Opdater listview af Tilmeldinger
            lvwTilmeldinger.getItems().setAll(deltager.getTilmeldinger());
            // Opdater Bruger Info
            txaBrugerInfo.setText(deltager.infoToString());
        }
    }

    public void selectedHotelChanged() {
        Hotel hotel = (Hotel) lvwHoteller.getSelectionModel().getSelectedItem();
        // Opdater listview af HotelTilvalg
        if (!(hotel == null)) {
            lvwTilvalg.getItems().setAll(hotel.getHotelTilvalg());
            // Opdater Hotel Info
            StringBuilder sb = new StringBuilder();
            sb.append(String.format(hotel.getNavn() + "\n"));
            sb.append(String.format("Pris Enkelt: %.2f \n", hotel.getPrisEnkelt()));
            sb.append(String.format("Pris Dobbelt: %.2f \n", hotel.getPrisDobbelt()));
            txaHotelInfo.setText(sb.toString());
        }
    }

    public void selectedKonferenceChanged() {
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        // Opdater listview af Udflugtter
        if (!(konference == null)) {
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
    }

    public void updateGUI() {
        lvwBrugere.getItems().setAll(Controller.getDeltagere());
        lvwKonferencer.getItems().setAll(Controller.getKonferencer());
        lvwHoteller.getItems().setAll(Controller.getHoteller());
        lvwBrugere.getSelectionModel().clearSelection();
        lvwKonferencer.getSelectionModel().clearSelection();
        lvwHoteller.getSelectionModel().clearSelection();
        clearGUI();
    }

    public void clearGUI() {
        txaBrugerInfo.clear();
        txaKonferenceInfo.clear();
        txaHotelInfo.clear();
        lvwTilmeldinger.getItems().clear();
        lvwTilvalg.getItems().clear();
        lvwUdflugter.getItems().clear();
    }

    //========================= Actions ===============================


    public void hentTilmeldte() {
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        if (konference != null) {
            ListeVindue listeVindue = new ListeVindue();
            listeVindue.visDeltagerKonference(konference);
            listeVindue.showAndWait();
        } else ingenKonferenceValgtAlert();
    }

    public void hentLedsagerePåUdflugter() {
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        if (konference != null) {
            ListeVindue listeVindue = new ListeVindue();
            listeVindue.visUdflugter(konference);
            listeVindue.showAndWait();
        } else ingenKonferenceValgtAlert();
    }

    public void hentBookinger() {
        ListeVindue listeVindue = new ListeVindue();
        listeVindue.visBookinger();
        listeVindue.showAndWait();
    }

    public void opretBruger() {
        VindueManager.opretBrugerVindue.clearGUI();
        VindueManager.opretBrugerVindue.showAndWait();
        updateGUI();
    }

    public void opdaterBruger() {
        Deltager deltager = lvwBrugere.getSelectionModel().getSelectedItem();
        OpretBrugerVindue opretBrugerVindue = new OpretBrugerVindue(deltager);
        opretBrugerVindue.showAndWait();
        updateGUI();
    }

    public void opretTilmelding() {
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        Deltager deltager = lvwBrugere.getSelectionModel().getSelectedItem();
        Boolean erTilmeldt = false;

        for (Tilmelding tilmelding : deltager.getTilmeldinger()) {
            if (tilmelding.getKonference().equals(konference)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Fejl");
                alert.setContentText("Du kan ikke oprette 2 tilmeldinger til samme konference");
                alert.showAndWait();
                erTilmeldt = true;

            }
        }

        if (konference != null && deltager != null && !erTilmeldt) {
            TilmeldingsVindue tilmeldingsVindue = new TilmeldingsVindue(konference, deltager);
            tilmeldingsVindue.showAndWait();
            lvwTilmeldinger.getItems().setAll(deltager.getTilmeldinger());
        } else if (!erTilmeldt) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Vælg venlist en deltager, og en konference");
            alert.showAndWait();
        }
    }

    public void afmeld() {
        Tilmelding tilmelding = lvwTilmeldinger.getSelectionModel().getSelectedItem();
        Deltager deltager = lvwBrugere.getSelectionModel().getSelectedItem();
        if (tilmelding != null) {
            Konference konference = tilmelding.getKonference();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Er du sikker?");
            alert.setContentText("Vil du slette din tilmelding?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Controller.sletTilmelding(konference, deltager, tilmelding);
                    lvwTilmeldinger.getItems().setAll(deltager.getTilmeldinger());
                } else {
                    // Do nothing
                }
            });
        }
    }

    public void opdaterTilmelding() {
        Deltager deltager = lvwBrugere.getSelectionModel().getSelectedItem();
        Tilmelding tilmelding = lvwTilmeldinger.getSelectionModel().getSelectedItem();
        if (tilmelding != null) {
            Konference konference = tilmelding.getKonference();
            TilmeldingsVindue tilmeldingsVindue = new TilmeldingsVindue(konference, deltager, tilmelding);
            tilmeldingsVindue.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Vælg venlist en tilmelding");
            alert.showAndWait();
        }
    }

    public void opdaterHotel() {
        Hotel hotel = (Hotel) lvwHoteller.getSelectionModel().getSelectedItem();
        OpretHotelVindue opretHotelVindue = new OpretHotelVindue(hotel);
        opretHotelVindue.showAndWait();
        updateGUI();
    }

    public void opdaterKonference() {
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        OpretKonferenceVindue opretKonferenceVindue = new OpretKonferenceVindue(konference);
        opretKonferenceVindue.showAndWait();
        updateGUI();
    }

    public void sletDeltager() {
        Deltager deltager = lvwBrugere.getSelectionModel().getSelectedItem();
        if (deltager != null) {
            if (showConfirmation("Er du sikker på, at du vil slette denne bruger?")) {
                Controller.sletDeltager(deltager);
                updateGUI();
            }
        }
    }

    public void sletHotel() {
        Hotel hotel = (Hotel) lvwHoteller.getSelectionModel().getSelectedItem();
        if (hotel != null) {
            if (showConfirmation("Er du sikker på, at du vil slette dette hotel?")) {
                Controller.sletHotel(hotel);
                updateGUI();
            }
        }
    }

    public void sletKonference() {
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        if (konference != null) {
            if (showConfirmation("Er du sikker på, at du vil slette denne konference?")) {
                Controller.sletKonference(konference);
                updateGUI();
            }
        }
    }

    public boolean showConfirmation(String infoText) {
        Boolean ok;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Godkend");
        alert.setHeaderText(null);
        alert.setContentText(infoText);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.isPresent() && buttonType.get().equals(ButtonType.OK)) {
            ok = true;
        } else ok = false;
        return ok;
    }

    public void ingenKonferenceValgtAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText("Ingen konference valgt");
        alert.setContentText("Vælg venligst en konference.");
        alert.showAndWait();
    }
}
