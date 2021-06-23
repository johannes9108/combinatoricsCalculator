//package se.iths.jh.combinatoricsCalculator.kafka;
//
//import io.quarkus.runtime.Quarkus;
//import io.quarkus.runtime.QuarkusApplication;
//import io.quarkus.runtime.annotations.QuarkusMain;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//
//import java.util.Collections;
//import java.util.Properties;
//
//@QuarkusMain
//public class Main {
//    public static void main(String[] args) {
//        Quarkus.run(MyApp.class,args);
//    }
//
//    public static class MyApp implements QuarkusApplication{
//
//        @Override
//        public int run(String... args) throws Exception {
//            Properties props = new Properties();
//            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092,localhost:19093,localhost:19094");
//            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "se.iths.jh.combinatoricsCalculator.kafka.KafkaRecordDeserializer");
//            props.put(ConsumerConfig.GROUP_ID_CONFIG, "calculatorService");
//
//            CalculatorServiceLoop serviceLoop = new CalculatorServiceLoop(props, Collections.singletonList("input2"),
//                    new CalculatorService());
//            serviceLoop.run();
//
//            return 0;
//        }
//    }
//}
