package nl.deketelman.project.webservices;

import nl.deketelman.project.model.Afspraak;
import nl.deketelman.project.model.Bedrijf;
import nl.deketelman.project.model.Klant;
import nl.deketelman.project.model.Werknemer;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Path("afspraken")
public class AfsprakenMiddelen {

    @GET
    @RolesAllowed({"klant", "werknemer"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAfspraken() {

        List<Afspraak> list = Bedrijf.getAlles().getalleafspraken();
        if (list == null){
            Map<String, String> messages = new HashMap<>();
            messages.put("error","Afspraak does not exist");
            return Response.status(409).entity(messages).build();
        }
        return Response.ok(list).build();
    }

    @GET
    @RolesAllowed({"klant", "werknemer"})
    @Path("AfsprakenVandaag")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAfsprakenVanVandaag() {
        LocalDate a = LocalDate.now();
        List<Afspraak> list = Bedrijf.getAlles().getAfspraakbyOnlyDate(a);
        if (list == null){
            Map<String, String> messages = new HashMap<>();
            messages.put("error","Afspraak does not exist");
            return Response.status(409).entity(messages).build();
        }
        return Response.ok(list).build();
    }

    @GET
    @RolesAllowed({"klant", "werknemer"})
    @Path("afsprakenKlant")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAfsprakenVanKlant(@Context SecurityContext user) {
        Principal a = user.getUserPrincipal();
        String ml = a.getName();
        System.out.println("dit is de mail van de klant : " + ml);
        List<Afspraak> list = Bedrijf.getAlles().getKlantAfspraak(ml);
        if (list == null){
            Map<String, String> messages = new HashMap<>();
            messages.put("error","Afspraak does not exist");
            return Response.status(403).entity(messages).build();
        }
        return Response.ok(list).build();
    }

    @GET
    @RolesAllowed({"klant", "werknemer"})
    @Path("perWeek/{id}")

    @Produces(MediaType.APPLICATION_JSON)
    public Response getAfsprakenPerWeek(@PathParam("id") int week) {

        List<Afspraak> list = Bedrijf.getAlles().getAfspraakbyWeek(week);
        if (list == null){
            Map<String, String> messages = new HashMap<>();
            messages.put("error","Geen afspraken beschikbaar");
            return Response.status(409).entity(messages).build();
        }
        return Response.ok(list).build();
    }

    @POST
//    @Path("")
    @RolesAllowed({"klant", "werknemer"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response maakAfspraakAan(@FormParam("klant") String kmail, @FormParam("werknemer") String wmail, @FormParam("datum")  String datumstr, @FormParam("tijd") String tijdstr,@FormParam("beschrijving") String beschrijving){
//        @DateTimeFormat(pattern = "yyyy-MM-dd")
//        private LocalDate date;
        Klant klant = Bedrijf.getAlles().getKlantByMail(kmail);
        Werknemer werknemer = Bedrijf.getAlles().getWerknemerbyMail(wmail);
        LocalTime tijd = LocalTime.parse(tijdstr);
        LocalDate datum = LocalDate.parse(datumstr);
//        System.out.println(klant.toString());
//        System.out.println(werknemer);

        Boolean list = Bedrijf.getAlles().createAfspraak(datum, tijd, beschrijving, klant, werknemer);
        if (!list){
            Map<String, String> messages = new HashMap<>();
            messages.put("error"," Er bestaat al een afspraak om deze tijd, kies aub een andere afspraak");
            return Response.status(409).entity(messages).build();
        }
        return Response.ok(list).build();
    }
    @DELETE
    @RolesAllowed({"werknemer"})
    @Path("{mail}/{datum}/{tijd}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAfspraak(@PathParam("mail") String mail, @PathParam("datum") String datum, @PathParam("tijd") String tijd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localDate = LocalDate.parse(datum,formatter);
        LocalTime localTime = LocalTime.parse(tijd);
        return Bedrijf.getAlles().deleteAfspraak(localDate,mail,localTime)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();

    }

    @PUT
    @RolesAllowed({"werknemer"})
    @Path("{idMail}/{idDatum}/{idTtijd}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response UpdateAfspraak(@PathParam("idMail") String idMail, @PathParam("idDatum") String idDatum, @PathParam("idTtijd") String idTijd, @FormParam("klant") String kmail, @FormParam("werknemer") String wmail, @FormParam("datum")  String datumstr, @FormParam("tijd") String tijdstr,@FormParam("beschrijving") String beschrijving) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localDate = LocalDate.parse(idDatum,formatter);
        LocalTime localTime = LocalTime.parse(idTijd);
        Klant klant = Bedrijf.getAlles().getKlantByMail(kmail);
        Werknemer werknemer = Bedrijf.getAlles().getWerknemerbyMail(wmail);
        LocalTime tijd = LocalTime.parse(tijdstr);
        LocalDate datum = LocalDate.parse(datumstr);

        Boolean list = Bedrijf.getAlles().updateAfspraak(localDate,idMail,localTime,datum, tijd, beschrijving, klant, werknemer);
        if (!list){
            Map<String, String> messages = new HashMap<>();
            messages.put("error"," Het is niet gelukt om deze afspraak te veranderen !");
            return Response.status(409).entity(messages).build();
        }
        return Response.ok(list).build();



    }
}
