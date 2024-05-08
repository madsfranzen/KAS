package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Controller {


    /**
     * Opretter en deltager
     */
    public static Deltager opretDeltager(String brugernavn, String kodeord, String navn, String adresse, String by, String land, String tlf) {
        Deltager deltager = new Deltager(brugernavn, kodeord, navn, adresse, by, land, tlf);
        Storage.storeDeltager(deltager);
        return deltager;
    }

    /**
     * Præbetingelse : at der er blevet lavet en Deltager - Opdaterer en deltager
     */
    public static void opdaterDeltager(Deltager deltager, String brugernavn, String kodeord, String navn, String adresse, String by, String land, String tlf) {
        deltager.opdaterInfo(brugernavn, kodeord, navn, adresse, by, land, tlf);
    }

    /**
     * Præbetingelse : At der er blevet lavet en Deltager - Sletter en deltager
     */
    public static void sletDeltager(Deltager deltager) {
        Storage.deleteDeltager(deltager);
    }

    /**
     * OBS: booking og ledsager er nullable
     */
    public static Tilmelding opretTilmelding(LocalDate startDato, LocalDate slutDato, boolean foredragsholder, Deltager deltager, Konference konference) {
        Tilmelding tilmelding = new Tilmelding(startDato, slutDato, foredragsholder, deltager, konference);
        konference.tilføjTilmelding(tilmelding);
        deltager.tilføjTilmelding(tilmelding);
        return tilmelding;
    }

    /**
     * Præbetingelse : At der er blevet lavet en tilmelding - Sletter en tilmelding
     */
    public static void sletTilmelding(Konference konference, Deltager deltager, Tilmelding tilmelding) {
        konference.fjernTilmelding(tilmelding);
        deltager.fjernTilmelding(tilmelding);
    }

    /**
     * Præbetingelse : At der er blevet lavet en konference - Opretter en udflugt til en konference
     */
    public static Udflugt opretUdflugt(String navn, double pris, LocalDate dato, boolean inklusivFrokost, Konference konference) {
        Udflugt udflugt = new Udflugt(navn, pris, dato, inklusivFrokost);
        konference.tilføjUdflugt(udflugt);
        return udflugt;
    }

    /**
     * Præbetingelser : Udflugt samt konference er blevet lavet - Tilføjer udflugt til konference
     */
    public static void tilføjUdflugtTilKonference(Konference konference, Udflugt udflugt) {
        konference.tilføjUdflugt(udflugt);
    }

    /**
     * Præbetingelser: Hotel samt konference er blevet lavet - Tilføjer hotel til konference
     */
    public static void tilføjHotelTilKonference(Konference konference, Hotel hotel) {
        konference.tilføjHotel(hotel);
    }

    /* Præbetingelser : Tilmelding samt Udflugt er blevet lavet -  Tilføjer udflugt til en tilmelding*/
    public static void tilføjUdflugtTilTilmelding(Udflugt udflugt, Tilmelding tilmelding) {
        tilmelding.tilføjUdflugt(udflugt);
    }

    /**
     * Opretter et hotel
     */
    public static Hotel opretHotel(String navn, double prisEnkelt, double prisDobbelt) {
        Hotel hotel = new Hotel(navn, prisEnkelt, prisDobbelt);
        Storage.storeHotel(hotel);
        return hotel;
    }

    /**
     * Præbetingelse: At der er blevet lavet et hotel - Sletter et hotel
     */
    public static void sletHotel(Hotel hotel) {
        Storage.deleteHotel(hotel);
    }

    /**
     * Præbetingelse: At der er blevet lavet en konference - Sletter en konference
     */
    public static void sletKonference(Konference konference) {
        Storage.deleteKonference(konference);
        if (!konference.getTilmeldinger().isEmpty()) {
            ArrayList<Tilmelding> tilmeldinger = konference.getTilmeldinger();
            for (Tilmelding tilmelding : tilmeldinger) {
                Deltager deltager = tilmelding.getDeltager();
                Controller.sletTilmelding(konference, deltager, tilmelding);
            }
        }
    }

    /**
     * Opretter et hotel tilvalg -  Denne metode bliver kun brugt i initStorage
     */
    public static HotelTilvalg opretHotelTilvalg(String hotelTilvalgType, double pris, Hotel hotel) {
        HotelTilvalg hotelTilvalg = new HotelTilvalg(hotelTilvalgType, pris);
        hotel.tilføjHotelTilvalg(hotelTilvalg);
        return hotelTilvalg;
    }

    /**
     * Præbetingelse: Der er blevet lavet et hotel - Tilføjer et HotelTilvalg til et bestemt hotel
     */
    public static void tilføjHotelTilvalg(Hotel hotel, HotelTilvalg hotelTilvalg) {
        hotel.tilføjHotelTilvalg(hotelTilvalg);
    }

    /**
     * Opretter en konference
     */
    public static Konference opretKonference(String navn, String beskrivelse, String lokation, LocalDate startDato, LocalDate slutDato, double pris) {
        Konference konference = new Konference(navn, beskrivelse, lokation, startDato, slutDato, pris);
        Storage.storeKonference(konference);
        return konference;
    }

    /**
     * Præbetingelse: At der er blevet lavet en konference. - Opdaterer konferencen
     */
    public static void opdaterKonference(Konference konference, String navn, String beskrivelse, String lokation, LocalDate startDato, LocalDate slutDato, double pris, ArrayList<Hotel> hoteller, ArrayList<Udflugt> udflugter) {
        konference.opdaterInfo(navn, beskrivelse, lokation, startDato, slutDato, pris, hoteller, udflugter);
    }

    /**
     * Præbetingelse : At der er lavet en tilmelding -  Opretter en booking
     */
    public static Booking opretBooking(LocalDate startDato, LocalDate slutDato, Tilmelding tilmelding, Hotel hotel) {
        Booking booking = new Booking(startDato, slutDato, tilmelding, hotel);
        tilmelding.setBooking(booking);
        hotel.tilføjBooking(booking);
        return booking;
    }

    /**
     * Tilføjer et HotelTilvalg til et hotel
     */
    public static void tilføjTilvalgTilBooking(Booking booking, HotelTilvalg tilvalg) {
        booking.tilføjTilvalg(tilvalg);
    }

    /**
     * Opretter et ledsager objekt, som har en tvunget associering med Deltager, Deltager er nullable
     */
    public static Ledsager opretLedsager(String navn, Tilmelding tilmelding) {
        Ledsager ledsager = new Ledsager(navn);
        tilmelding.setLedsager(ledsager);
        return ledsager;
    }

    /**
     * Viser alle hoteller samt Deltager og Ledsager
     */
    public static void visHotellerDL() {
        for (Hotel hotel : Storage.getHoteller()) {
            System.out.println(hotel.getNavn());
            for (Booking booking : hotel.getBookinger()) {
                Tilmelding tilmelding = booking.getTilmelding();
                String deltagerNavn = tilmelding.getDeltager().getNavn();
                if (tilmelding.getLedsager() == null) {
                    System.out.printf("%s\n", deltagerNavn);
                } else {
                    String ledsagerNavn = tilmelding.getLedsager().getNavn();
                    System.out.printf("%s (%s)\n", deltagerNavn, ledsagerNavn);
                }
            }
        }
    }

    public static ArrayList<Hotel> getHoteller() {
        return Storage.getHoteller();
    }

    public static ArrayList<Deltager> getDeltagere() {
        return Storage.getDeltagere();
    }

    public static ArrayList<Konference> getKonferencer() {
        return Storage.getKonferencer();
    }


}
