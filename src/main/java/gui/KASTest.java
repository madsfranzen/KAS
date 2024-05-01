package gui;

import controller.Controller;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class KASTest {
    public static void main(String[] args) {

        Konference havOgHimmel = Controller.opretKonference("Hav og Himmel","Det handler om Hav, samt Himmel","Odense Universitet", LocalDate.of(2024,5,18),LocalDate.of(2024,5,20),1500);
        Udflugt byrundtur = Controller.opretUdflugt("Byrundtur, Odense", 125, LocalDate.of(2024,5,18),true, havOgHimmel);
        Udflugt egeskov = Controller.opretUdflugt("Egeskov", 75, LocalDate.of(2024,5,19),false, havOgHimmel);
        Udflugt trapholt = Controller.opretUdflugt("Trapholt Museum, Kolding", 200, LocalDate.of(2024,5,20), true, havOgHimmel);

        Hotel denHvideSvane = Controller.opretHotel("Den Hvide Svane", 1050,1250);
        HotelTilvalg wifi = Controller.opretHotelTilvalg("Wifi", 50, denHvideSvane);
        Deltager finn = Controller.opretDeltager("Fm123","kode","Finn Madsen","Adresse","By","Land","12345678");
        Tilmelding finnTilmelding = Controller.opretTilmelding(LocalDate.of(2024,5,18),LocalDate.of(2024,5,20),false, finn, havOgHimmel);
        System.out.println(finnTilmelding);

        Deltager niels = Controller.opretDeltager("Np123","kode","Niels Petersen", "Adresse","By","Danmark","23456789");
        Tilmelding nielsTilmelding = Controller.opretTilmelding(LocalDate.of(2024,5,18),LocalDate.of(2024,5,20), false, niels, havOgHimmel);
        Booking nielsBooking = Controller.opretBooking(LocalDate.of(2024,5,18),LocalDate.of(2024,5,20),nielsTilmelding,denHvideSvane);
        System.out.println(nielsTilmelding);

        Deltager ulla = Controller.opretDeltager("Ull143","kode","Ulla Hansen", "VejVej 14", "By", "Azeroth", "13589385");
        Tilmelding ullaTilmelding = Controller.opretTilmelding(LocalDate.of(2024,5,18),LocalDate.of(2024,5,19), false, ulla, havOgHimmel);
        Ledsager hans = Controller.opretLedsager("Hans Hansen", ullaTilmelding);
        ullaTilmelding.tilføjUdflugt(byrundtur);
        System.out.println(ullaTilmelding);

        Deltager peter = Controller.opretDeltager("PetPet","kode","Peter Sommer", "Vej1", "Skanderborg", "Skanderborg", "101358135");
        Tilmelding peterTilmelding = Controller.opretTilmelding(LocalDate.of(2024,5,18),LocalDate.of(2024,5,20),false, peter, havOgHimmel);
        Ledsager mia = Controller.opretLedsager("Mie", peterTilmelding);
        peterTilmelding.tilføjUdflugt(egeskov);
        peterTilmelding.tilføjUdflugt(trapholt);
        Booking booking = Controller.opretBooking(LocalDate.of(2024,5,18),LocalDate.of(2024,5,20),peterTilmelding, denHvideSvane);
        booking.tilføjTilvalg(wifi);
        System.out.println(peterTilmelding);



    }

}
