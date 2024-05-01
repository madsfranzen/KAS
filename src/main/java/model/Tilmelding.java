package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tilmelding {
    private LocalDate startDato;
    private LocalDate slutDato;
    private boolean foredragsholder;
    private final ArrayList<Udflugt> udflugter = new ArrayList<Udflugt>();
    private Booking booking;
    private Ledsager ledsager;
    private Deltager deltager;
    private Konference konference;

    /**
     * OBS: booking og ledsager er nullable
     */
    public Tilmelding(LocalDate startDato, LocalDate slutDato, boolean foredragsholder, Booking booking, Ledsager ledsager, Deltager deltager, Konference konference) {
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.foredragsholder = foredragsholder;
        this.booking = booking;
        this.ledsager = ledsager;
        this.deltager = deltager;
        this.konference = konference;
    }

    public void tilføjUdflugt(Udflugt udflugt) {
        this.udflugter.add(udflugt);
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Deltager getDeltager () {
        return this.deltager;
    }
}