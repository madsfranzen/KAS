package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {

    private String navn;
    private String beskrivelse;
    private String lokation;
    private LocalDate startDato;
    private LocalDate slutDato;
    private double pris;
    private final ArrayList<Hotel> hoteller= new ArrayList<>();
    private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private final ArrayList<Udflugt> udflugter = new ArrayList<>();

    public Konference(String navn, String beskrivelse, String lokation, LocalDate startDato, LocalDate slutDato, double pris) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.lokation = lokation;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.pris = pris;
    }

    public void tilføjUdflugt(Udflugt udflugt){
        this.udflugter.add(udflugt);
    }

    public void tilføjTilmelding(Tilmelding tilmelding){
        this.tilmeldinger.add(tilmelding);
    }


    public ArrayList<Deltager> visDeltagere() {
        ArrayList<Deltager> alleDeltagere = new ArrayList<>();

        for (Tilmelding tilmelding : tilmeldinger) {
            alleDeltagere.add(tilmelding.getDeltager());
        }

        return alleDeltagere;
    }
}
