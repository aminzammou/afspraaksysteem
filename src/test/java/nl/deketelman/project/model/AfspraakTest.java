//package nl.deketelman.project.model;
//
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AfspraakTest {
//    private LocalDate datumm = LocalDate.now().plusDays(2);
//    private LocalTime tijdd = LocalTime.now().plusHours(2);
//
//    private LocalDate mindatumm = LocalDate.now().minusDays(2);
//    private LocalTime mintijdd = LocalTime.now().minusHours(2);
//    Afspraak a = new Afspraak(datumm,tijdd,"hallo");
//    Werknemer werknemer = new Werknemer("hans", "erde", "hans@hotmail.com",0623222222, "test123");
//
//    @Test
//    public void testCreateAfspraak(){
//        werknemer.createAfspraak(datumm,tijdd,"hallo");
//        assertFalse(werknemer.createAfspraak(datumm, tijdd, "hallo daar"));
//    }
//    @Test
//    public void testDateInPastCreateAfspraak(){
//        assertFalse(werknemer.createAfspraak(mindatumm, mintijdd, "hallo daar"));
//    }
//
//}