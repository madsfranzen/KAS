package model;

public class Bruger {
    private String brugernavn;
    private String kodeord;

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

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public void setKodeord(String kodeord) {
        this.kodeord = kodeord;
    }
}
