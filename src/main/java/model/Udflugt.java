package model;

import java.time.LocalDate;

public class Udflugt {

    private String navn;
    private double pris;
    private LocalDate dato;
    private boolean InklusivFrokost;


    public Udflugt(String navn, double pris, LocalDate dato, boolean inklusivFrokost) {
        this.navn = navn;
        this.pris = pris;
        this.dato = dato;
        InklusivFrokost = inklusivFrokost;
    }

    public double getPris() {
        return pris;
    }
}
