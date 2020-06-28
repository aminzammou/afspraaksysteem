package nl.deketelman.project.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Werknemer extends Gebruiker implements Serializable {
    private ArrayList<Afspraak> alleAfspraken = new ArrayList();
    public Werknemer(String nm,String acht,  String em,int tel, String wacht){
        super(nm,acht,em,tel,wacht);
        Bedrijf.getAlles().voegWerknemerToe(this);
    }


}
