package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {

    private String navn;
    private LocalDate startDato;
    private LocalDate slutDato;
    private double pris;
    private String lokation;
    private String beskrivelse;
    private final ArrayList<Hotel> hoteller= new ArrayList<>();
    private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();


    public Konference(String navn, LocalDate startDato, LocalDate slutDato) {
        this.navn = navn;
        this.startDato = startDato;
        this.slutDato = slutDato;
    }


    public ArrayList<Deltager> visDeltagere() {
        ArrayList<Deltager> alleDeltagere = new ArrayList<>();

        for (Tilmelding tilmelding : tilmeldinger) {
            alleDeltagere.add(tilmelding.getDeltager);
        }

        return alleDeltagere;
    }
}
