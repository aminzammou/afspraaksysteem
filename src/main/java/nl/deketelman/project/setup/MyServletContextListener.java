package nl.deketelman.project.setup;

import nl.deketelman.project.model.*;
import nl.deketelman.project.persistence.PersistenceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        try {
//            PersistenceManager.saveWorldToAzure();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            PersistenceManager.loadWorldFromAzure();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Werknemer werknemer = new Werknemer("Jhon Doe", "erde", "werknemer@hotmail.com",0623222222, "test123");
        werknemer.setWerknemer();

        Date date = new Date();
        Abonnement abonnement = new Abonnement("sanitair",date);
        Abonnement abonnement1 = new Abonnement("verwarming",date);
        Klant klant = new Klant("hans", "rie", "klant@hotmail.com",0621214532, "test123", "leidserijn","teststraat",10,"Utrecht",abonnement);
        Klant klant2 = new Klant("jos", "brie", "jos@hotmail.com",062222222, "ww123", "utrecht","strStraat",10,"Utrecht",abonnement);

//        LocalTime tijd = LocalTime.now();
//        LocalDate datum = LocalDate.now();
//        Bedrijf.getAlles().createAfspraak(datum, tijd, "test hallo", klant, werknemer);
//
//        klant.voegAbonnementToe(abonnement1);
//        Object a = klant.getAlleAbonementen();

    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            PersistenceManager.saveWorldToAzure();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Applicatie sluit af");
    }
}
