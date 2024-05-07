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
import model.Hotel;
import model.HotelTilvalg;
import model.Konference;

import java.util.ArrayList;

public class OpretHotelVindue extends Stage {

    private ArrayList<HotelTilvalg> tilvalgArr = new ArrayList<>();

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
    }

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
        Hotel hotel = Controller.opretHotel(txfNavnHotel.getText(), Double.parseDouble(txfPrisSingle.getText()), Double.parseDouble(txfPrisDobbelt.getText()));
        for (HotelTilvalg tilvalg : tilvalgArr) {
            Controller.tilføjHotelTilvalg(hotel, tilvalg);
        }
        txfNavnHotel.clear();
        txfPrisSingle.clear();
        txfPrisDobbelt.clear();
        System.out.println(hotel);
    }
}
