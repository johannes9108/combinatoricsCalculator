package se.iths.jh.combinatoricsCalculator.kafka;

import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;

@ApplicationScoped
public class KafkaService {

    private Logger LOGGER = Logger.getLogger(KafkaService.class);


    @Inject
    @Channel("input")
    Emitter<KafkaRecord> recordEmitter;

    public void send(Optional<Long> elements, Optional<Long> choices, boolean repetition, KafkaRecord.Type type) {
        KafkaRecord kafkaRecord = new KafkaRecord(LocalDateTime.now(), elements.get(), choices.get(), repetition, -1L,
                KafkaRecord.Type.PERMUTATION.equals(type) ? KafkaRecord.Type.PERMUTATION : KafkaRecord.Type.COMBINATION);
        recordEmitter.send(kafkaRecord).whenComplete((unused, throwable) -> LOGGER.warn("Complete"));

    }


}
