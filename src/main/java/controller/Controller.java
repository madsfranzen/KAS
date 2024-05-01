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

    /**
     * OBS: booking og ledsager er nullable
     */
    public static Tilmelding opretTilmelding(LocalDate startDato, LocalDate slutDato, boolean foredragsholder, Ledsager ledsager, Deltager deltager, Konference konference, ArrayList<Udflugt> valgteUdflugter) {
        Tilmelding tilmelding = new Tilmelding(startDato, slutDato, foredragsholder, ledsager, deltager, konference);
        // HENT UDFLUGTER NÅR UI er klar
        if (ledsager != null) {
            for (Udflugt udflugt : valgteUdflugter) {
                tilmelding.tilføjUdflugt(udflugt);
            }
        }
        konference.tilføjTilmelding(tilmelding);
        deltager.tilføjTilmelding(tilmelding);
        return tilmelding;


    }

    public static Udflugt opretUdflugt(String navn, double pris, LocalDate dato, boolean inklusivFrokost, Konference konference) {
        Udflugt udflugt = new Udflugt(navn, pris, dato, inklusivFrokost);
        konference.tilføjUdflugt(udflugt);
        return udflugt;
    }

    public static Hotel opretHotel(String navn, double prisEnkelt, double prisDobbelt) {
        Hotel hotel = new Hotel(navn, prisEnkelt, prisDobbelt);
        Storage.storeHotel(hotel);
        return hotel;
    }

    public static HotelTilvalg opretHotelTilvalg(HotelTilvalgType hotelTilvalgType, double pris, Hotel hotel) {
        HotelTilvalg hotelTilvalg = new HotelTilvalg(hotelTilvalgType, pris);
        hotel.tilføjHotelTilvalg(hotelTilvalg);
        return hotelTilvalg;
    }

    public static Konference opretKonference(String navn, String beskrivelse, String lokation, LocalDate startDato, LocalDate slutDato, double pris) {
        Konference konference = new Konference(navn, beskrivelse, lokation, startDato, slutDato, pris);
        Storage.storeKonference(konference);
        return konference;
    }

    public static Booking opretBooking(LocalDate startDato, LocalDate slutDato, Tilmelding tilmelding, Hotel hotel) {
        Booking booking = new Booking(startDato, slutDato, tilmelding, hotel);
        tilmelding.setBooking(booking);
        hotel.tilføjBooking(booking);
        return booking;
    }
}
