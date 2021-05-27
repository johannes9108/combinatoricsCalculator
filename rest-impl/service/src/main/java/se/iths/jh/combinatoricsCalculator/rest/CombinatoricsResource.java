package se.iths.jh.combinatoricsCalculator.rest;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.Optional;

@Path("/service")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class CombinatoricsResource {

    @Inject
    CombinatoricsSerivce combinatoricsSerivce;

    @GET
    @Path("/permutations")
    public BigInteger permuatations(@QueryParam("elements") Optional<Long> elements,
                             @QueryParam("choices") Optional<Long> choices,
                             @QueryParam("repetition") @DefaultValue("false") Boolean repetition){
        return combinatoricsSerivce.calcuatePermutations(elements, choices, repetition);
    }
    @GET
    @Path("/combinations")
    public Long combinations(@QueryParam("elements")Optional<Long> elements,
                                 @QueryParam("choices") Optional<Long> choices,
                                 @QueryParam("repetition") Boolean repetition){
        return combinatoricsSerivce.calcuateCombinations(elements,choices,repetition);
    }
}
