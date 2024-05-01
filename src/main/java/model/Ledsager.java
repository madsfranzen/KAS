package model;

public class Ledsager {
    private String navn;

    public Ledsager(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    @Override
    public String toString() {
        return "Ledsager{" +
                "navn='" + navn + '\'' +
                '}';
    }
}
