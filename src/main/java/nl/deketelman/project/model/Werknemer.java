package nl.deketelman.project.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Werknemer extends Gebruiker {
    private ArrayList<Afspraak> alleAfspraken = new ArrayList();
//    private ArrayList<Werknemer> alleWerknemers = new ArrayList();
    public Werknemer(String nm,String acht,  String em,int tel, String wacht){
        super(nm,acht,em,tel,wacht);
        Bedrijf.voegWerknemerToe(this);
    }


//    public ArrayList<Werknemer> getAlleWerknemers() {
//        return alleWerknemers;
//    }
}
