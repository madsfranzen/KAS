package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class OpretKonferenceVindue extends Stage {

    TextField txfKonferenceNavn = new TextField();
    DatePicker dpStart = new DatePicker();
    DatePicker dpSlut = new DatePicker();
    TextField txfLokation = new TextField();
    TextArea txaBeskrivelse = new TextArea();

    TextField txfUdflugtNavn = new TextField();
    TextField txfUdflugtPris = new TextField();
    CheckBox chbMiddag = new CheckBox("Middag Inkluderet");
    ListView lvwUdflugter = new ListView<>();
    Button btnOpretUdflugt = new Button("Opret Udflugt");
    Button btnSletUdflugt = new Button("Slet Udflugt");

    ListView lvwHoteller = new ListView<>();
    ListView lvwTilkHoteller = new ListView<>();
    Button btnTilføjHotel = new Button("Tilføj ->");
    Button btnFjernHotel = new Button("<- Fjern");
    Button btnOpretKonference = new Button("Opret Konference");


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


        //===================== LEFT ====================//

        Label lblKonference = new Label("Konference");
        Label lblKonferenceNavn = new Label("Navn");
        Label lblStartDato = new Label("Start Dato");
        Label lblSlutDato = new Label("Slut Dato");
        Label lblLokation = new Label("Lokation");
        Label lblBeskrivelse = new Label("Beskrivelse");

        paneL.add(lblKonference, 1, 0);
        paneL.add(lblKonferenceNavn, 0, 1);
        paneL.add(txfKonferenceNavn, 1, 1);
        paneL.add(lblStartDato, 0, 2);
        paneL.add(dpStart, 1, 2);
        paneL.add(lblSlutDato, 0, 3);
        paneL.add(dpSlut, 1, 3);
        paneL.add(lblLokation, 0, 4);
        paneL.add(txfLokation, 1, 4);
        paneL.add(lblBeskrivelse, 0, 5);
        paneL.add(txaBeskrivelse, 1, 5);

        //===================== MID ===================//

        Label lblUdflugt = new Label("Udlflugt");
        Label lblUdflugtNavn = new Label("Navn");
        Label lblUdflugtPris = new Label("Pris");

        //====================== RIGHT =================//

        Label lblHoteller = new Label("Hoteller");
        Label lblTilknHoteller = new Label("Tilknyttede Hoteller");

    }
}
