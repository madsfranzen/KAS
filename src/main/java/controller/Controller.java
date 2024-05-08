package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Controller {

    public static Deltager opretDeltager(String brugernavn, String kodeord, String navn, String adresse, String by, String land, String tlf) {
        Deltager deltager = new Deltager(brugernavn, kodeord, navn, adresse, by, land, tlf);
        Storage.storeDeltager(deltager);
        return deltager;
    }


    public static void opdaterDeltager(Deltager deltager, String brugernavn, String kodeord, String navn, String adresse, String by, String land, String tlf) {
        deltager.opdaterInfo(brugernavn, kodeord, navn, adresse, by, land, tlf);
    }

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


    public static void sletTilmelding(Konference konference, Deltager deltager, Tilmelding tilmelding) {
        konference.fjernTilmelding(tilmelding);
        deltager.fjernTilmelding(tilmelding);
    }

    public static Udflugt opretUdflugt(String navn, double pris, LocalDate dato, boolean inklusivFrokost, Konference konference) {
        Udflugt udflugt = new Udflugt(navn, pris, dato, inklusivFrokost);
        konference.tilføjUdflugt(udflugt);
        return udflugt;
    }

    public static void tilføjUdflugtTilKonference(Konference konference, Udflugt udflugt) {
        konference.tilføjUdflugt(udflugt);
    }

    public static void tilføjHotelTilKonference(Konference konference, Hotel hotel) {
        konference.tilføjHotel(hotel);
    }

    public static void tilføjUdflugtTilTilmelding(Udflugt udflugt, Tilmelding tilmelding) {
        tilmelding.tilføjUdflugt(udflugt);
    }

    public static Hotel opretHotel(String navn, double prisEnkelt, double prisDobbelt) {
        Hotel hotel = new Hotel(navn, prisEnkelt, prisDobbelt);
        Storage.storeHotel(hotel);
        return hotel;
    }

    public static void sletHotel(Hotel hotel) {
        Storage.deleteHotel(hotel);
    }

    public static void sletKonference(Konference konference) {
        Storage.deleteKonference(konference);
    }

    public static HotelTilvalg opretHotelTilvalg(String hotelTilvalgType, double pris, Hotel hotel) {
        HotelTilvalg hotelTilvalg = new HotelTilvalg(hotelTilvalgType, pris);
        hotel.tilføjHotelTilvalg(hotelTilvalg);
        return hotelTilvalg;
    }

    public static void tilføjHotelTilvalg(Hotel hotel, HotelTilvalg hotelTilvalg) {
        hotel.tilføjHotelTilvalg(hotelTilvalg);
    }

    public static Konference opretKonference(String navn, String beskrivelse, String lokation, LocalDate startDato, LocalDate slutDato, double pris) {
        Konference konference = new Konference(navn, beskrivelse, lokation, startDato, slutDato, pris);
        Storage.storeKonference(konference);
        return konference;
    }

    public static void opdaterKonference(Konference konference, String navn, String beskrivelse, String lokation, LocalDate startDato, LocalDate slutDato, double pris, ArrayList<Hotel> hoteller, ArrayList<Udflugt> udflugter) {
        konference.opdaterInfo(navn, beskrivelse, lokation, startDato, slutDato, pris, hoteller, udflugter);
    }

    public static Booking opretBooking(LocalDate startDato, LocalDate slutDato, Tilmelding tilmelding, Hotel hotel) {
        Booking booking = new Booking(startDato, slutDato, tilmelding, hotel);
        tilmelding.setBooking(booking);
        hotel.tilføjBooking(booking);
        return booking;
    }

    public static void tilføjTilvalgTilBooking(Booking booking, HotelTilvalg tilvalg) {
        booking.tilføjTilvalg(tilvalg);
    }

    public static Ledsager opretLedsager(String navn, Tilmelding tilmelding) {
        Ledsager ledsager = new Ledsager(navn);
        tilmelding.setLedsager(ledsager);
        return ledsager;
    }

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
