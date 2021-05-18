package se.iths.jh.combinatoricsCalculator;


import org.jboss.resteasy.specimpl.ResponseBuilderImpl;
import se.iths.jh.combinatoricsCalculator.entities.Record;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("combinatorics")
@Produces(MediaType.TEXT_PLAIN)
public class CombinatoricsResource {

    @Inject
    CombinatoricsSerivce combinatoricsSerivce;


    @GET
    @Path("permutations")
    public Response permuatations(@QueryParam("elements")Optional<Integer> elements,
                                @QueryParam("choices") Optional<Integer> choices,
                                @QueryParam("repetition") Boolean repetition){

        int result = 0;
        try {
            result = combinatoricsSerivce.calcuatePermutations(elements,choices,repetition);
        } catch (WebApplicationException e) {
            return new ResponseBuilderImpl().entity(e.getMessage()).build();
        }
        return new ResponseBuilderImpl().entity(String.format("There are %d ways of picking %d items from a set of %d elements", result, choices.get(), elements.get())).status(Response.Status.OK).build();
    }

    @GET
    @Path("combinations")
    public Response combinations(@QueryParam("elements")Optional<Integer> elements,
                                 @QueryParam("choices") Optional<Integer> choices,
                                 @QueryParam("repetition") Boolean repetition){

        int result = 0;
        try {
            result = combinatoricsSerivce.calcuateCombinations(elements,choices,repetition);
        } catch (WebApplicationException e) {
            return new ResponseBuilderImpl().entity(e.getMessage()).build();
        }
        return new ResponseBuilderImpl().entity(String.format("There are unique %d ways of picking %d items from a set of %d elements", result, choices.get(), elements.get())).status(Response.Status.OK).build();
    }

    @GET
    @Path("all")
    public Response getAll(){

        List<Record>  result = null;
        try {
            result = combinatoricsSerivce.getAll();
        } catch (WebApplicationException e) {
            return new ResponseBuilderImpl().entity(e.getMessage()).build();
        }
        return new ResponseBuilderImpl().entity(result).status(Response.Status.OK).build();
    }

}
