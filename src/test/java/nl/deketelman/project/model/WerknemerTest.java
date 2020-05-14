package nl.deketelman.project.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class WerknemerTest {

    private LocalDate datumm = LocalDate.now().plusDays(2);
    private LocalTime tijdd = LocalTime.now().plusHours(2);
    Afspraak a = new Afspraak(datumm,tijdd,"hallo");
    Werknemer werknemer = new Werknemer("hans", "erde", "klaas@hotmail.com",0621212121, "wachtwoord");

    @Test
    public void testCreateWerknemer(){
        werknemer.createWerknemer("pieter", "jan", "pieter@hotmail.com",0621212222, "123");
        assertFalse(werknemer.createWerknemer("pieter", "jan", "pieter@hotmail.com",0621212222, "123"));
    }

}