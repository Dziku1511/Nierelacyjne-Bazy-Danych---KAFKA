package consumer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Topic.createTopic();
        Consumer consumer = new Consumer();
        consumer.initConsumer();
        consumer.consume(Consumer.getKafkaConsumer());
    }
}
