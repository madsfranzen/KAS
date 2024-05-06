package gui;

import controller.Controller;
import javafx.application.Application;
import model.*;

import java.time.LocalDate;

public class KASApp {
    public static void main(String[] args) {

        initStorage();
        Application.launch(TilmeldingsVindue.class);
    }

    private static void initStorage(){
        Konference havOgHimmel = Controller.opretKonference("Hav og Himmel", "Det handler om Hav, samt Himmel", "Odense Universitet", LocalDate.of(2024, 5, 18), LocalDate.of(2024, 5, 20), 1500);
        Udflugt byrundtur = Controller.opretUdflugt("Byrundtur, Odense", 125, LocalDate.of(2024, 5, 18), true, havOgHimmel);
        Udflugt egeskov = Controller.opretUdflugt("Egeskov", 75, LocalDate.of(2024, 5, 19), false, havOgHimmel);
        Udflugt trapholt = Controller.opretUdflugt("Trapholt Museum, Kolding", 200, LocalDate.of(2024, 5, 20), true, havOgHimmel);

        Hotel denHvideSvane = Controller.opretHotel("Den Hvide Svane", 1050, 1250);
        HotelTilvalg wifi = Controller.opretHotelTilvalg("Wifi", 50, denHvideSvane);
        Deltager finn = Controller.opretDeltager("Fm123", "kode", "Finn Madsen", "Adresse", "By", "Land", "12345678");
        Tilmelding finnTilmelding = Controller.opretTilmelding(LocalDate.of(2024, 5, 18), LocalDate.of(2024, 5, 20), false, finn, havOgHimmel);

        Deltager niels = Controller.opretDeltager("Np123", "kode", "Niels Petersen", "Adresse", "By", "Danmark", "23456789");
        Tilmelding nielsTilmelding = Controller.opretTilmelding(LocalDate.of(2024, 5, 18), LocalDate.of(2024, 5, 20), false, niels, havOgHimmel);
        Booking nielsBooking = Controller.opretBooking(LocalDate.of(2024, 5, 18), LocalDate.of(2024, 5, 20), nielsTilmelding, denHvideSvane);

        Deltager ulla = Controller.opretDeltager("Ull143", "kode", "Ulla Hansen", "VejVej 14", "By", "Azeroth", "13589385");
        Tilmelding ullaTilmelding = Controller.opretTilmelding(LocalDate.of(2024, 5, 18), LocalDate.of(2024, 5, 19), false, ulla, havOgHimmel);
        Ledsager hans = Controller.opretLedsager("Hans Hansen", ullaTilmelding);
        ullaTilmelding.tilføjUdflugt(byrundtur);

        Deltager peter = Controller.opretDeltager("PetPet", "kode", "Peter Sommer", "Vej1", "Skanderborg", "Skanderborg", "101358135");
        Tilmelding peterTilmelding = Controller.opretTilmelding(LocalDate.of(2024, 5, 18), LocalDate.of(2024, 5, 20), false, peter, havOgHimmel);
        Ledsager mia = Controller.opretLedsager("Mie", peterTilmelding);
        peterTilmelding.tilføjUdflugt(egeskov);
        peterTilmelding.tilføjUdflugt(trapholt);
        Booking booking = Controller.opretBooking(LocalDate.of(2024, 5, 18), LocalDate.of(2024, 5, 20), peterTilmelding, denHvideSvane);
        booking.tilføjTilvalg(wifi);


        Deltager lone = Controller.opretDeltager("Lonlon", "kode", "Lone Jensen", "Vej5", "Ulstrup", "London", "139580229");
        Tilmelding loneTilmelding = Controller.opretTilmelding(LocalDate.of(2024, 5, 18), LocalDate.of(2024, 5, 20), true, lone, havOgHimmel);
        Ledsager jan = Controller.opretLedsager("Jan Madsen", loneTilmelding);
        loneTilmelding.tilføjUdflugt(egeskov);
        loneTilmelding.tilføjUdflugt(byrundtur);
        Booking loneBooking = Controller.opretBooking(LocalDate.of(2024, 5, 18), LocalDate.of(2024, 5, 20),loneTilmelding,denHvideSvane);
        loneBooking.tilføjTilvalg(wifi);
    }
}