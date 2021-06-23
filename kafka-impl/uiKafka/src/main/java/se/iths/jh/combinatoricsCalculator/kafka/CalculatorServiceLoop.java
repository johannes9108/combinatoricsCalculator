package se.iths.jh.combinatoricsCalculator.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Properties;

public class CalculatorServiceLoop extends BasicConsumerLoop{

    private Logger LOGGER = Logger.getLogger(CalculatorServiceLoop.class);
    public CalculatorServiceLoop(Properties config, List<String> topics) {
        super(config, topics);

    }

    @Override
    public void process(ConsumerRecord<String, KafkaRecord> record) {
        long result;
        KafkaRecord kafkaRecord = record.value();
    }
}
