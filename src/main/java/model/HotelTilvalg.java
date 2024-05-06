package model;

public class HotelTilvalg {

    private String type;

    private double pris;

    public HotelTilvalg(String type, double pris) {
        this.type = type;
        this.pris = pris;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f kr)", type, pris);
    }

    public double getPris() {
        return pris;
    }
}
