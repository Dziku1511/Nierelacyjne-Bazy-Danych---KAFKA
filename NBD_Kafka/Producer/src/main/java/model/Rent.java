package model;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Rent implements Serializable {

    @BsonId
    UUID rentId;
    @NotNull
    @BsonProperty("beginTime")
    LocalDateTime begin;
    @NotNull
    @BsonProperty("endTime")
    LocalDateTime end;
    @BsonProperty("client")
    Client client;
    @BsonProperty("book")
    Book book;

    @BsonCreator
    public Rent(
            @BsonId UUID id,
            @BsonProperty("beginTime") LocalDateTime beginTime,
            @BsonProperty("endTime") LocalDateTime endTime,
            @BsonProperty("client") Client client,
            @BsonProperty("room") Book book) {
        this.rentId = id;
        this.begin = beginTime;
        this.end = endTime;
        this.client = client;
        this.book = book;
    }


    public String rentGetData() {
        return "Rent{" +
                "id=" + rentId +
                ", begin=" + begin +
                ", end=" + end +
                ", client=" + client +
                ", book=" + book +
                '}';
    }

}
