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
    private final ArrayList<Hotel> hoteller = new ArrayList<>();
    private final ArrayList<Udflugt> udflugter = new ArrayList<>();
    private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();


    public Konference(String navn, String beskrivelse, String lokation, LocalDate startDato, LocalDate slutDato, double pris) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.lokation = lokation;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.pris = pris;
    }

    public void tilføjUdflugt(Udflugt udflugt) {
        this.udflugter.add(udflugt);
    }

    public void tilføjTilmelding(Tilmelding tilmelding) {
        this.tilmeldinger.add(tilmelding);
    }

    public void fjernTilmelding(Tilmelding tildmelding) {
        if (this.tilmeldinger.contains(tildmelding)) {
            this.tilmeldinger.remove(tildmelding);
        }
    }

    public void tilføjHotel(Hotel hotel){
        this.hoteller.add(hotel);
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    public ArrayList<Deltager> visDeltagere() {
        ArrayList<Deltager> alleDeltagere = new ArrayList<>();

        for (Tilmelding tilmelding : tilmeldinger) {
            alleDeltagere.add(tilmelding.getDeltager());
        }
        sorterDeltagere(alleDeltagere);
        return alleDeltagere;
    }

    public ArrayList<Hotel> getHoteller() {
        return new ArrayList<>(hoteller);
    }

    public static void sorterDeltagere(ArrayList<Deltager> arr) {
        for (int i = arr.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr.get(j).CompareTo(arr.get(j + 1)) > 0) {
                    Deltager temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }

    public void hent_DL_PåUdflugt() {
        for (Udflugt udflugt : udflugter) {
            System.out.print(udflugt.toString());
            for (Tilmelding tilmelding : tilmeldinger) {
                if (tilmelding.getUdflugter().contains(udflugt)) {
                    System.out.print(tilmelding.getLedsager());
                    System.out.print("(" + tilmelding.getDeltager().toString() + ")");
                }
            }
            System.out.println();
        }
    }

    public void opdaterInfo(String navn, String beskrivelse, String lokation, LocalDate startDato, LocalDate slutDato, double pris, ArrayList<Hotel> hoteller, ArrayList<Udflugt> udflugter) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.lokation = lokation;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.pris = pris;
        this.hoteller.clear();
        this.hoteller.addAll(hoteller);
        this.udflugter.clear();
        this.udflugter.addAll(udflugter);
    }

    public double getPris() {
        return this.pris;
    }

    public String getNavn() {
        return navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public String getLokation() {
        return lokation;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    @Override
    public String toString() {
        return String.format("%s", navn);
    }


}
