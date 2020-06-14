package nl.deketelman.project.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Bedrijf implements Serializable {
    String naam;
    private static ArrayList<Afspraak> alleAfspraken = new ArrayList();
    private static ArrayList<Werknemer> alleWerknemers = new ArrayList();
    private static ArrayList<Klant> alleKlanten = new ArrayList();
    public Bedrijf(String nm){
        naam = nm;
    }

    public static ArrayList<Afspraak> getalleafspraken() {
        return alleAfspraken;
    }
    public static Afspraak getAfspraakbyDate(LocalDate datum, LocalTime tijd) {
        Afspraak nieuwe = null;
        for (Afspraak a : alleAfspraken) {
            if (a.getDatum().equals(datum) && a.getTijd().equals(tijd)) {
                return a;
            }
        }
        return nieuwe;
    }

    public static ArrayList<Afspraak> getAfspraakbyOnlyDate(LocalDate datum) {
        ArrayList<Afspraak> nieuwe = new ArrayList();
        for (Afspraak a : alleAfspraken) {
            if (a.getDatum().equals(datum)) {
                nieuwe.add(a);
            }
        }
        return nieuwe;
    }

    public static ArrayList<Werknemer> getAlleWerknemers() {
        return alleWerknemers;
    }

    public static ArrayList<Klant> getAlleKlanten() {
        return alleKlanten;
    }

    public static boolean createAfspraak(LocalDate datum, LocalTime tijd, String beschrijving, Klant kl, Werknemer we ){
        boolean resp = false;
        if (getAfspraakbyDate(datum,tijd) != null ){
            resp = false;
        }else {
            resp = true;
            alleAfspraken.add(new Afspraak(datum,tijd,beschrijving,kl,we));
        }
        return resp;
    }
    public static Klant getKlantByMail(String mail) {
        Klant nieuwe = null;
        for (Klant a : alleKlanten) {
            if (a.getEmail().equals(mail)) {
                return a;
            }
        }
        return nieuwe;
    }
    public static Werknemer getWerknemerbyMail(String mail) {
        Werknemer nieuwe = null;
        for (Werknemer a : alleWerknemers) {
            if (a.getEmail().equals(mail)) {
                return a;
            }
        }
        return nieuwe;
    }
    public static void voegWerknemerToe(Werknemer w){
        alleWerknemers.add(w);
    }
    public static void voegKlantToe(Klant k){
        alleKlanten.add(k);
    }

}
