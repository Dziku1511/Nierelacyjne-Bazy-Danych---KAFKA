package model;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class Client {

    @BsonId
    UUID clientId;
    @NotNull
    @BsonProperty("firstName")
    String firstName;
    @NotNull
    @BsonProperty("lastName")
    String lastName;

    @BsonCreator
    public Client(@BsonId UUID id,
                  @BsonProperty("firstName") String firstName,
                  @BsonProperty("lastName") String lastName) {
        this.clientId = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "personalId=" + clientId +
                ", firstName='" + firstName +
                ", lastName='" + lastName +
                '}';
    }
}
