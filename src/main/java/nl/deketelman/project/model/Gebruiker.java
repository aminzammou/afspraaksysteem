package nl.deketelman.project.model;

public class Gebruiker {
    private String naam;
    private String achternaam;
    private String email;
    private int telefoonnummer;
    private String wachtwoord;

    public Gebruiker(String nm,String acht,  String em,int tel, String wacht){
        naam = nm;
        achternaam = acht;
        email = em;
        telefoonnummer = tel;
        wachtwoord = wacht;
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
