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
}
