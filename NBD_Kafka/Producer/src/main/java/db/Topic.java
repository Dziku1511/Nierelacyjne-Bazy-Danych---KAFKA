package db;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.errors.TopicExistsException;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Topic {
    public static final String CONSUMER_GROUP_NAME = "ConsumerGroup";
    public static final String CLIENT_TOPIC = "rents";

    public static void createTopic(){
        Properties properties =  new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "172.25.0.3:9192,172.25.0.2:9292,172.25.0.4:9392");
        int partitionsNumber = 3;
        short replicationFactor = 2;
        try (Admin admin = Admin.create(properties)) {
            NewTopic newTopic = new NewTopic(Topic.CLIENT_TOPIC, partitionsNumber, replicationFactor);
            CreateTopicsOptions options = new CreateTopicsOptions()
                    .timeoutMs(10000)
                    .validateOnly(false)
                    .retryOnQuotaViolation(true);
            CreateTopicsResult result = admin.createTopics(List.of(newTopic), options);
            KafkaFuture<Void> futureResult = result.values().get(Topic.CLIENT_TOPIC);
            futureResult.get();
        } catch (ExecutionException ee) {
            System.out.println(ee.getCause());
            assertThat(ee.getCause(), is(instanceOf(TopicExistsException.class)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}