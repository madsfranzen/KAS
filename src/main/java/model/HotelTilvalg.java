package model;

public class HotelTilvalg {

    private String type;

    private double pris;

    public HotelTilvalg(String type, double pris) {
        this.type = type;
        this.pris = pris;
    }

    public double getPris() {
        return pris;
    }
}
