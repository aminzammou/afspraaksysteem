package nl.deketelman.project.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Klant extends Gebruiker implements Serializable {
    private ArrayList<Afspraak> alleAfspraken = new ArrayList();
    private static ArrayList<Abonnement> alleAbonementen = new ArrayList();
    private Abonnement abonement;
    private String woonplaats;
    private String straat;
    private int huisnummer;
    private String stad;

    public Klant(String nm,String acht,  String em,int tel, String wacht,String wn, String str, int hs, String st, Abonnement abo){
        super(nm,acht,em,tel,wacht);
        woonplaats = wn;
        straat = str;
        huisnummer = hs;
        stad = st;
        abonement = abo;
        alleAbonementen.add(abo);
        Bedrijf.getAlles().voegKlantToe(this);
    }
    public static void voegAbonnementToe(Abonnement a){
        alleAbonementen.add(a);
    }

    public static ArrayList<Abonnement> getAlleAbonementen() {
        return alleAbonementen;
    }

    public ArrayList<Afspraak> getAlleAfspraken() {
        return alleAfspraken;
    }

    @Override
    public String toString() {
        return "Klant{" +
                "alleAfspraken=" + alleAfspraken +
                ", abonement=" + abonement +
                ", woonplaats='" + woonplaats + '\'' +
                ", straat='" + straat + '\'' +
                ", huisnummer=" + huisnummer +
                ", stad='" + stad + '\'' +
                '}';
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public String getStraat() {
        return straat;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public String getStad() {
        return stad;
    }
}
