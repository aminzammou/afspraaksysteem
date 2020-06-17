package nl.deketelman.project.webservices;

import nl.deketelman.project.model.Bedrijf;
import nl.deketelman.project.model.Klant;
import nl.deketelman.project.model.Werknemer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("werknemer")
public class WerknemerMiddelen {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWerknemer() {
        List<Werknemer> list = Bedrijf.getAlles().getAlleWerknemers();
        if (list == null){
            Map<String, String> messages = new HashMap<>();
            messages.put("error","Werknemer bestaat niet!");
            return Response.status(409).entity(messages).build();
        }
        return Response.ok(list).build();
    }
}
