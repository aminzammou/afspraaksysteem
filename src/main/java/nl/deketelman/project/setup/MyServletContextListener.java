package nl.deketelman.project.setup;

import nl.deketelman.project.model.Abonnement;
import nl.deketelman.project.model.Bedrijf;
import nl.deketelman.project.model.Klant;
import nl.deketelman.project.model.Werknemer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Werknemer werknemer = new Werknemer("hans", "erde", "werknemer@hotmail.com",0623222222, "test123");
        Date date = new Date();
        Abonnement abonnement = new Abonnement("sanitair",date);
        Abonnement abonnement1 = new Abonnement("verwarming",date);
        Klant klant = new Klant("hans", "rie", "klant@hotmail.com",0621214532, "test123", "leidserijn","teststraat",10,"Utrecht",abonnement);
        klant.voegAbonnementToe(abonnement1);
        Object a = klant.getAlleAbonementen();
//        Bedrijf.voegKlantToe(klant);
//        System.out.println(Bedrijf.getAlleKlanten());
//        System.out.println("hallllooooooooooo");
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("terminating application");
    }
}