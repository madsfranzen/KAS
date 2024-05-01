package gui;

import controller.Controller;
import model.Deltager;
import model.Konference;
import model.Tilmelding;

import java.time.LocalDate;

public class KASTest {
    public static void main(String[] args) {

        Konference havOgHimmel = Controller.opretKonference("Hav og Himmel","Det handler om Hav, samt Himmel","Odense Universitet", LocalDate.of(2024,5,18),LocalDate.of(2024,5,20),1500);
        Deltager finn = Controller.opretDeltager("Fm123","kode","Finn Madsen","Adresse","By","Land","12345678");
        Tilmelding finnTilmelding = Controller.opretTilmelding(LocalDate.of(2024,5,18),LocalDate.of(2024,5,20),false, null, null, finn, havOgHimmel, null);
        System.out.println(finnTilmelding);
    }

}
