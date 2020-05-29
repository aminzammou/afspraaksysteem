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

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public void setWerknemer(Werknemer werknemer) {
        this.werknemer = werknemer;
    }

    public Afspraak(LocalDate dat, LocalTime ti, String bes, Klant kl, Werknemer we){
        datum = dat;
        tijd = ti;
        beschrijving = bes;
        klant = kl;
        werknemer = we;
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
