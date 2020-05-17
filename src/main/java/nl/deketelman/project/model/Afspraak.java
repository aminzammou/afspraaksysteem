package nl.deketelman.project.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Afspraak {

    private LocalDate datum;
    private LocalTime tijd;
    private String beschrijving;
    private Klant klant;
    private Werknemer werknemer;
    private Abonnement abonnement;

    public Afspraak(LocalDate dat, LocalTime ti, String bes){
        datum = dat;
        tijd = ti;
        beschrijving = bes;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public LocalTime getTijd() {
        return tijd;
    }

    public String getBeschrijving() {
        return beschrijving;
    }
}
