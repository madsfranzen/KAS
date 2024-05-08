package model;

import java.time.LocalDate;
import java.time.Period;
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
     * OBS: ledsager og valgteUdflugter er nullable
     */
    public Tilmelding(LocalDate startDato, LocalDate slutDato, boolean foredragsholder, Deltager deltager, Konference konference) {
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.foredragsholder = foredragsholder;
        this.deltager = deltager;
        this.konference = konference;
    }

    public void tilføjUdflugt(Udflugt udflugt) {
        this.udflugter.add(udflugt);
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Deltager getDeltager() {
        return this.deltager;
    }

    public Konference getKonference() {
        return this.konference;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public boolean isForedragsholder() {
        return foredragsholder;
    }

    public Booking getBooking() {
        return booking;
    }

    @Override
    public String toString() {
        return String.format("%s : %s fra %s -> %s (%.2f kr)", konference, deltager, startDato, slutDato, beregnPris());
    }

    /* Beregner samlet pris på tilmeldingen */

    public double beregnPris() {
        double pris = 0 ;
        if (!foredragsholder){
            pris = konference.getPris() * getDage();
        }
        if (booking != null) {
            pris += booking.beregnPris();
        }

        for (Udflugt udflugt : udflugter) {
            pris += udflugt.getPris();
        }
        return pris;
    }

    /**
     * Nullable return value
     */
    public Ledsager getLedsager() {
        return ledsager;
    }

    private int getDage() {
        return Period.between(startDato, slutDato).getDays() + 1;
    }

    public void setLedsager(Ledsager ledsager) {
        this.ledsager = ledsager;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }
}