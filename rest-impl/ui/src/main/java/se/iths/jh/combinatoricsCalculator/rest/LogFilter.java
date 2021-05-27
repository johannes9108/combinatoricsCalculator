package se.iths.jh.combinatoricsCalculator.rest;

import org.jboss.logging.Logger;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class LogFilter implements ContainerRequestFilter, ClientRequestFilter {
    private Logger LOGGER = Logger.getLogger(LogFilter.class);
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        LOGGER.warn(requestContext.getUriInfo().getRequestUri().toString());
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        LOGGER.warn(requestContext.getUri().toString());

    }
}
