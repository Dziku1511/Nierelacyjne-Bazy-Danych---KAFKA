package model;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.UUID;


@Getter
@Setter
public class Book implements Serializable {


    @BsonId
    UUID bookId;
    @NotNull
    @BsonProperty("title")
    String title;
    @NotNull
    @BsonProperty("genre")
    String genre;
    @NotNull
    @BsonProperty("author")
    String author;
    @NotNull
    @Positive
    @BsonProperty("pageNumber")
    Integer pageNumber;


    @BsonCreator
    public Book(@BsonId UUID id,
                @BsonProperty("title") String title,
                @BsonProperty("genre") String genre,
                @BsonProperty("pageNumber") Integer pageNumber,
                @BsonProperty("author") String author) {
        this.bookId = id;
        this.title = title;
        this.genre = genre;
        this.pageNumber = pageNumber;
        this.author = author;
    }

    public String bookGetData() {
        return "Book{" +
                "id=" + bookId +
                ", title='" + title +
                ", genre='" + genre +
                ", pageNumber=" + pageNumber +
                ", author='" + author +
                '}';
    }
}