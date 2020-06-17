package nl.deketelman.project.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Bedrijf implements Serializable {
//    String naam;
    private ArrayList<Afspraak> alleAfspraken = new ArrayList();
    private ArrayList<Werknemer> alleWerknemers = new ArrayList();
    private ArrayList<Klant> alleKlanten = new ArrayList();
//    public Bedrijf(String nm){
//        naam = nm;
//    }

    public ArrayList<Afspraak> getalleafspraken() {
        return alleAfspraken;
    }

    private static Bedrijf alles = new Bedrijf();

    public static Bedrijf getAlles() {
        return alles;
    }

    public static void setBedrijf(Bedrijf bedrijf) {
        alles = bedrijf;
    }

    public Afspraak getAfspraakbyDate(LocalDate datum, LocalTime tijd) {
        Afspraak nieuwe = null;
        for (Afspraak a : alleAfspraken) {
            if (a.getDatum().equals(datum) && a.getTijd().equals(tijd)) {
                return a;
            }
        }
        return nieuwe;
    }

    public ArrayList<Afspraak> getAfspraakbyOnlyDate(LocalDate datum) {
        ArrayList<Afspraak> nieuwe = new ArrayList();
        for (Afspraak a : alleAfspraken) {
            if (a.getDatum().equals(datum)) {
                nieuwe.add(a);
            }
        }
        return nieuwe;
    }
    public ArrayList<Afspraak> getKlantAfspraak(String mail) {
        Klant k = Bedrijf.getAlles().getKlantByMail(mail);
        ArrayList<Afspraak> nieuwe = new ArrayList();
        for (Afspraak a : alleAfspraken) {
            if (a.getKlant().equals(k)) {
                nieuwe.add(a);
            }
        }
        return nieuwe;
    }

    public ArrayList<Werknemer> getAlleWerknemers() {
        return alleWerknemers;
    }

    public ArrayList<Klant> getAlleKlanten() {
        return alleKlanten;
    }

    public boolean createAfspraak(LocalDate datum, LocalTime tijd, String beschrijving, Klant kl, Werknemer we ){
        boolean resp = false;
        if (getAfspraakbyDate(datum,tijd) != null ){
            resp = false;
        }else {
            resp = true;
            alleAfspraken.add(new Afspraak(datum,tijd,beschrijving,kl,we));
        }
        return resp;
    }
    public Klant getKlantByMail(String mail) {
        Klant nieuwe = null;
        for (Klant a : alleKlanten) {
            if (a.getEmail().equals(mail)) {
                return a;
            }
        }
        return nieuwe;
    }
    public Werknemer getWerknemerbyMail(String mail) {
        Werknemer nieuwe = null;
        for (Werknemer a : alleWerknemers) {
            if (a.getEmail().equals(mail)) {
                return a;
            }
        }
        return nieuwe;
    }
    public void voegWerknemerToe(Werknemer w){
        alleWerknemers.add(w);
    }
    public void voegKlantToe(Klant k){
        alleKlanten.add(k);
    }

}
