package nl.deketelman.project.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AfspraakTest {
    Werknemer werknemer = new Werknemer("Jhon Doe", "erde", "werknemer@hotmail.com",0623222222, "test123");

    Date date = new Date();
    Abonnement abonnement = new Abonnement("sanitair",date);
    Abonnement abonnement1 = new Abonnement("verwarming",date);
    Klant klant = new Klant("hans", "rie", "klant@hotmail.com",0621214532, "test123", "leidserijn","teststraat",10,"Utrecht",abonnement);

    LocalTime tijd = LocalTime.now();
    LocalDate datum = LocalDate.now();

//test voor iteratie 3
    @Test
    public void testCreateAfspraak(){
        Bedrijf.getAlles().createAfspraak(datum, tijd, "test hallo", klant, werknemer);
        assertEquals(Bedrijf.getAlles().getalleafspraken().size(),1);
    }

    @Test
    public void testDag(){
        LocalDate datum = Bedrijf.getAlles().getDag(0).plusDays(3);
        LocalDate datum3 = LocalDate.now();
        assertEquals(datum,datum3);
        System.out.println(datum);
    }
//    @Test
//    public void testDateInPastCreateAfspraak(){
//        assertFalse(werknemer.createAfspraak(mindatumm, mintijdd, "hallo daar"));
//    }

}