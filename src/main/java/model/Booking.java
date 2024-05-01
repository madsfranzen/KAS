package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Booking {
    private LocalDate startDato;
    private LocalDate slutDato;
    private Tilmelding tilmelding;
    private final ArrayList<HotelTilvalg> tilvalg = new ArrayList<HotelTilvalg>();
    private Hotel hotel;

    public Booking(LocalDate startDato, LocalDate slutDato, Tilmelding tilmelding, Hotel hotel) {
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.tilmelding = tilmelding;
    }

    /**
     * Beregner den samlede pris for en hotelbooking.
     * Pris for værelse samt valgte HotelTilvalg. (Wifi, Bad, osv)
     */
    public double beregnPris() {
        double pris = 0;
        // Pris for enkelt/dobbeltværelse
        if (tilmelding.getLedsager() != null) {
            pris = hotel.getPrisDobbelt();
        } else {
            pris = hotel.getPrisEnkelt();
        }
        // Pris for valgte HotelTilvalg
        for (HotelTilvalg HotelTilvalg : tilvalg) {
            pris = HotelTilvalg.getPris();
        }
        pris = pris * getDage();
        return pris;
    }

    private int getDage() {
        return Period.between(startDato, slutDato).getDays() + 1;
    }
}
