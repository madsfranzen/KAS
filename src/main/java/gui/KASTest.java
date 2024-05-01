package gui;

import controller.Controller;
import model.*;

import java.time.LocalDate;

public class KASTest {
    public static void main(String[] args) {

        Konference havOgHimmel = Controller.opretKonference("Hav og Himmel","Det handler om Hav, samt Himmel","Odense Universitet", LocalDate.of(2024,5,18),LocalDate.of(2024,5,20),1500);
        Hotel denHvideSvane = new Hotel("Den Hvide Svane", 1050,1250);
        Deltager finn = Controller.opretDeltager("Fm123","kode","Finn Madsen","Adresse","By","Land","12345678");
        Tilmelding finnTilmelding = Controller.opretTilmelding(LocalDate.of(2024,5,18),LocalDate.of(2024,5,20),false, null, finn, havOgHimmel, null);
        System.out.println(finnTilmelding);
        Deltager niels = Controller.opretDeltager("Np123","kode","Niels Petersen", "Adresse","By","Danmark","23456789");
        Tilmelding nielsTilmelding = Controller.opretTilmelding(LocalDate.of(2024,5,18),LocalDate.of(2024,5,20), false, null, niels, havOgHimmel, null);
        Booking nielsBooking = Controller.opretBooking(LocalDate.of(2024,5,18),LocalDate.of(2024,5,20),nielsTilmelding,denHvideSvane);
        System.out.println(nielsTilmelding);
        
    }

}
