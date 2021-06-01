package se.iths.jh.combinatoricsCalculator.rest;

import org.jboss.logging.Logger;
import se.iths.jh.combinatoricsCalculator.rest.entities.Record;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Path("/persistence")
@Produces(MediaType.APPLICATION_JSON)
public class PersistenceResource implements PersistenceService{

    @Inject
    PersistenceService persistenceService;

    private Logger LOGGER = Logger.getLogger(PersistenceResource.class);


    @POST
    @Path("/persist")
    @Override
    public Record persist(@QueryParam("id") Optional<Long> id, @QueryParam("elements") long n,
                          @QueryParam("choices") long k,
                          @QueryParam("repetition") boolean repetition,
                          @QueryParam("result") long result) {
        LOGGER.warn(String.format("%s %s %s %s",n,k,repetition,result));
        return persistenceService.persist(id, n,k,repetition,result);
    }

    @DELETE
    @Override
    @Path("/{id}")
    public Record delete(@PathParam("id") long id) {
        return persistenceService.delete(id);
    }
    @GET
    @Path("/{id}")
    @Override
    public Record getBy(@PathParam("id") long id) {

        return persistenceService.getBy(id);
    }
    @GET
    @Override
    public List<Record> getAll(HashMap<String, String> searchParams) {

        return persistenceService.getAll(searchParams);
    }
}
