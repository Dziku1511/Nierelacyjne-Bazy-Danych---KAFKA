package repository;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ValidationOptions;
import db.AbsMongoRepository;
import model.Client;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;


public class ClientRepository extends AbsMongoRepository {


    private final MongoCollection<Client> clientMongoCollection;


    public ClientRepository(){
        super.initConnection();
        try{
            libraryDB.createCollection("clients", new CreateCollectionOptions().validationOptions(new ValidationOptions().validator(
                    Document.parse(
                            """
                                    {
                                        $jsonSchema: {
                                           bsonType: "object",
                                           required: [ "firstName", "lastName" ],
                                           properties: {
                                              firstName: {
                                                 bsonType: "string",
                                                 description: "must be a string"
                                              },
                                              lastName: {
                                                 bsonType: "string",
                                                 description: "must be a string"
                                              }
                                           }
                                        }
                                     }
                                    """
                    )
            )));
        } catch (MongoCommandException mongoCommandException) {

        }

        this.clientMongoCollection = libraryDB.getCollection("clients", Client.class);
    }





    public boolean addClient(Client client){
        if(isExisting(client)) {
            return false;
        }
        clientMongoCollection.insertOne(client);
        return true;
    }

    public Client removeClient(String id) {
        Bson filter = eq("_id", UUID.fromString(id));
        return clientMongoCollection.findOneAndDelete(filter);
    }
    private boolean isExisting(Client client) {
        Bson filter;
        filter = eq("_id", client.getClientId());

        ArrayList<Client> ls = clientMongoCollection.find(filter).into(new ArrayList<>());
        return !ls.isEmpty();
    }

    public Client update(String personalId, String key, String value) {

        Bson filter = eq("personalId", UUID.fromString(personalId));
        Bson setUpdate = set(key, value);
        return clientMongoCollection.findOneAndUpdate(filter, setUpdate);
    }
    public void drop()
    {
        clientMongoCollection.drop();
    }

    public ArrayList<Client> findAll() {
        return clientMongoCollection.find().into(new ArrayList<> ());
    }

    public ArrayList<Client> find(String personalId) {
        Bson filter = eq("personalId", UUID.fromString(personalId));

        return clientMongoCollection.find(filter, Client.class).into(new ArrayList<> ());
    }


    @Override
    public void close() throws Exception {

    }
}
