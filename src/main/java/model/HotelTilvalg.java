package model;

public class HotelTilvalg {

    private HotelTilvalgType type;

    private double pris;

    public HotelTilvalg(HotelTilvalgType type, double pris) {
        this.type = type;
        this.pris = pris;
    }

    public double getPris() {
        return pris;
    }
}
