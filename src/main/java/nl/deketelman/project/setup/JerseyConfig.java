package nl.deketelman.project.setup;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("restservices")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("nl.deketelman.project.webservices, nl.deketelman.project.security");
        register(RolesAllowedDynamicFeature.class);
    }
}
