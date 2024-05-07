package model;

public class Bruger {
    private final String brugernavn;
    private final String kodeord;

    public Bruger(String brugernavn, String kodeord) {
        this.brugernavn = brugernavn;
        this.kodeord = kodeord;
    }

    public String getBrugernavn() {
        return brugernavn;
    }

    public String getKodeord() {
        return kodeord;
    }
}
