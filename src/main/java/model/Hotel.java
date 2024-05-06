package model;

import java.util.ArrayList;

public class Hotel {

    private String navn;
    private double prisEnkelt;
    private double prisDobbelt;
    private final ArrayList<Booking> bookinger = new ArrayList<>();
    private final ArrayList<HotelTilvalg> hotelTilvalg = new ArrayList<>();

    public Hotel(String navn, double prisEnkelt, double prisDobbelt) {
        this.navn = navn;
        this.prisEnkelt = prisEnkelt;
        this.prisDobbelt = prisDobbelt;
    }

    public void tilføjHotelTilvalg(HotelTilvalg hotelTilvalg) {
        this.hotelTilvalg.add(hotelTilvalg);
    }

    public void tilføjBooking(Booking booking) {
        this.bookinger.add(booking);
    }

    public double getPrisEnkelt() {
        return prisEnkelt;
    }

    public double getPrisDobbelt() {
        return prisDobbelt;
    }

    public ArrayList<Booking> getBookinger() {
        return bookinger;
    }

    public String getNavn() {
        return navn;
    }

    public ArrayList<HotelTilvalg> getHotelTilvalg() {
        return new ArrayList<>(hotelTilvalg);
    }

    @Override
    public String toString() {
        return String.format("%s", navn);
    }
}
