package nl.deketelman.project.model;

import javax.security.auth.Subject;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class Gebruiker implements Serializable, Principal {
    private String naam;
    private String achternaam;
    private String email, role;
    private int telefoonnummer;
    private String wachtwoord;
    private static List<Gebruiker> alleGebruikers = new ArrayList<>();

    public Gebruiker(String nm,String acht,  String em,int tel, String wacht){
        naam = nm;
        achternaam = acht;
        email = em;
        telefoonnummer = tel;
        wachtwoord = wacht;
        role = "klant";
        alleGebruikers.add(this);
//        alleGebruikers.add(Bedrijf.getAlles().getAlleKlanten());
    }
    public static void setGebruikers(){
        alleGebruikers.addAll(Bedrijf.getAlles().getAlleKlanten());
        alleGebruikers.addAll(Bedrijf.getAlles().getAlleWerknemers());

    }
    public void setAdmin(){
        role="beheerder";
    }

    public void setWerknemer(){
        role="werknemer";
    }

    public static List<Gebruiker> getAlleGebruikers() {
        return alleGebruikers;
    }
    public static Gebruiker getUserByMail(String email){
        return alleGebruikers.stream()
                .filter(e -> e.email.equals(email))
                .findFirst()
                .orElse(null);
    }

    public static String validateLogin(String email, String wachtwoord){
        Gebruiker found = getUserByMail(email);
        if (found!=null) return wachtwoord.equals(found.wachtwoord) ? found.getRole(): null;
        return null;
    }

    public boolean implies(Subject subject) {
        return false;
    }
    public String getRole() {
        return role;
    }

    public String getRealName() {
        return naam;
    }

    @Override
    public String getName() {
        return email;
    }

    public String getEmail() {
        return email;
    }

    public String getNaam() {
        return naam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public int getTelefoonnummer() {
        return telefoonnummer;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }
}
