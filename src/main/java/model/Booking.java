package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Booking {
    private LocalDate startDato;
    private LocalDate slutDato;
    private Tilmelding tilmelding;
    private final ArrayList<HotelTilvalg> tilvalg = new ArrayList<HotelTilvalg>();

    public Booking(LocalDate startDato, LocalDate slutDato, Tilmelding tilmelding) {
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.tilmelding = tilmelding;
    }
}
