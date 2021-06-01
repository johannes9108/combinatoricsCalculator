package se.iths.jh.combinatoricsCalculator.rest;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.annotation.processing.Generated;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;

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
