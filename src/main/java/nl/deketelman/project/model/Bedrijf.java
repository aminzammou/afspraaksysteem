package nl.deketelman.project.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Bedrijf implements Serializable {
    private ArrayList<Afspraak> alleAfspraken = new ArrayList();
    private ArrayList<Werknemer> alleWerknemers = new ArrayList();
    private ArrayList<Klant> alleKlanten = new ArrayList();


    public ArrayList<Afspraak> getalleafspraken() {
        return alleAfspraken;
    }

    private static Bedrijf alles = new Bedrijf();

    public static Bedrijf getAlles() {
        return alles;
    }

    public static void setBedrijf(Bedrijf bedrijf) {
        alles = bedrijf;
        Gebruiker.setGebruikers();
    }
    /**
     * voor het ophalen van een afspraak met een bepaalde datum en tijd
     */
    public Afspraak getAfspraakbyDate(LocalDate datum, LocalTime tijd) {
        Afspraak nieuwe = null;
        for (Afspraak a : alleAfspraken) {
            if (a.getDatum().equals(datum) && a.getTijd().equals(tijd)) {
                return a;
            }
        }
        return nieuwe;
    }
    /**
     * voor het ophalen van een afspraak met een bepaalde datum
     */
    public ArrayList<Afspraak> getAfspraakbyOnlyDate(LocalDate datum) {
        ArrayList<Afspraak> nieuwe = new ArrayList();
        for (Afspraak a : alleAfspraken) {
            if (a.getDatum().equals(datum)) {
                nieuwe.add(a);
            }
        }
        return nieuwe;
    }

    /**
     * voor het ophalen van de huidige week
     */
    public LocalDate getDag(int b){
        LocalDate date = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        final long calendarWeek = weekNumber + b;
        LocalDate desiredDate = LocalDate.now()
                .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, calendarWeek)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return desiredDate;
    }
    /**
     * voor het ophalen van alle afspraken van de gekoze week
     */
    public ArrayList<Afspraak> getAfspraakbyWeek(int week) {
        ArrayList<Afspraak> nieuwe = new ArrayList();
        LocalDate man = Bedrijf.getAlles().getDag(week);
        LocalDate dins = Bedrijf.getAlles().getDag(week).plusDays(1);
        LocalDate woen = Bedrijf.getAlles().getDag(week).plusDays(2);
        LocalDate dond = Bedrijf.getAlles().getDag(week).plusDays(3);
        LocalDate vrij = Bedrijf.getAlles().getDag(week).plusDays(4);
        LocalDate za = Bedrijf.getAlles().getDag(week).plusDays(5);
        LocalDate zo = Bedrijf.getAlles().getDag(week).plusDays(6);

        for (Afspraak a : alleAfspraken) {
            if (a.getDatum().equals(man) || a.getDatum().equals(dins) || a.getDatum().equals(woen) || a.getDatum().equals(dond) || a.getDatum().equals(vrij) || a.getDatum().equals(za) || a.getDatum().equals(zo)) {
                nieuwe.add(a);
            }
        }
        return nieuwe;
    }
    /**
     * voor het herschrijven van een bestaande afspraak
     */
    public boolean updateAfspraak(LocalDate gevondeDatum, String gevondeMail, LocalTime gevondeTijd, LocalDate datum, LocalTime tijd, String beschrijving, Klant kl, Werknemer we) {
        boolean resp = false;
        Afspraak gevonden = null;
        Klant k = Bedrijf.getAlles().getKlantByMail(gevondeMail);
        for (Afspraak a : alleAfspraken) {
            if (a.getDatum().equals(gevondeDatum) && a.getKlant().equals(k) && a.getTijd().equals(gevondeTijd)) {
                gevonden = a;
                break;
            }
        }
        if (getAfspraakbyDate(datum,tijd) != null ){
            resp = false;
        }else {
            resp = true;
            int nummer = alleAfspraken.indexOf(gevonden);
            if (resp){
                alleAfspraken.set(nummer,new Afspraak(datum,tijd,beschrijving,kl,we));
            }
        }

        return resp;
    }
    /**
     * voor het ophalen van een klant door middel van een mail
     */
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
    /**
     * voor het deleten van een afspraak
     */
    public boolean deleteAfspraak(LocalDate datum, String mail, LocalTime tijd) {
        boolean resp = false;
        Afspraak gevonden = null;
        Klant k = Bedrijf.getAlles().getKlantByMail(mail);
        for (Afspraak a : alleAfspraken) {
            if (a.getDatum().equals(datum) && a.getKlant().equals(k) && a.getTijd().equals(tijd)) {
                gevonden = a;
                resp = true;
                break;
            }
        }
        if (resp){
            alleAfspraken.remove(gevonden);
        }
        return resp;
    }

    public ArrayList<Werknemer> getAlleWerknemers() {
        return alleWerknemers;
    }

    public ArrayList<Klant> getAlleKlanten() {
        return alleKlanten;
    }

    /**
     * voor het aanmaken van een bestaande afspraak
     */
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
