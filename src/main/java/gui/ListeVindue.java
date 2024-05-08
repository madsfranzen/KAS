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
        sb.append(String.format("=========%s=========\n", konference.getNavn()));

        ArrayList<Tilmelding> tilmeldinger = new ArrayList<>(konference.getTilmeldinger());
        sorterTilmeldinger(tilmeldinger);

        for (Tilmelding tilmelding : tilmeldinger) {
            Deltager deltager = tilmelding.getDeltager();
            sb.append(String.format("------ %s ", deltager.getNavn()));
            if (tilmelding.getLedsager() != null) {
                sb.append(String.format("med %s ------ \n", tilmelding.getLedsager().getNavn()));
            } else {
                sb.append("------ \n");
            }
            if (tilmelding.isForedragsholder()){
                sb.append("Er foredragsholder \n");
            }
            sb.append(String.format("Deltager fra %s til %s\n", tilmelding.getStartDato(), tilmelding.getSlutDato()));
            if (tilmelding.getBooking() != null) {
                Booking booking = tilmelding.getBooking();
                sb.append(String.format("Booking hos %s \n", booking.getHotel().getNavn()));
            }
            sb.append("\n");
        }
        txa.setText(sb.toString());
    }

    public static void sorterTilmeldinger(ArrayList<Tilmelding> arr) {
        for (int i = arr.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr.get(j).getDeltager().CompareTo(arr.get(j + 1).getDeltager()) > 0) {
                    Tilmelding temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
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
            sb.append("\n");
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
                sb.append("\n");
            }
        }
        txa.setText(sb.toString());

    }
}
