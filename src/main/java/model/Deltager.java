package model;

import java.util.ArrayList;

public class Deltager extends Bruger {
    private String navn;
    private String adresse;
    private String by;
    private String land;
    private String tlf;
    private String firma;
    private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<Tilmelding>();

    public Deltager(String brugernavn, String kodeord, String navn, String adresse, String by, String land, String tlf) {
        super(brugernavn, kodeord);
        this.navn = navn;
        this.adresse = adresse;
        this.by = by;
        this.land = land;
        this.tlf = tlf;
    }

    @Override
    public String toString() {
        return String.format("%s", navn);
    }

    public String infoToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(navn + "\n"));
        sb.append(String.format(adresse + "\n"));
        sb.append(String.format(by + ", " + land + "\n"));
        sb.append(String.format(tlf + "\n"));
        if (firma != null) {
            sb.append(String.format(firma + "\n"));
        }
        return sb.toString();
    }

    public void tilføjTilmelding(Tilmelding tilmelding) {
        this.tilmeldinger.add(tilmelding);
    }

    public int CompareTo(Deltager other) {
        return this.navn.compareTo(other.navn);
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }

    public String getNavn() {
        return navn;
    }
}
