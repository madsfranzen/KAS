package controller;

import model.*;

import java.time.LocalDate;

public abstract class Controller {


    public static Udflugt opretUdflugt(String navn, double pris, LocalDate dato, boolean inklusivFrokost, Konference konference){
        Udflugt udflugt = new Udflugt(navn, pris, dato, inklusivFrokost);
        konference.tilføjUdflugt(udflugt);
        Storage.storeUdflugt(udflugt);
        return udflugt;
    }

    public static Hotel opretHotel(String navn, double prisEnkelt, double prisDobbelt){
       Hotel hotel = new Hotel(navn,prisEnkelt,prisDobbelt);
       Storage.storeHotel(hotel);
       return hotel;
    }

    public static HotelTilvalg opretHotelTilvalg(HotelTilvalgType hotelTilvalgType, double pris, Hotel hotel){
        HotelTilvalg hotelTilvalg = new HotelTilvalg(hotelTilvalgType,pris);
        hotel.tilføjHotelTilvalg(hotelTilvalg);
        return hotelTilvalg;
    }

    public static Konference opretKonference(String navn, String beskrivelse, String lokation, LocalDate startDato, LocalDate slutDato, double pris){
        Konference konference  = new Konference(navn,beskrivelse,lokation,startDato,slutDato,pris);
        Storage.storeKonference(konference);
        return konference;
    }
}
