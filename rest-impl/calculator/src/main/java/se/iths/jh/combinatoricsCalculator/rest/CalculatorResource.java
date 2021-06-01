package se.iths.jh.combinatoricsCalculator.rest;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/calculator")
@Produces(MediaType.APPLICATION_JSON)
public class CalculatorResource {

    @Inject
    CalculatorService calculatorService;

    private Logger LOGGER = Logger.getLogger(CalculatorResource.class);

    @GET
    @Path("/permutations")
    public long permuatations(@QueryParam("elements") long elements,
                             @QueryParam("choices") long choices,
                             @QueryParam("repetition")  boolean repetition){
        LOGGER.info("Reached CalculatorResource: " + repetition);
        return calculatorService.calcuatePermutations(elements, choices, repetition);
    }
    @GET
    @Path("/combinations")
    public long combinations(@QueryParam("elements") long elements,
                             @QueryParam("choices") long choices,
                             @QueryParam("repetition") @DefaultValue("false") boolean repetition){
        LOGGER.info("Reached CalculatorResource");
        return calculatorService.calcuatePermutations(elements, choices, repetition);
    }

}
