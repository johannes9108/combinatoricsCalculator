package se.iths.jh.combinatoricsCalculator.rest;

import org.jboss.logging.Logger;
import se.iths.jh.combinatoricsCalculator.rest.entities.Record;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Path("/persistence")
@ApplicationScoped
@Produces(MediaType.TEXT_PLAIN)
public class CombinatoricsResource implements PersistenceService{

    @Inject
    PersistenceService persistenceService;

    private Logger LOGGER = Logger.getLogger(CombinatoricsResource.class);

    @POST
    @Override
    public Record persist(@QueryParam("elements") long n,
                          @QueryParam("choices") long k,
                          @QueryParam("repetition") @DefaultValue("false") boolean repetition,
                          @QueryParam("result") long result) {
        LOGGER.warn(String.format("%s %s %s %s",n,k,repetition,result));
        return persistenceService.persist(n,k,repetition,result);
    }
    @POST
    @Override
    @Path("unknown")
    public Record update(@QueryParam("id") long id,
                         @QueryParam("elements") long n,
                         @QueryParam("choices") long k,
                         @QueryParam("repetition") @DefaultValue("false") boolean repetition,
                         @QueryParam("result") long result) {
        return persistenceService.update(id,n,k,repetition, result);
    }
    @DELETE
    @Override
    public Record delete(@QueryParam("id") long id) {
        return persistenceService.delete(id);
    }
    @GET
    @Path("/{id}")
    @Override
    public Record getBy(@QueryParam("id") long id) {
        return persistenceService.getBy(id);
    }
    @GET
    @Override
    public List<Record> getAll(HashMap<String, String> searchParams) {
        return persistenceService.getAll(searchParams);
    }
}
