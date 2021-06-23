package se.iths.jh.combinatoricsCalculator.rest;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/persistence")
@RegisterRestClient(configKey = "persistenceAPI")
@Produces(MediaType.APPLICATION_JSON)
public interface PersistenceService {

    @POST
    @Path("/persist")
    Record persist(@QueryParam("elements") long n,
                             @QueryParam("choices") long k,
                             @QueryParam("repetition") boolean repetition,
                             @QueryParam("result") long result);


}
