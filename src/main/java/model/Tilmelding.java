package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tilmelding {
    private LocalDate startDato;
    private LocalDate slutDato;
    private boolean foredragsholder;
    private final ArrayList<Udflugt> udflugter = new ArrayList<Udflugt>();

    public Tilmelding(LocalDate startDato, LocalDate slutDato, boolean foredragsholder) {
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.foredragsholder = foredragsholder;
    }

    public tilføjUdflugt(Udflugt udflugt){
        this.udflugter(udflugt);
    }
}