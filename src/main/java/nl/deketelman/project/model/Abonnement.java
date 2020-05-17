package nl.deketelman.project.model;

import java.util.ArrayList;
import java.util.Date;

public class Abonnement {

    private String service;
    private Date date;
    private Klant klant;
    private ArrayList<Afspraak> alleafspraken = new ArrayList();
    public Abonnement(String ser,Date da){
        service = ser;
        date = da;
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
