package gui;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;

public class ListeVindue extends Stage {

    private final TextArea txa = new TextArea();

    public ListeVindue() {
        GridPane pane = new GridPane();
        this.initContent(pane);
        this.setTitle("KAS");

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(25));
        pane.add(txa, 0, 1);
        txa.setEditable(false);
        txa.setFocusTraversable(false);
        txa.setMaxWidth(325);
        txa.setMinHeight(666);

    }

    public void visDeltagerKonference(Konference konference) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("=========%s=========", konference.getNavn()));
            ArrayList<Deltager> deltagere = konference.visDeltagere();
            for (Deltager deltager : deltagere) {
                sb.append(deltager + "\n");
            }
            txa.setText(sb.toString());


//        StringBuilder sb = new StringBuilder();
//        sb.append(String.format("=========%s=========", konference.getNavn()));
//        ArrayList<Deltager> deltagere = konference.visDeltagere();
//        for (Deltager deltager : deltagere) {
//            sb.append(deltager + "\n");
//        }
//        txa.setText(sb.toString());
    }

    public void visUdflugter(Konference konference) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Udflugt> udflugter = konference.getUdflugter();
        ArrayList<Tilmelding> tilmeldinger = konference.getTilmeldinger();
        for (Udflugt udflugt : udflugter) {
            sb.append(String.format("==========%s======== \n", udflugt.getNavn()));
            for (Tilmelding tilmelding : tilmeldinger) {
                if (tilmelding.getUdflugter().contains(udflugt)) {
                    sb.append(String.format("%s (%s %s) \n", tilmelding.getLedsager().getNavn(), tilmelding.getDeltager().getNavn(), tilmelding.getDeltager().getTlf()));
                }
            }
            txa.setText(sb.toString());
        }
    }

    public void visBookinger() {
        ArrayList<Hotel> hoteller = Controller.getHoteller();
        StringBuilder sb = new StringBuilder();
        for (Hotel hotel : hoteller) {
            sb.append(String.format("==========%s======== \n", hotel.getNavn()));
            for (Booking booking : hotel.getBookinger()) {
                Tilmelding tilmelding = booking.getTilmelding();
                Deltager deltager = booking.getTilmelding().getDeltager();
                sb.append(String.format("------ %s ", deltager.getNavn()));
                if (tilmelding.getLedsager() != null) {
                    sb.append(String.format("med %s ------ \n", tilmelding.getLedsager().getNavn()));
                } else {
                    sb.append("------ \n");
                }
                sb.append(String.format("Deltager i %s \n", tilmelding.getKonference().getNavn()));
                sb.append(String.format("Booking fra %s til %s \n", booking.getStartDato(), booking.getSlutDato()));
                if (!booking.getTilvalg().isEmpty()) {
                    sb.append("Tilvalg: ");
                    for (HotelTilvalg tilvalg : booking.getTilvalg()) {
                        sb.append(String.format("%s ", tilvalg));
                    }
                    sb.append("\n");
                }
            }
        }
        txa.setText(sb.toString());

    }
}
