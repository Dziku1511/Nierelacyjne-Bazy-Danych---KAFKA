package db;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;

public abstract class AbsMongoRepository implements AutoCloseable{
   private ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
   private MongoCredential credential = MongoCredential.createCredential("admin", "admin", "adminpassword".toCharArray());
   private CodecRegistry codecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder()
           .automatic(true)
           .conventions(List.of(Conventions.ANNOTATION_CONVENTION))
           .build());
   private MongoClient MongoClient;
   public MongoDatabase libraryDB;

   protected void initConnection() {
      MongoClientSettings settings = MongoClientSettings.builder()
              .credential(credential)
              .applyConnectionString(connectionString)
              .uuidRepresentation(UuidRepresentation.STANDARD)
              .codecRegistry(CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),codecRegistry))
              .build();
      MongoClient = MongoClients.create(settings);
      libraryDB = MongoClient.getDatabase("library");
   }

   public com.mongodb.client.MongoClient getMongoClient() {
      return MongoClient;
   }
}
