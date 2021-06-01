package se.iths.jh.combinatoricsCalculator.rest;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.Optional;

@Path("/calculator")
@RegisterRestClient(configKey="calculatorApi")
@Produces(MediaType.APPLICATION_JSON)
public interface CalculatorService {

    @GET
    @Path("/permutations")
    long permuatations(@QueryParam("elements")  long elements,
                             @QueryParam("choices")  long choices,
                             @QueryParam("repetition") @DefaultValue("false") boolean repetition);
    @GET
    @Path("/combinations")
     long combinations(@QueryParam("elements") long elements,
                                 @QueryParam("choices")  long choices,
                                 @QueryParam("repetition") boolean repetition);
}
