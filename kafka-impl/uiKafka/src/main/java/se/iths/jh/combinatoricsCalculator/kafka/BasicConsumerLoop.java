package se.iths.jh.combinatoricsCalculator.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.jboss.logging.Logger;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BasicConsumerLoop implements Runnable {
    private final KafkaConsumer<String, KafkaRecord> consumer;
    private final List<String> topics;
    private final AtomicBoolean shutdown;
    private final CountDownLatch shutdownLatch;

    private Logger LOGGER = Logger.getLogger(BasicConsumerLoop.class);


    public BasicConsumerLoop(Properties config, List<String> topics) {
        this.consumer = new KafkaConsumer<>(config);
        this.topics = topics;
        this.shutdown = new AtomicBoolean(false);
        this.shutdownLatch = new CountDownLatch(1);
    }

    public abstract void process(ConsumerRecord<String, KafkaRecord> record);

    public void run() {
        try {
            consumer.subscribe(topics);

            while (!shutdown.get()) {
                ConsumerRecords<String, KafkaRecord> records = consumer.poll(Duration.ofMillis(500));
                records.forEach(record -> process(record));
            }
        }catch (Exception e){
        LOGGER.error("WRONG FORMAT");
        LOGGER.error("WRONG FORMAT");
    }

        finally {
            consumer.close();
            shutdownLatch.countDown();
        }
    }

    public void shutdown() throws InterruptedException {
        shutdown.set(true);
        shutdownLatch.await();
    }
}
