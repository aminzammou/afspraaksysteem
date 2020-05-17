package nl.deketelman.project.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Werknemer extends Gebruiker {
    private ArrayList<Afspraak> alleAfspraken = new ArrayList();
    private ArrayList<Werknemer> alleWerknemers = new ArrayList();
    public Werknemer(String nm,String acht,  String em,int tel, String wacht){
        super(nm,acht,em,tel,wacht);
    }

    public ArrayList<Afspraak> getalleafspraken() {
        return alleAfspraken;
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
    public boolean createAfspraak(LocalDate datum, LocalTime tijd, String beschrijving){
        boolean resp = false;
        if (getAfspraakbyDate(datum,tijd) != null ){
           resp = false;
        }else {
            resp = true;
            alleAfspraken.add(new Afspraak(datum,tijd,beschrijving));
        }
        return resp;
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
    public boolean createWerknemer(String nm,String acht,  String em,int tel, String wacht){
        boolean resp = false;
        if (getWerknemerbyMail(em) != null ){
            resp = false;
        }else {
            resp = true;
            alleWerknemers.add(new Werknemer(nm, acht, em, tel, wacht));
        }
        return resp;

    }

    public ArrayList<Werknemer> getAlleWerknemers() {
        return alleWerknemers;
    }
}
