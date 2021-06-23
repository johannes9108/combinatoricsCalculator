package se.iths.jh.combinatoricsCalculator.kafka;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class KafkaRecordDeserializer extends ObjectMapperDeserializer<KafkaRecord> {
    public KafkaRecordDeserializer() {
        super(KafkaRecord.class);
    }
}
