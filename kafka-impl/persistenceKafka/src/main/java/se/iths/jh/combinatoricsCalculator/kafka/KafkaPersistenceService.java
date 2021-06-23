package se.iths.jh.combinatoricsCalculator.kafka;


import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class KafkaPersistenceService {

    private Logger LOGGER = Logger.getLogger(KafkaPersistenceService.class);

    @Inject
    PersistenceService persistenceService;

    @Incoming("result")
    @Blocking
    public void handleKafkaRecord(KafkaRecord kafkaRecord){
        LOGGER.warn("receieved: " + kafkaRecord);
        try{
        persistenceService.persist(kafkaRecord);

        }catch (Exception e){
            LOGGER.warn("msg: " + e.getMessage() );
        }
    }



}
