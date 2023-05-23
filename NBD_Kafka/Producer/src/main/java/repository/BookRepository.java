package repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import db.AbsMongoRepository;
import model.Book;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.UUID;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;

public class BookRepository extends AbsMongoRepository {

    public BookRepository(){
        super.initConnection();
        bookMongoCollection = libraryDB.getCollection("books", Book.class);
    }

    private final MongoCollection<Book> bookMongoCollection;

    public boolean addBook(Book book){
        if(isExisting(book)) {
            return false;
        }
        bookMongoCollection.insertOne(book);
        return true;
    }

    public Book removeBook(String id){
        Bson filter = eq("_id", UUID.fromString(id));
        return bookMongoCollection.findOneAndDelete(filter);
    }

    private boolean isExisting(Book book) {
        Bson filter;
        filter = or(eq("title", book.getTitle()), eq("_id", book.getBookId()));

        ArrayList<Book> ls = bookMongoCollection.find(filter).into(new ArrayList<>());
        return !ls.isEmpty();
    }
    public void drop()
    {
        bookMongoCollection.drop();
    }


    public ArrayList<Book> findAll() {
        return bookMongoCollection.find().into(new ArrayList<> ());
    }

    public ArrayList<Book> find(String id) {
        Bson filter = eq("_id", UUID.fromString(id));

        return bookMongoCollection.find(filter, Book.class).into(new ArrayList<> ());
    }

    public Book updateOne(String id, Bson updateOperation) {
        Bson filter = eq("_id", UUID.fromString(id));
        return bookMongoCollection.findOneAndUpdate(filter, updateOperation);
    }

    public UpdateResult updateMany(Bson filter, Bson updateOperation) {
        return bookMongoCollection.updateMany(filter, updateOperation);
    }

    @Override
    public void close() throws Exception {

    }
}
