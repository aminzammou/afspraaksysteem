package nl.deketelman.project.webservices;

import nl.deketelman.project.model.Afspraak;
import nl.deketelman.project.model.Bedrijf;
import nl.deketelman.project.model.Klant;
import nl.deketelman.project.model.Werknemer;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Path("afspraken")
public class AfsprakenMiddelen {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAfspraken() {

        List<Afspraak> list = Bedrijf.getalleafspraken();
        if (list == null){
            Map<String, String> messages = new HashMap<>();
            messages.put("error","Shop does not exist");
            return Response.status(409).entity(messages).build();
        }
        return Response.ok(list).build();
    }

    @POST
//    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response maakAfspraakAan(@FormParam("klant") String kmail, @FormParam("werknemer") String wmail, @FormParam("datum")  String datumstr, @FormParam("tijd") String tijdstr,@FormParam("beschrijving") String beschrijving){
//        @DateTimeFormat(pattern = "yyyy-MM-dd")
//        private LocalDate date;
        Klant klant = Bedrijf.getKlantByMail(kmail);
        Werknemer werknemer = Bedrijf.getWerknemerbyMail(wmail);
        LocalTime tijd = LocalTime.parse(tijdstr);
        LocalDate datum = LocalDate.parse(datumstr);
//        System.out.println(klant.toString());
//        System.out.println(werknemer);

        Boolean list = Bedrijf.createAfspraak(datum, tijd, beschrijving, klant, werknemer);
        if (!list){
            Map<String, String> messages = new HashMap<>();
            messages.put("error","Sommeting went wrong");
            return Response.status(409).entity(messages).build();
        }
        return Response.ok(list).build();
    }
}
