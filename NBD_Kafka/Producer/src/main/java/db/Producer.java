package db;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.UUIDSerializer;

import java.util.Properties;

public class Producer {

    private static KafkaProducer producer;

    public static KafkaProducer getProducer() {
        return producer;
    }

    public static void initProducer() {
        Properties producerConfig = new Properties();
        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class.getName());
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerConfig.put(ProducerConfig.CLIENT_ID_CONFIG, "local");
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9192, kafka2:9292, kafka3:9392");
        producerConfig.put(ProducerConfig.ACKS_CONFIG, "all"); //wszytskie repliki musza potwierdzić zapis danej
        producerConfig.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true); //zapobieganie duplikacji
        producer = new KafkaProducer(producerConfig);
    }

}
