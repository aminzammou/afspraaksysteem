package nl.deketelman.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Abonnement implements Serializable {

    private String service;
    private Date date;
    private Klant klant;
    private ArrayList<Afspraak> alleafspraken = new ArrayList();
    public Abonnement(String ser,Date da){
        service = ser;
        date = da;
        Klant.voegAbonnementToe(this);
    }

    public String getService() {
        return service;
    }

    public Date getDate() {
        return date;
    }

    public Klant getKlant() {
        return klant;
    }
}
