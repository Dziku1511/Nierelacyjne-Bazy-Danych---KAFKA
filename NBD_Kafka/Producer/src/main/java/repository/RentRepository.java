package repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import db.AbsMongoRepository;
import db.Producer;
import db.Topic;
import model.Rent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.errors.TopicExistsException;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import static com.mongodb.client.model.Filters.eq;
import static db.Producer.getProducer;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class RentRepository extends AbsMongoRepository {


    private final MongoCollection<Rent> rentMongoCollection;


    public RentRepository() {
        super.initConnection();
        Topic.createTopic();
        rentMongoCollection = libraryDB.getCollection("rents", Rent.class);
        Producer.initProducer();

    }

    public void send (Rent rent) {
        try {
            ProducerRecord<UUID, String> record = new ProducerRecord<>(Topic.CLIENT_TOPIC,
                    rent.getRentId(), rent.rentGetData()+"LIBRARY");

//            System.out.println("\nrecord:toString()");
//            System.out.println(record.toString());
//            System.out.println("\n");

            Future<RecordMetadata> sent = getProducer().send(record);
            RecordMetadata recordMetadata = sent.get();
        } catch (ExecutionException ee) {
            System.out.println(ee.getCause());
            assertThat(ee.getCause(), is(instanceOf(TopicExistsException.class)));
        } catch (InterruptedException ie) {
            System.out.println(ie.getCause());
        }
    }
    public boolean addRent(Rent rent) {
       rentMongoCollection.insertOne(rent);
       send(rent);
       return true;
    }

    public Rent removeRent(String id) {
        Bson filter = eq("_id", UUID.fromString(id));
        return rentMongoCollection.findOneAndDelete(filter);
    }

    public void drop()
    {
        rentMongoCollection.drop();
    }

    public ArrayList<Rent> findAll() {
        return rentMongoCollection.find().into(new ArrayList<> ());
    }

    public ArrayList<Rent> find(String id) {
        Bson filter = eq("_id", UUID.fromString(id));

        return rentMongoCollection.find(filter).into(new ArrayList<> ());
    }

    public Rent updateOne(String id, Bson updateOperation) {
        Bson filter = eq("_id", UUID.fromString(id));
        return rentMongoCollection.findOneAndUpdate(filter, updateOperation);
    }

    public UpdateResult updateMany(Bson filter, Bson updateOperation) {
        return rentMongoCollection.updateMany(filter, updateOperation);
    }
    @Override
    public void close() throws Exception {

    }




}
