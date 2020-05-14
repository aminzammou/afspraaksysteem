package nl.deketelman.project.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class KlantTest {

    private LocalDate datumm = LocalDate.now().plusDays(2);
    private LocalTime tijdd = LocalTime.now().plusHours(2);
    Afspraak a = new Afspraak(datumm,tijdd,"hallo");
    Klant klant = new Klant("hans", "rie", "hans@hotmail.com",0621214532, "test123", "leidserijn","teststraat",10,"Utrecht");

    @Test
    public void testCreateDubbeleKlant(){
        klant.createKlant("hans", "rie", "hansa@hotmail.com",0621214532, "test123", "leidserijn","teststraat",10,"Utrecht");
        assertFalse(klant.createKlant("hans", "rie", "hansa@hotmail.com",0621214532, "test123", "leidserijn","teststraat",10,"Utrecht"));
    }

}