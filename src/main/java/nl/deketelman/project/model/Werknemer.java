package nl.deketelman.project.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Werknemer extends Gebruiker {
    private ArrayList<Afspraak> alleafspraken = new ArrayList();
    private ArrayList<Werknemer> allewerknemers = new ArrayList();
    public Werknemer(String nm,String acht,  String em,int tel, String wacht){
        super(nm,acht,em,tel,wacht);
    }

    public ArrayList<Afspraak> getalleafspraken() {
        return alleafspraken;
    }
    public Afspraak getAfspraakbyDate(LocalDate datum, LocalTime tijd) {
        Afspraak nieuwe = null;
        for (Afspraak a : alleafspraken) {
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
            alleafspraken.add(new Afspraak(datum,tijd,beschrijving));
        }
        return resp;
    }

    public Werknemer getAfspraakbyMail(String mail) {
        Werknemer nieuwe = null;
        for (Werknemer a : allewerknemers) {
            if (a.getEmail().equals(mail)) {
                return a;
            }
        }
        return nieuwe;
    }
    public boolean createWerknemer(String nm,String acht,  String em,int tel, String wacht){
        boolean resp = false;
        if (getAfspraakbyMail(em) != null ){
            resp = false;
        }else {
            resp = true;
            allewerknemers.add(new Werknemer(nm, acht, em, tel, wacht));
        }
        return resp;

    }
}
