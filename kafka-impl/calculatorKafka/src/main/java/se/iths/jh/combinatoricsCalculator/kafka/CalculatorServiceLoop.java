package se.iths.jh.combinatoricsCalculator.kafka;

import com.fasterxml.jackson.core.JsonParseException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Properties;

public class CalculatorServiceLoop extends BasicConsumerLoop{

    private Logger LOGGER = Logger.getLogger(CalculatorServiceLoop.class);
    private CalculatorService calculatorService;
    KafkaProducer<String, KafkaRecord> producer;
    public CalculatorServiceLoop(Properties config, List<String> topics, CalculatorService calculatorService) {
        super(config, topics);
        this.calculatorService = calculatorService;
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092,localhost:19093,localhost:19094");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.quarkus.kafka.client.serialization.ObjectMapperSerializer");
        producer = new KafkaProducer<String, KafkaRecord>(props);
    }

    @Override
    public void process(ConsumerRecord<String, KafkaRecord> record) {
        long result;
        KafkaRecord kafkaRecord = record.value();
        result = KafkaRecord.Type.PERMUTATION.equals(kafkaRecord.getType()) ?
                 calculatorService.calcuatePermutations(kafkaRecord.getElements(), kafkaRecord.getChoices(), kafkaRecord.getRepetition()):
                 calculatorService.calcuateCombinations(kafkaRecord.getElements(), kafkaRecord.getChoices(), kafkaRecord.getRepetition());
        kafkaRecord.setResult(result);
        producer.send(new ProducerRecord<String, KafkaRecord>("result",kafkaRecord));
        producer.flush();
    }
}
