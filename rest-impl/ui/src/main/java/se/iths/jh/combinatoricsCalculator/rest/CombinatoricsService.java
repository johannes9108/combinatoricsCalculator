package se.iths.jh.combinatoricsCalculator.rest;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.Optional;

@Path("/service")
@RegisterRestClient(configKey="combinatorics-api")
@Produces(MediaType.APPLICATION_JSON)
public interface CombinatoricsService {

    @GET
    @Path("/permutations")
    BigInteger permuatations(@QueryParam("elements") Long elements,
                             @QueryParam("choices") Long choices,
                             @QueryParam("repetition") @DefaultValue("false") Boolean repetition);
    @GET
    @Path("/combinations")
    Long combinations(@QueryParam("elements")Long elements,
                                 @QueryParam("choices") Long choices,
                                 @QueryParam("repetition") Boolean repetition);
}
