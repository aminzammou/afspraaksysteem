package nl.deketelman.project.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Klant extends Gebruiker{
    private ArrayList<Klant> alleklanten = new ArrayList();
    private String woonplaats;
    private String straat;
    private int huisnummer;
    private String stad;

    public Klant(String nm,String acht,  String em,int tel, String wacht,String wn, String str, int hs, String st){
        super(nm,acht,em,tel,wacht);
        woonplaats = wn;
        straat = str;
        huisnummer = hs;
        stad = st;
    }


    public Klant getAfspraakbyMail(String mail) {
        Klant nieuwe = null;
        for (Klant a : alleklanten) {
            if (a.getEmail().equals(mail)) {
                return a;
            }
        }
        return nieuwe;
    }
    public boolean createKlant(String nm,String acht,  String em,int tel, String wacht,String wn, String str, int hs, String st){
        boolean resp = false;
        if (getAfspraakbyMail(em) != null ){
            resp = false;
        }else {
            resp = true;
            alleklanten.add(new Klant(nm, acht, em, tel, wacht,wn,str,hs,st));
        }
        return resp;

    }


}
