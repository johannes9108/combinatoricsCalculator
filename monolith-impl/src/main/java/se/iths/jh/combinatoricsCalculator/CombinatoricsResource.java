package se.iths.jh.combinatoricsCalculator;


import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.jboss.logging.Logger;
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

    private Logger LOGGER = Logger.getLogger(CombinatoricsResource.class);

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(){
        return Template.index();
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/permutations")
    public TemplateInstance postPermutations(@FormParam("elements") Optional<Long> elements,
                                 @FormParam("choices")  Optional<Long> choices,
                                 @FormParam("repetition") String repetition){
        boolean rep = repetition !=null;
        try {
            return Template.index().data("result", combinatoricsSerivce.calcuatePermutations(elements,choices,rep));
        } catch (Exception e) {
            return Template.index().data("result", e.getMessage());

        }
    }
    @POST
    @Path("/combinations")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance postCombinations(@FormParam("elements") Optional<Long> elements,
                                 @FormParam("choices")  Optional<Long> choices,
                                 @FormParam("repetition") String repetition){
        boolean rep = repetition !=null;
        try {
            return Template.index().data("result", combinatoricsSerivce.calcuateCombinations(elements,choices,rep));
        } catch (Exception e) {
            return Template.index().data("result", e.getMessage());
        }
    }

    @CheckedTemplate
    public static class Template{
        public static native TemplateInstance index();

    }


    @GET
    @Path("permutations")
    public Response permuatations(@QueryParam("elements")Optional<Long> elements,
                                @QueryParam("choices") Optional<Long> choices,
                                @QueryParam("repetition") @DefaultValue("false") boolean repetition){

        long result = -1;
        try {
            result = combinatoricsSerivce.calcuatePermutations(elements,choices,repetition);
        } catch (WebApplicationException e) {
            return new ResponseBuilderImpl().entity(e.getMessage()).build();
        }
        return new ResponseBuilderImpl().entity(String.format("There are %d ways of picking %d items from a set of %d elements", result, choices.get(), elements.get())).status(Response.Status.OK).build();
    }

    @GET
    @Path("combinations")
    public Response combinations(@QueryParam("elements")Optional<Long> elements,
                                 @QueryParam("choices") Optional<Long> choices,
                                 @QueryParam("repetition") boolean repetition){

        long result = -1;
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
