package se.iths.jh.combinatoricsCalculator.kafka;


import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class KafkaService {

    private Logger LOGGER = Logger.getLogger(KafkaService.class);


    @Inject
    CalculatorService calculatorService;

    @Incoming("input")
    @Outgoing("result")
    @Broadcast
    @Acknowledgment(Acknowledgment.Strategy.POST_PROCESSING)
    public KafkaRecord handleKafkaRecord(KafkaRecord kafkaRecord){
        long result;
        LOGGER.warn("receieved: " + kafkaRecord);
        result = KafkaRecord.Type.PERMUTATION.equals(kafkaRecord.getType()) ?
                calculatorService.calcuatePermutations(kafkaRecord.getElements(), kafkaRecord.getChoices(), kafkaRecord.getRepetition()):
                calculatorService.calcuateCombinations(kafkaRecord.getElements(), kafkaRecord.getChoices(), kafkaRecord.getRepetition());
        kafkaRecord.setResult(result);
        return kafkaRecord;
    }



}
