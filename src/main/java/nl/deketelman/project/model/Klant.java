package nl.deketelman.project.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Klant extends Gebruiker implements Serializable {
    private ArrayList<Afspraak> alleAfspraken = new ArrayList();
    private static ArrayList<Abonnement> alleAbonementen = new ArrayList();
//    private static ArrayList<Klant> alleKlanten = new ArrayList();
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
        Bedrijf.voegKlantToe(this);
    }
    public static void voegAbonnementToe(Abonnement a){
        alleAbonementen.add(a);
    }

    public static ArrayList<Abonnement> getAlleAbonementen() {
        return alleAbonementen;
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
    //    public boolean createKlant(String nm,String acht,  String em,int tel, String wacht,String wn, String str, int hs, String st){
//        boolean resp = false;
//        if (getKlantByMail(em) != null ){
//            resp = false;
//        }else {
//            resp = true;
//            alleKlanten.add(new Klant(nm, acht, em, tel, wacht,wn,str,hs,st));
//        }
//        return resp;
//
//    }

//    public ArrayList<Klant> getAlleKlanten() {
//        return alleKlanten;
//    }
}
