package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Controller {

    private static Deltager opretDeltager(String brugernavn, String kodeord, String navn, String adresse, String by, String land, String tlf) {
        Deltager deltager = new Deltager(brugernavn, kodeord, navn, adresse, by, land, tlf);
        Storage.storeDeltager(deltager);
        return deltager;
    }

    /**
     * OBS: booking og ledsager er nullable
     */
    public static Tilmelding opretTilmelding(LocalDate startDato, LocalDate slutDato, boolean foredragsholder, Booking booking, Ledsager ledsager, Deltager deltager, Konference konference, ArrayList<Udflugt> valgteUdflugter) {
        Tilmelding tilmelding = new Tilmelding(startDato, slutDato, foredragsholder, booking, ledsager, deltager, konference);
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
}
